package com.example.gamevault

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gamevault.adapter.GameItemAdapter
import com.example.gamevault.viewmodel.GameViewModel

class MainActivity : AppCompatActivity() {

    private val gameViewModel: GameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val addButton = findViewById<Button>(R.id.button_add_item)

        // item click and delete click listeners
        val adapter = GameItemAdapter(
            onItemClick = { gameItem ->
                val intent = Intent(this, AddEditGameItemActivity::class.java).apply {
                    putExtra("EXTRA_ID", gameItem.id)
                    putExtra("EXTRA_NAME", gameItem.name)
                    putExtra("EXTRA_DESCRIPTION", gameItem.description)
                }
                startActivity(intent)
            },
            onDeleteClick = { gameItem ->
                gameViewModel.delete(gameItem)
            }
        )

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        gameViewModel.allGameItems.observe(this) { items ->
            items?.let { adapter.submitList(it) }
        }

        // adding a new item
        addButton.setOnClickListener {
            val intent = Intent(this, AddEditGameItemActivity::class.java)
            startActivity(intent)
        }
    }
}
