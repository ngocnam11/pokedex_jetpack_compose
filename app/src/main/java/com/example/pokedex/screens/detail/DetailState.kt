package com.example.pokedex.screens.detail

import com.example.pokedex.data.model.Pokemon

data class DetailState(
    val pokemon: Pokemon = Pokemon(),
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
    val errorMessage: String = "Error"
)
