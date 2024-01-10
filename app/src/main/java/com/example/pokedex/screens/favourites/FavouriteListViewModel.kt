package com.example.pokedex.screens.favourites

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.pokedex.PokedexApplication
import com.example.pokedex.data.PokemonRepository
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class FavouriteListViewModel(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {
    var state by mutableStateOf(FavouriteListState())
        private set

    init {
        getFavoritesPokemon()
    }

    private fun getFavoritesPokemon() {
        viewModelScope.launch {
            pokemonRepository.getFavoritePokemons()
                .onEach {
                    state = state.copy(
                        pokemonsFav = it
                    )
                }.launchIn(this)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as PokedexApplication)
                val pokemonRepository = application.container.pokemonRepository
                FavouriteListViewModel(pokemonRepository = pokemonRepository)
            }
        }
    }
}