package com.example.gamevault.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.gamevault.dao.GameItemDao
import com.example.gamevault.database.GameDatabase
import com.example.gamevault.entity.GameEntity

class GameRepository(application: Application) {

    private val gameItemDao: GameItemDao
    val allGameItems: LiveData<List<GameEntity>>

    init {
        val database = GameDatabase.getDatabase(application)
        gameItemDao = database.gameItemDao()
        allGameItems = gameItemDao.getAllGameItems()
    }

    suspend fun insert(gameItem: GameEntity) {
        gameItemDao.insert(gameItem)
    }

    suspend fun update(gameItem: GameEntity) {
        gameItemDao.update(gameItem)
    }

    suspend fun delete(gameItem: GameEntity) {
        gameItemDao.delete(gameItem)
    }
}
