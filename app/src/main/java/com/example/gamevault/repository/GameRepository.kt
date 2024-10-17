import android.app.Application
import androidx.lifecycle.LiveData

class GameRepository(application: Application) {
    private val gameItemDao: GameItemDao
    val allGameItems: LiveData<List<GameItem>>

    init {
        val database = GameDatabase.getDatabase(application)
        gameItemDao = database.gameItemDao()
        allGameItems = gameItemDao.getAllGameItems()
    }

    suspend fun insert(gameItem: GameItem) {
        gameItemDao.insert(gameItem)
    }

    suspend fun update(gameItem: GameItem) {
        gameItemDao.update(gameItem)
    }

    suspend fun delete(gameItem: GameItem) {
        gameItemDao.delete(gameItem)
    }
}
