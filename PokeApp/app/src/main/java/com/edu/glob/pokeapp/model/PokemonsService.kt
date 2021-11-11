package com.edu.glob.pokeapp.model

import com.edu.glob.pokeapp.Pokemon
import com.edu.glob.pokeapp.PokemonResponse
import com.edu.glob.pokeapp.SinglePokemonResponse
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class PokemonsService {

    private val BASE_URL = "https://pokeapi.co"
    private val api : PokemonApi

    init {
        api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(PokemonApi::class.java)
    }

    fun getPokemons(): Single<PokemonResponse>{
        return api.getPokemons(10,0)
    }

    fun getSinglePokemon(id : Int): Single<SinglePokemonResponse>{
        return  api.getSinglePokemon(id)
    }
}