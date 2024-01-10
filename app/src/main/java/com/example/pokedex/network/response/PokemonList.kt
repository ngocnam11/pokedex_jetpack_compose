package com.example.pokedex.network.response

import kotlinx.serialization.Serializable

@Serializable
data class PokemonList(
    val count: Int,
    val next: String,
    val previous: String?,
    val results: List<Result>
)