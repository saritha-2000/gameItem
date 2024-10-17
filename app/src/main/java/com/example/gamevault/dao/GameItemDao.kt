package com.example.gamevault.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.gamevault.entity.GameEntity

@Dao
interface GameItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(gameItem: GameEntity)

    @Update
    suspend fun update(gameItem: GameEntity)

    @Delete
    suspend fun delete(gameItem: GameEntity)

    @Query("SELECT * FROM game_items")
    fun getAllGameItems(): LiveData<List<GameEntity>>
}
