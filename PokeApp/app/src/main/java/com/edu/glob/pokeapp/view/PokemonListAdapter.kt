package com.edu.glob.pokeapp.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.edu.glob.pokeapp.Pokemon
import com.edu.glob.pokeapp.R
import com.edu.glob.pokeapp.databinding.ItemPokemonBinding
import com.edu.glob.pokeapp.util.extractPokemonId
import com.edu.glob.pokeapp.util.getProgressDrawable
import com.edu.glob.pokeapp.util.loadImage

class PokemonListAdapter(var pokemons: ArrayList<Pokemon>):RecyclerView.Adapter<PokemonListAdapter.PokemonViewHolder>() {

    fun updatePokemon(newPokemons: List<Pokemon>){
        pokemons.clear()
        pokemons.addAll(newPokemons)
        notifyDataSetChanged()
    }

    inner class PokemonViewHolder(binding: ItemPokemonBinding): RecyclerView.ViewHolder(binding.root){

        private val pokemonName = binding.name
        private val imageView = binding.imageView
        private val cardView = binding.cardPokemon
        private val progressDrawable = getProgressDrawable(binding.root.context)
        private val context = binding.root.context

        fun bind(pokemon: Pokemon){
            pokemonName.text = pokemon.pokemonName
            imageView.loadImage(pokemon.pokemonUrl, progressDrawable)

            cardView.setOnClickListener {
                val toPass = Bundle()
                toPass.putString("url", pokemon.pokemonUrl)
                toPass.putString("id", pokemon.pokemonUrl?.extractPokemonId().toString())


                val intent =
                    Intent(context, DetailActivity::class.java) //context we got from constructor
                intent.putExtras(toPass)
                context.startActivity(intent) // or we can use ContextCompat
            }

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