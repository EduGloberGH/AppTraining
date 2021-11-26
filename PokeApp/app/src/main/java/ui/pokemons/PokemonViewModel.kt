package com.edu.glob.pokeapp.ui.pokemons

import androidx.lifecycle.MutableLiveData
import com.edu.glob.pokeapp.base.BaseViewModel
import com.edu.glob.pokeapp.model.Pokemon

class PokemonViewModel:BaseViewModel() {
    private val pokemonName = MutableLiveData<String>()

    fun bind(pokemon: Pokemon){
        pokemonName.value = pokemon.name
    }

    fun getPokemonName():MutableLiveData<String>{
        return pokemonName
    }
}