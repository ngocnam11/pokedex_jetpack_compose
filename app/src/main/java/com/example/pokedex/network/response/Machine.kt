package com.example.pokedex.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Machine(
    val machine: MachineX,
    @SerialName("version_group")
    val versionGroup: VersionGroupXX
)