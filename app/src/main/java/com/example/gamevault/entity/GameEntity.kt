import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game_items")
data class GameItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val description: String,
    val completed: Boolean = false
)
