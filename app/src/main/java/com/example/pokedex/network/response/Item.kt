package com.example.pokedex.network.response

import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val name: String,
    val url: String
)
