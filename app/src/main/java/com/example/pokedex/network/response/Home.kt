package com.example.pokedex.network.response

import kotlinx.serialization.Serializable

@Serializable
data class Home(
    val front_default: String,
    val front_female: String?,
    val front_shiny: String,
    val front_shiny_female: String?
)