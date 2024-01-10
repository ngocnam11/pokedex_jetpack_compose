package com.example.pokedex.network.response

import kotlinx.serialization.Serializable

@Serializable
data class PastType(
    val generation: Generation,
    val types: List<Type>,
)
