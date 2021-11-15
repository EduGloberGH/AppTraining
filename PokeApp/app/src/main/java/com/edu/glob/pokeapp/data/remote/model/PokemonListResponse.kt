package com.edu.glob.pokeapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class PokemonListResponse(
    @SerializedName("count") val count: Int? = null,
    @SerializedName("next") val next: String? = null,
    @SerializedName("previous") val previous: String? = null,
    @SerializedName("results") val results: List<PokemonSimpleResponse>? = null
)

data class PokemonSimpleResponse(
    @SerializedName("name") val name: String? = null,
    @SerializedName("url") val url: String? = null
)