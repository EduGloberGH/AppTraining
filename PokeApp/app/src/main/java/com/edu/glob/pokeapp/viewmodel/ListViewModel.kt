package com.edu.glob.pokeapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.edu.glob.pokeapp.Pokemon

class ListViewModel: ViewModel() {

    //livedata
    val pokemons = MutableLiveData<List<Pokemon>>()
    val pokemonLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh(){
        fetchPokemons()
    }
    //evade of showing the actual method
    private fun fetchPokemons(){

        //add mockdata for testing before retrofit
        val mockData = listOf(Pokemon("pokemon 1"),
            Pokemon("pokemon 2"),
            Pokemon("pokemon 3"),
            Pokemon("pokemon 4"),
            Pokemon("pokemon 5"),
            Pokemon("pokemon 6"),
            Pokemon("pokemon 7"),)

        pokemonLoadError.value= false
        loading.value= false
        pokemons.value = mockData
    }
}