package com.edu.glob.pokeapp

import androidx.multidex.MultiDexApplication
import com.edu.glob.pokeapp.di.repositoryModule
import com.edu.glob.pokeapp.di.retrofitModule
import com.edu.glob.pokeapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PokeAppApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@PokeAppApplication)
            modules(
                listOf(
                    retrofitModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }

}