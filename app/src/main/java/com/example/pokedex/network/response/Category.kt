package com.example.pokedex.network.response

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val name: String,
    val url: String
)
