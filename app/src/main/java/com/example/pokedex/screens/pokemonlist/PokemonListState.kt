package com.example.pokedex.screens.pokemonlist

import com.example.pokedex.data.model.Pokemon

data class PokemonListState (
    val pokemons: List<Pokemon> = emptyList(),
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
)