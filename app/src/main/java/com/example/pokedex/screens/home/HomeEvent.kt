package com.example.pokedex.screens.home

sealed class HomeEvent {
    data class GetSearchPokemon(val query: String) : HomeEvent()
    object ClearSearchText : HomeEvent()
}