package com.example.pokedex.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoveResponse(
    val accuracy: Int,
    @SerialName("contest_combos")
    val contestCombos: ContestCombos,
    @SerialName("contest_effect")
    val contestEffect: ContestEffect,
    @SerialName("contest_type")
    val contestType: ContestType,
    @SerialName("damage_class")
    val damageClass: DamageClass,
    @SerialName("effect_chance")
    val effectChance: String?,
    @SerialName("effect_changes")
    val effectChanges: List<String?>,
    @SerialName("effect_entries")
    val effectEntries: List<EffectEntry>,
    @SerialName("flavor_text_entries")
    val flavorTextEntries: List<FlavorTextEntry>,
    val generation: Generation,
    val id: Int,
    @SerialName("learned_by_pokemon")
    val learnedByPokemon: List<LearnedByPokemon>,
    val machines: List<Machine>,
    val meta: Meta,
    val name: String,
    val names: List<Name>,
    @SerialName("past_values")
    val pastValues: List<String?>,
    val power: Int,
    val pp: Int,
    val priority: Int,
    @SerialName("stat_changes")
    val statChanges: List<String?>,
    @SerialName("super_contest_effect")
    val superContestEffect: SuperContestEffect,
    val target: Target,
    val type: TypeXX
)