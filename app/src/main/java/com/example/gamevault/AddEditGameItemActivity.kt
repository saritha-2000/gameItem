package com.example.gamevault

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.gamevault.R
import com.example.gamevault.entity.GameEntity
import com.example.gamevault.viewmodel.GameViewModel

class AddEditGameItemActivity : AppCompatActivity() {

    private val gameViewModel: GameViewModel by viewModels()
    private lateinit var saveButton: Button
    private lateinit var nameEditText: EditText
    private lateinit var descriptionEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_game_item)

        saveButton = findViewById(R.id.button_save)
        nameEditText = findViewById(R.id.edit_text_name)
        descriptionEditText = findViewById(R.id.edit_text_description)

        saveButton.setOnClickListener {
            saveGameItem()
        }
    }

    private fun saveGameItem() {
        val name = nameEditText.text.toString()
        val description = descriptionEditText.text.toString()

        if (name.isBlank() || description.isBlank()) {
            Toast.makeText(this, "Please enter a name and description", Toast.LENGTH_SHORT).show()
            return
        }

        // Use GameEntity instead of GameItem
        val gameItem = GameEntity(name = name, description = description)
        gameViewModel.insert(gameItem)

        val resultIntent = Intent().apply {
            putExtra("EXTRA_NAME", name)
            putExtra("EXTRA_DESCRIPTION", description)
        }
        setResult(RESULT_OK, resultIntent)
        finish()
    }
}
