package com.example.pokedex.network.response

import kotlinx.serialization.Serializable

@Serializable
data class Result(
    val name: String,
    val url: String
)
