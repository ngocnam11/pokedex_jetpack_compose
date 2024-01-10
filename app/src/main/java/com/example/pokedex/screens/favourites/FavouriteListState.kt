package com.example.pokedex.screens.favourites

import com.example.pokedex.data.model.Pokemon

data class FavouriteListState(
    val pokemonsFav: List<Pokemon> = emptyList()
)