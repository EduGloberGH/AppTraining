package com.edu.glob.pokeapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.edu.glob.pokeapp.R
import com.edu.glob.pokeapp.databinding.ActivityMainBinding
import com.edu.glob.pokeapp.viewmodel.ListViewModel

class MainActivity : AppCompatActivity() {

    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModel: ListViewModel
    private val pokemonAdapter = PokemonListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()

        binding.pokemonList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = pokemonAdapter
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.pokemons.observe(this, Observer {
            pokemons -> pokemons?.let {
                pokemonAdapter.updatePokemon(it) }
        })

        viewModel.pokemonLoadError.observe(this, Observer {
            isError -> isError?.let {
                binding.listError.visibility = if(it) View.VISIBLE else View.GONE

            }
        })

        viewModel.loading.observe(this, Observer {
            isLoading-> isLoading?.let {
            binding.loadingView.visibility = if(it) View.VISIBLE else View.GONE
            if(it){
                binding.listError.visibility = View.GONE
                binding.pokemonList.visibility = View.GONE
            }
        }
        })
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}