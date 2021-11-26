package com.edu.glob.pokeapp.ui.pokemons

import javax.inject.Inject
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.edu.glob.pokeapp.R
import com.edu.glob.pokeapp.base.BaseViewModel
import com.edu.glob.pokeapp.model.Pokemon
import com.edu.glob.pokeapp.network.PokemonApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class PokemonListViewModel:BaseViewModel() {

    @Inject
    lateinit var pokemonApi: PokemonApi

    private lateinit var subscription: Disposable

    init {
        loadPokemons()
    }

    private fun loadPokemons() {
        subscription = pokemonApi.getPokemons()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrievePokemonListStart() }
            .doOnTerminate { onRetrievePokemonListFinish() }
            .subscribe(
                { result -> onRetrievePokemonListSuccess(result) },
                { onRetrievePokemonListError() }
            )
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadPokemons() }
    val pokemonListAdapter: PokemonListAdapter = PokemonListAdapter()

    private fun onRetrievePokemonListStart(){
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrievePokemonListFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onRetrievePokemonListSuccess(pokemonList:List<Pokemon>){
        pokemonListAdapter.updatePokemonList(pokemonList)
    }

    private fun onRetrievePokemonListError(){
        errorMessage.value = R.string.post_error
    }
}