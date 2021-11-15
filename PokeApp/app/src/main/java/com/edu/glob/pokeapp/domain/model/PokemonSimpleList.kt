package com.edu.glob.pokeapp.domain.model

data class PokemonSimpleList(
    var nextListURL: String = "",
    var list: MutableList<PokemonSimpleItem> = mutableListOf()
)

data class PokemonSimpleItem(
    var name: String = "",
    var detailURL: String = ""
)