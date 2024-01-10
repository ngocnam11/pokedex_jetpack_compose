package com.example.pokedex.data.model

import com.example.pokedex.network.response.Stat

data class Pokemon(
    val id: Int? = null,
    val name: String = "",
    val imageUrl: String = "",
    val height: Int = 0,
    val weight: Int = 0,
    val types: List<String> = emptyList(),
    val moves: List<String> = emptyList(),
    val stats: List<Stat> = emptyList(),
    val abilities: List<String> = emptyList(),
    val isFavorite: Boolean = false
)