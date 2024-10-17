import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [GameItem::class], version = 1)
abstract class GameDatabase : RoomDatabase() {
    abstract fun gameItemDao(): GameItemDao

    companion object {
        @Volatile
        private var INSTANCE: GameDatabase? = null

        fun getDatabase(context: Context): GameDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GameDatabase::class.java,
                    "game_item_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
