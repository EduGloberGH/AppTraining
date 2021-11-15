package com.edu.glob.pokeapp.di

import com.edu.glob.pokeapp.data.repository.PokemonRepositoryImpl
import com.edu.glob.pokeapp.domain.repository.PokemonRepository
import org.koin.dsl.module

val repositoryModule = module {

    factory<PokemonRepository> {
        PokemonRepositoryImpl(get())
    }

}