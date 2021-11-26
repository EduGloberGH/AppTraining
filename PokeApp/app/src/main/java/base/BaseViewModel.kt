package com.edu.glob.pokeapp.base

import androidx.lifecycle.ViewModel
import com.edu.glob.pokeapp.injection.injection.component.ViewModelInjector
import com.edu.glob.pokeapp.injection.injection.component.DaggerViewModelInjector
import com.edu.glob.pokeapp.injection.injection.module.NetworkModule
import com.edu.glob.pokeapp.ui.pokemons.PokemonListViewModel

abstract class BaseViewModel: ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is PokemonListViewModel -> injector.inject(this)
        }
    }
}