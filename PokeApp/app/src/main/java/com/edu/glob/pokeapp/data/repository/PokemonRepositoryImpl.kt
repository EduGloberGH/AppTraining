package com.edu.glob.pokeapp.data.repository

import com.edu.glob.pokeapp.data.remote.apis.PokemonAPI
import com.edu.glob.pokeapp.data.remote.converter.PokemonListConverter
import com.edu.glob.pokeapp.domain.interactor.UseCaseResult
import com.edu.glob.pokeapp.domain.model.PokemonSimpleList
import com.edu.glob.pokeapp.domain.repository.PokemonRepository
import kotlinx.coroutines.delay

class PokemonRepositoryImpl(val api: PokemonAPI) : PokemonRepository {

    override suspend fun pokemonList(offset: Int, limit: Int): UseCaseResult<PokemonSimpleList> {
        try {
            api.pokemonList()?.let {
                val pokemonList = PokemonListConverter.convert(it)
                return if (pokemonList.list.isNotEmpty())
                    UseCaseResult.Success(pokemonList)
                else UseCaseResult.NoData
            } ?: run { return UseCaseResult.NoData }
        } catch (ex: Exception) {
            return UseCaseResult.Error(ex)
        }
    }

}