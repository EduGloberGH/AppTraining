package com.edu.glob.pokeapp.model


import com.edu.glob.pokeapp.PokemonResponse
import com.edu.glob.pokeapp.SinglePokemonResponse
import com.edu.glob.pokeapp.di.DaggerApiComponent
import io.reactivex.Single
import javax.inject.Inject

class PokemonsService {

    @Inject
    lateinit var api : PokemonApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getPokemons(): Single<PokemonResponse>{
        return api.getPokemons(10,0)
    }

    fun getSinglePokemon(id : Int): Single<SinglePokemonResponse>{
        return  api.getSinglePokemon(id)
    }
}