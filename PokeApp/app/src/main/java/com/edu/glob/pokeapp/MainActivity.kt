package com.edu.glob.pokeapp

import android.os.Bundle
import android.view.View
import com.edu.glob.pokeapp.databinding.ActivityMainBinding
import com.edu.glob.pokeapp.domain.model.PokemonSimpleList
import com.edu.glob.pokeapp.ui.base.BaseActivity
import com.edu.glob.pokeapp.ui.feature.home.HomeViewModel
import com.edu.glob.pokeapp.ui.feature.home.PokemonListAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, HomeViewModel>() {

    private val vm: HomeViewModel by viewModel()

    override fun getViewBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    override fun getMainViewModel(): HomeViewModel = vm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getPokemonList()
    }

    override fun processData(any: Any) {
        val data = any as PokemonSimpleList
        binding.pokemonRecycler.apply {
            adapter = PokemonListAdapter(data.list)
        }
    }

    override fun processError(message: String) {
        binding.pokemonError.text = message
    }

    override fun showLoading(show: Boolean) {
        binding.pokemonLoading.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun processNoData() {
        processError("No hay data")
    }

}