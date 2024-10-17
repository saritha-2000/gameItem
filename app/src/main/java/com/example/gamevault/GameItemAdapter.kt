package com.example.gamevault.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gamevault.R
import com.example.gamevault.entity.GameEntity

class GameItemAdapter(
    private val onItemClick: (GameEntity) -> Unit,
    private val onDeleteClick: (GameEntity) -> Unit
) : ListAdapter<GameEntity, GameItemAdapter.GameItemViewHolder>(GameItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_game_item_adapter, parent, false)
        return GameItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GameItemViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)

        holder.itemView.setOnClickListener {
            onItemClick(currentItem)
        }
    }

    inner class GameItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.text_view_name)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.text_view_description)
        private val deleteButton: ImageButton = itemView.findViewById(R.id.button_delete)

        fun bind(gameItem: GameEntity) {
            nameTextView.text = gameItem.name
            descriptionTextView.text = gameItem.description

            // Set click listener for delete button
            deleteButton.setOnClickListener {
                onDeleteClick(gameItem)
            }
        }
    }

    class GameItemDiffCallback : DiffUtil.ItemCallback<GameEntity>() {
        override fun areItemsTheSame(oldItem: GameEntity, newItem: GameEntity): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: GameEntity, newItem: GameEntity): Boolean = oldItem == newItem
    }
}
