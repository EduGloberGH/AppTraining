package com.edu.glob.pokeapp.data.remote.apis

import com.edu.glob.pokeapp.data.remote.model.PokemonListResponse
import retrofit2.http.GET

interface PokemonAPI {

    @GET(URLs.POKEMON_LIST)
    suspend fun pokemonList(): PokemonListResponse?

}