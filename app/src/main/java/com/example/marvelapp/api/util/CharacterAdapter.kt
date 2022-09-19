package com.example.marvelapp.api.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvelapp.R
import com.example.marvelapp.databinding.CardViewCharacterItemBinding
import com.example.marvelapp.entity.Character

class CharacterAdapter(private val characters: List<Character>) : RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

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
        holder.bind(characters[position])
    }

    override fun getItemCount(): Int = characters.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = CardViewCharacterItemBinding.bind(itemView)
        fun bind(item: Character) {
            item.let {
                binding.apply {
                    this.characterName.text = item.name
                    Glide.with(itemView.context).load(item.imageURL).into(this.characterImage);
                    if (item.description.isNotEmpty()) {
                        this.characterDesc.text = item.description
                    } else {
                        this.characterDesc.text = itemView.resources.getString(R.string.empty_description)
                    }
                }
            }
        }
    }
}
