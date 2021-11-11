package com.edu.glob.pokeapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.edu.glob.pokeapp.SinglePokemonResponse
import com.edu.glob.pokeapp.di.DaggerApiComponent
import com.edu.glob.pokeapp.model.PokemonsService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class StatsViewModel: ViewModel() {

    @Inject
    lateinit var pokemonService: PokemonsService
    @Inject
    lateinit var disposable: CompositeDisposable
    init {
        DaggerApiComponent.create().Inject(this)
    }

    //liveData
    val details = MutableLiveData<SinglePokemonResponse>()
    val detailsLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh(id: Int)
    {
        fetchDetails(id)
    }

    private fun fetchDetails(id: Int) {
        loading.value = true
        disposable.add(
            pokemonService.getSinglePokemon(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<SinglePokemonResponse>(){
                    override fun onSuccess(t: SinglePokemonResponse) {
                        details.value = t
                        detailsLoadError.value = false
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        loading.value = false
                        detailsLoadError.value = false
                    }

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}