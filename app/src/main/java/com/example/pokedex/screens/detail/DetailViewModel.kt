package com.example.pokedex.screens.detail

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
import com.example.pokedex.util.toPokemon
import kotlinx.coroutines.launch

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data = data)
    class Error<T>(errorMessage: String) : Resource<T>(message = errorMessage)
}


class DetailViewModel(
    private val pokemonRepository: PokemonRepository,
) : ViewModel() {
    var state by mutableStateOf(DetailState(isLoading = false))
        private set

    fun onEvent(event: DetailEvent) {
        state = state.copy(isLoading = true)
        viewModelScope.launch {
            when (event) {
                is DetailEvent.GetPokemonDetail -> {
                    state = when (val result = pokemonRepository.getPokemonInfo(event.name)) {
                        is Resource.Success -> {
                            val pokemon = result.data!!.toPokemon()
                            val pokemonLocal = pokemonRepository.getFavoritePokemon(event.name)
                            state.copy(
                                pokemon = pokemon.copy(isFavorite = pokemonLocal?.isFavorite ?: false),
                                isLoading = false
                            )
                        }
                        is Resource.Error -> {
                            state.copy(
                                hasError = true,
                                errorMessage = "Error"
                            )
                        }
                    }
                }

                is DetailEvent.FavoritePokemon -> {
                    val result = pokemonRepository.getFavoritePokemon(event.name)
                    if (result == null) {
                        state = state.copy(pokemon = state.pokemon.copy(isFavorite = true))
                        pokemonRepository.insertFavoritePokemon(
                            state.pokemon
                        )
                    } else {
                        state = state.copy(pokemon = state.pokemon.copy(isFavorite = !state.pokemon.isFavorite))
                        pokemonRepository.updateFavoritePokemon(state.pokemon)
                    }
                    state = state.copy(isLoading = false)
                }
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as PokedexApplication)
                val pokemonRepository = application.container.pokemonRepository
                DetailViewModel(pokemonRepository = pokemonRepository)
            }
        }
    }
}