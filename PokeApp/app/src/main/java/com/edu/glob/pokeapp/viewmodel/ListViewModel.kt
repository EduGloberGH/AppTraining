package com.edu.glob.pokeapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.edu.glob.pokeapp.Pokemon
import com.edu.glob.pokeapp.PokemonResponse
import com.edu.glob.pokeapp.di.DaggerApiComponent
import com.edu.glob.pokeapp.model.PokemonsService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ListViewModel : ViewModel() {

    @Inject
    lateinit var pokemonsService: PokemonsService
    @Inject
    lateinit var disposable: CompositeDisposable
    init {
        DaggerApiComponent.create().inject(this)
    }


    //livedata
    val pokemons = MutableLiveData<List<Pokemon>>()
    val pokemonLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchPokemons()
    }

    //evade of showing the actual method
    private fun fetchPokemons() {
        loading.value = true
        disposable.add(
            pokemonsService.getPokemons()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<PokemonResponse>() {
                    override fun onSuccess(value: PokemonResponse) {
                        pokemons.value = value.results
                        pokemonLoadError.value = false
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        pokemonLoadError.value = true
                        loading.value = false
                    }

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}