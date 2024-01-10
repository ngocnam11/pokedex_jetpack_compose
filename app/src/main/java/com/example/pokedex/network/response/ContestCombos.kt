package com.example.pokedex.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ContestCombos(
    val normal: Normal,
    @SerialName("super")
    val supe: Super
)
