package com.example.pokedex.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Super(
    @SerialName("use_after")
    val useAfter: List<String>?,
    @SerialName("use_before")
    val useBefore: String?
)