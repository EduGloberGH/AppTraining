package com.edu.glob.pokeapp.ui.feature.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.edu.glob.pokeapp.databinding.LayoutPokemonSimpleItemBinding
import com.edu.glob.pokeapp.domain.model.PokemonSimpleItem

class PokemonListAdapter(val data: List<PokemonSimpleItem>) :
    RecyclerView.Adapter<PokemonListAdapter.PokemonItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonItemViewHolder {
        return PokemonItemViewHolder(
            LayoutPokemonSimpleItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: PokemonItemViewHolder, position: Int) {
        holder.binding.pokemonName.text = data[position].name
    }

    override fun getItemCount(): Int = data.size

    class PokemonItemViewHolder(val binding: LayoutPokemonSimpleItemBinding) :
        RecyclerView.ViewHolder(binding.root)

}