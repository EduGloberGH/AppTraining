package com.edu.glob.pokeapp.ui.feature.home

import com.edu.glob.pokeapp.domain.interactor.UseCaseResult
import com.edu.glob.pokeapp.domain.model.PokemonSimpleList
import com.edu.glob.pokeapp.domain.repository.PokemonRepository
import com.edu.glob.pokeapp.ui.base.ViewModelBase
import com.edu.glob.pokeapp.ui.interactor.UIState
import com.edu.glob.pokeapp.util.notNull
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val repository: PokemonRepository) :
    ViewModelBase<PokemonSimpleList>() {

    fun getPokemonList() {
        launch {
            uiState.postValue(UIState.Loading(true))
            val result = withContext(IO) { repository.pokemonList(0, 100) }
            uiState.postValue(UIState.Loading(false))
            when (result) {
                is UseCaseResult.Success -> uiState.postValue(
                    UIState.Data(result.data)
                )
                is UseCaseResult.Error -> uiState.postValue(
                    UIState.Error(result.exception.message.notNull())
                )
                is UseCaseResult.NoData -> uiState.postValue(UIState.NoData)
            }
        }
    }

}