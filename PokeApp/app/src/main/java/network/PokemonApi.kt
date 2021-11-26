package com.edu.glob.pokeapp.network

import com.edu.glob.pokeapp.model.Pokemon
import io.reactivex.Observable
import retrofit2.http.GET

interface PokemonApi {
    @GET("/pokemon")
    fun getPokemons(): Observable<List<Pokemon>>
}