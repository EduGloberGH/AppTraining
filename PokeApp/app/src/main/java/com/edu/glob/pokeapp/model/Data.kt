package com.edu.glob.pokeapp

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import dagger.multibindings.IntoMap


data class Pokemon(
    @SerializedName("name")
    val pokemonName: String?,
    @SerializedName("url")
    val pokemonUrl: String?,
)

data class PokemonResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Pokemon>
)

data class Stat(
    val name: String,
    val url: String,
)

data class Stats(
    val base_stat: Int,
    val effort: Int,
    val stat: Stat
    )

data class Sprites(
    val back_default: String,
    val back_shiny: String,
    val front_default: String,
    val front_shiny: String
)

data class SinglePokemonResponse(
    val sprites: Sprites,
    val stats: List<Stats>,
    val height: Int,
    val weight: Int,
)