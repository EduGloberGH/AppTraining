package com.edu.glob.pokeapp.di

import com.edu.glob.pokeapp.model.PokemonsService
import com.edu.glob.pokeapp.viewmodel.ListViewModel
import com.edu.glob.pokeapp.viewmodel.StatsViewModel
import dagger.Component
import javax.inject.Inject

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(Service: PokemonsService)

    fun inject(viewModel: ListViewModel)

    fun Inject(viewModel: StatsViewModel)

}