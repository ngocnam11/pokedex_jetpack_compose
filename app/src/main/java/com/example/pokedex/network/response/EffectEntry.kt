package com.example.pokedex.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EffectEntry(
    val effect: String,
    val language: Language,
    @SerialName("short_effect")
    val shortEffect: String
)