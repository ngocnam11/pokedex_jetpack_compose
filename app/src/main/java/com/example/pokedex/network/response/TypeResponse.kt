package com.example.pokedex.network.response

import kotlinx.serialization.Serializable

@Serializable
data class TypeResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<ResultX>
)