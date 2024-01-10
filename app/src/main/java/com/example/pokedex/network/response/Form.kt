package com.example.pokedex.network.response

import kotlinx.serialization.Serializable

@Serializable
data class Form(
    val name: String,
    val url: String
)