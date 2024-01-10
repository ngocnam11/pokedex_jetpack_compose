package com.example.pokedex.screens.detail

sealed class DetailEvent {
    data class GetPokemonDetail(val name: String) : DetailEvent()
    data class FavoritePokemon(val name: String) : DetailEvent()

}