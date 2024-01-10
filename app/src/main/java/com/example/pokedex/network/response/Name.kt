package com.example.pokedex.network.response

import kotlinx.serialization.Serializable

@Serializable
data class Name(
    val language: LanguageXX,
    val name: String
)