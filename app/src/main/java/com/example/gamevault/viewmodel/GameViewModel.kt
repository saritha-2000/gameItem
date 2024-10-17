package com.example.gamevault.viewmodel

import GameItem
import GameRepository
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class GameViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: GameRepository = GameRepository(application)
    val allGameItems: LiveData<List<GameItem>> = repository.allGameItems

    fun insert(gameItem: GameItem) = viewModelScope.launch {
        repository.insert(gameItem)
    }

    fun update(gameItem: GameItem) = viewModelScope.launch {
        repository.update(gameItem)
    }

    fun delete(gameItem: GameItem) = viewModelScope.launch {
        repository.delete(gameItem)
    }
}