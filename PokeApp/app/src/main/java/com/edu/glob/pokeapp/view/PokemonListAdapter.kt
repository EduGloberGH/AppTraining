package com.edu.glob.pokeapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.edu.glob.pokeapp.Pokemon
import com.edu.glob.pokeapp.R
import com.edu.glob.pokeapp.databinding.ItemPokemonBinding

class PokemonListAdapter(var pokemons: ArrayList<Pokemon>):RecyclerView.Adapter<PokemonListAdapter.PokemonViewHolder>() {

    fun updatePokemon(newPokemons: List<Pokemon>){
        pokemons.clear()
        pokemons.addAll(newPokemons)
        notifyDataSetChanged()
    }

    inner class PokemonViewHolder(binding: ItemPokemonBinding): RecyclerView.ViewHolder(binding.root){

        private val pokemonName = binding.name

        fun bind(pokemon: Pokemon){
            pokemonName.text = pokemon.pokemonName
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
      val binding = ItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(pokemons[position])
    }

    override fun getItemCount() = pokemons.size
}