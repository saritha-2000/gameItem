package com.example.gamevault

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.gamevault.entity.GameEntity
import com.example.gamevault.viewmodel.GameViewModel

class AddEditGameItemActivity : AppCompatActivity() {

    private val gameViewModel: GameViewModel by viewModels()
    private lateinit var saveButton: Button
    private lateinit var backButton: Button
    private lateinit var nameEditText: EditText
    private lateinit var descriptionEditText: EditText
    private var itemId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_game_item)

        // initialize views
        saveButton = findViewById(R.id.button_save)
        backButton = findViewById(R.id.button_back)
        nameEditText = findViewById(R.id.edit_text_name)
        descriptionEditText = findViewById(R.id.edit_text_description)

        // get existing item data
        val extras = intent.extras
        if (extras != null) {
            itemId = extras.getInt("EXTRA_ID")
            nameEditText.setText(extras.getString("EXTRA_NAME"))
            descriptionEditText.setText(extras.getString("EXTRA_DESCRIPTION"))
        }

        saveButton.setOnClickListener {
            saveGameItem()
        }

        backButton.setOnClickListener {
            finish()
        }
    }

    private fun saveGameItem() {
        val name = nameEditText.text.toString()
        val description = descriptionEditText.text.toString()

        if (name.isBlank() || description.isBlank()) {
            Toast.makeText(this, "Please enter a name and description", Toast.LENGTH_SHORT).show()
            return
        }

        val gameItem = GameEntity(id = itemId ?: 0, name = name, description = description)
        if (itemId != null) {
            gameViewModel.update(gameItem)
        } else {
            gameViewModel.insert(gameItem)
        }

        finish()
    }
}
