package com.example.pokedex.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Normal(
    @SerialName("use_after")
    val useAfter: List<UseAfter>,
    @SerialName("use_before")
    val useBefore: String?
)