package com.example.pokedex.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Meta(
    val ailment: Ailment,
    @SerialName("ailment_chance")
    val ailmentChance: Int,
    val category: Category,
    @SerialName("crit_rate")
    val critRate: Int,
    val drain: Int,
    @SerialName("flinch_chance")
    val flinchChance: Int,
    val healing: Int,
    @SerialName("maxHits")
    val max_hits: Int?,
    @SerialName("max_turns")
    val max_turns: Int?,
    @SerialName("min_hits")
    val minHits: Int?,
    @SerialName("min_turns")
    val minTurns: Int?,
    @SerialName("stat_chance")
    val statChance: Int
)