package com.edu.glob.pokeapp.ui.pokemons

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.edu.glob.pokeapp.R
import com.edu.glob.pokeapp.databinding.ItemPokemonBinding
import com.edu.glob.pokeapp.model.Pokemon

class PokemonListAdapter:RecyclerView.Adapter<PokemonListAdapter.ViewHolder>() {
    private lateinit var pokemonList:List<Pokemon>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListAdapter.ViewHolder {
        val binding: ItemPokemonBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_pokemon, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonListAdapter.ViewHolder, position: Int) {
        holder.bind(pokemonList[position])
    }

    override fun getItemCount(): Int {
        return if(::pokemonList.isInitialized) pokemonList.size else 0
    }

    fun updatePokemonList(pokemonList:List<Pokemon>){
        this.pokemonList = pokemonList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemPokemonBinding):RecyclerView.ViewHolder(binding.root){
        private val viewModel = PokemonViewModel()

        fun bind(pokemon:Pokemon){
            viewModel.bind(pokemon)
            binding.viewModel = viewModel        }
    }
}