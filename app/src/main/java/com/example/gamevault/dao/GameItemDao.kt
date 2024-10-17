import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GameItemDao {
    @Insert
    suspend fun insert(gameItem: GameItem)

    @Update
    suspend fun update(gameItem: GameItem)

    @Delete
    suspend fun delete(gameItem: GameItem)

    @Query("SELECT * FROM game_items")
    fun getAllGameItems(): LiveData<List<GameItem>>
}
