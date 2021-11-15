package com.edu.glob.pokeapp.di

import com.edu.glob.pokeapp.ui.feature.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { HomeViewModel(get()) }

}