package com.example.pokedex.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonResponse(
    val abilities: List<Ability>,
    @SerialName("base_experience")
    val baseExperience: Int,
    val forms: List<Form>,
    @SerialName("game_indices")
    val gameIndices: List<GameIndice>,
    val height: Int,
    @SerialName("held_items")
    val heldItems: List<HeldItem>,
    val id: Int,
    @SerialName("is_default")
    val isDefault: Boolean,
    @SerialName("location_area_encounters")
    val locationAreaEncounters: String,
    val moves: List<Move>,
    val name: String,
    val order: Int,
    @SerialName("past_abilities")
    val pastAbilities: List<String>? = emptyList(),
    @SerialName("past_types")
    val pastTypes: List<PastType>,
    val species: Species,
    val sprites: Sprites,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Int
)