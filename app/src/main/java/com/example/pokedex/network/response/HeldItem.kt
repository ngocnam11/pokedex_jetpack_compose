package com.example.pokedex.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HeldItem(
    val item: Item,
    @SerialName("version_details")
    val versionDetails: List<VersionDetail>,
)
