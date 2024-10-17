import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gamevault.R
import com.example.gamevault.viewmodel.GameViewModel

class MainActivity : AppCompatActivity() {

    private val gameViewModel: GameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val addButton = findViewById<Button>(R.id.button_add_item)

        val adapter = GameItemAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        gameViewModel.allGameItems.observe(this) { items ->
            items?.let { adapter.submitList(it) }
        }

        addButton.setOnClickListener {
            val intent = Intent(this, AddEditGameItemActivity::class.java)
            startActivity(intent)
        }
    }

}
