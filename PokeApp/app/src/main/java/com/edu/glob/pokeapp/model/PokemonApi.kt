package com.edu.glob.pokeapp.model

import com.edu.glob.pokeapp.Pokemon
import com.edu.glob.pokeapp.PokemonResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonApi {

    @GET("/api/v2/pokemon/")
    fun getPokemons(
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?
    ): Single<PokemonResponse>
}