package com.example.pokedex.screens.home

import com.example.pokedex.data.model.Pokemon

data class HomeState(
    val pokemon: Pokemon = Pokemon(),
    val query: String = "",
    val isVisiblePokemon: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String = "Error",
    val hasError: Boolean = false
)