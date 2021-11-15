package com.edu.glob.pokeapp.domain.repository

import com.edu.glob.pokeapp.domain.interactor.UseCaseResult
import com.edu.glob.pokeapp.domain.model.PokemonSimpleList

interface PokemonRepository {

    suspend fun pokemonList(offset: Int, limit: Int): UseCaseResult<PokemonSimpleList>

}