package com.edu.glob.pokeapp.data.remote.converter

import com.edu.glob.pokeapp.data.remote.model.PokemonListResponse
import com.edu.glob.pokeapp.domain.model.PokemonSimpleItem
import com.edu.glob.pokeapp.domain.model.PokemonSimpleList
import com.edu.glob.pokeapp.util.notNull

object PokemonListConverter {

    fun convert(pokemonListResponse: PokemonListResponse) : PokemonSimpleList {
        return PokemonSimpleList().apply {
            nextListURL = pokemonListResponse.next.notNull()
            pokemonListResponse.results?.forEach {
                list.add(PokemonSimpleItem(it.name.notNull(), it.url.notNull()))
            }
        }
    }

}