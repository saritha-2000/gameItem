package com.example.gamevault.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.gamevault.database.GameDatabase
import com.example.gamevault.entity.GameEntity
import com.example.gamevault.repository.GameRepository
import kotlinx.coroutines.launch

class GameViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: GameRepository
    val allGameItems: LiveData<List<GameEntity>>

    init {
        // Initialize the database and repository
        val database = GameDatabase.getDatabase(application)
        repository = GameRepository(application)
        allGameItems = repository.allGameItems
    }

    fun insert(gameItem: GameEntity) = viewModelScope.launch {
        repository.insert(gameItem)
    }

    fun update(gameItem: GameEntity) = viewModelScope.launch {
        repository.update(gameItem)
    }

    fun delete(gameItem: GameEntity) = viewModelScope.launch {
        repository.delete(gameItem)
    }
}
