package com.edu.glob.pokeapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.edu.glob.pokeapp.databinding.ActivityDetailBinding
import com.edu.glob.pokeapp.databinding.ActivityMainBinding
import com.edu.glob.pokeapp.util.getProgressDrawable
import com.edu.glob.pokeapp.util.loadImage
import com.edu.glob.pokeapp.util.loadImageFromSprite
import com.edu.glob.pokeapp.viewmodel.ListViewModel
import com.edu.glob.pokeapp.viewmodel.StatsViewModel


class DetailActivity : AppCompatActivity() {

    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModel: StatsViewModel
    private val statsListAdapter = PokemonStatsListAdapter(arrayListOf())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val id: String = bundle?.get("id").toString()

        viewModel = ViewModelProvider(this).get(StatsViewModel::class.java)
        viewModel.refresh(id.toInt())

        binding.pokemonStatList.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = statsListAdapter
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        val progressDrawable = getProgressDrawable(binding.root.context)
        viewModel.details.observe(this, Observer { details ->
            details?.let {
                binding.pokemonItemImage.loadImageFromSprite(
                    it.sprites.front_default,
                    progressDrawable
                )
                binding.pokemonItemHeight.text = it.height.toString()
                binding.pokemonItemWeight.text = it.weight.toString()
                statsListAdapter.updateStats(it.stats)
                binding.pokemonStatList.visibility = View.VISIBLE
            }
        })


    }
}