package com.edu.glob.pokeapp.di

import com.edu.glob.pokeapp.model.PokemonApi
import com.edu.glob.pokeapp.model.PokemonsService
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {

    private val BASE_URL = "https://pokeapi.co"

    @Provides
    fun providePokemonApi():PokemonApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(PokemonApi::class.java)
    }

    @Provides
    fun providesPokemonService(): PokemonsService{
        return  PokemonsService()
    }

    @Provides
    fun providesCompositeDisposable(): CompositeDisposable{
        return CompositeDisposable()
    }
}