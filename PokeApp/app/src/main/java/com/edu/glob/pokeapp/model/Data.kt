package com.edu.glob.pokeapp

import android.os.Parcelable
import com.google.gson.annotations.SerializedName


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