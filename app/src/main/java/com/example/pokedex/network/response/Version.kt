package com.example.pokedex.network.response

import kotlinx.serialization.Serializable

@Serializable
data class Version(
    val name: String,
    val url: String
)