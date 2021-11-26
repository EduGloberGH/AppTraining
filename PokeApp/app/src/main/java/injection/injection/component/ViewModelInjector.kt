package com.edu.glob.pokeapp.injection.injection.component

import javax.inject.Singleton
import com.edu.glob.pokeapp.injection.injection.module.NetworkModule
import com.edu.glob.pokeapp.ui.pokemons.PokemonListViewModel
import dagger.Component

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {
    /**
     * Injects required dependencies into the specified PokemonListViewModel.
     * @param pokemonListViewModel PokemonListViewModel in which to inject the dependencies
     */
    fun inject(pokemonListViewModel: PokemonListViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector
        fun networkModule(networkModule: NetworkModule): Builder
    }
}