package com.example.pokedex.network.response

import kotlinx.serialization.Serializable

@Serializable
data class Target(
    val name: String,
    val url: String
)