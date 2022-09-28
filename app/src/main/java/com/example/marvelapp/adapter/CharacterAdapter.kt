package com.example.marvelapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.entity.Character
import com.example.marvelapp.R
import com.example.marvelapp.databinding.CardViewCharacterItemBinding

interface CharacterAdapterListener {
    fun setOnClickListener(characterId: String)
}

class CharacterAdapter(
    private val characters: List<Character>,
    private val listener: CharacterAdapterListener
) : RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.card_view_character_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(characters[position], listener)
    }

    override fun getItemCount(): Int = characters.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = CardViewCharacterItemBinding.bind(itemView)
        fun bind(item: Character, listener: CharacterAdapterListener) {
            binding.apply {
                this.characterName.text = item.name
                Glide.with(itemView.context)
                    .load(item.imageURL)
                    .into(this.characterImage)
                binding.cardViewCharacterContainer.setOnClickListener {
                    listener.setOnClickListener(item.id)
                }
            }
        }
    }
}
