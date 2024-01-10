package com.example.pokedex.network.response

import kotlinx.serialization.Serializable

@Serializable
data class ResultX(
    val name: String,
    val url: String
)