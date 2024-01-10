package com.example.pokedex.screens.pokemonlist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.pokedex.PokedexApplication
import com.example.pokedex.data.PokemonRepository
import com.example.pokedex.data.UserPreferencesRepository
import com.example.pokedex.data.model.Pokemon
import com.example.pokedex.screens.detail.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PokemonListViewModel(
    private val pokemonRepository: PokemonRepository,
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {
    var pokemonListState: PokemonListState by mutableStateOf(PokemonListState())
        private set

    init {
        getPokemons()
    }

    private var currentPage = 1
    private val pageSize = 10

    fun getPokemons() {
        pokemonListState = pokemonListState.copy(isLoading = true)
        viewModelScope.launch {
            val result =
                pokemonRepository.getPokemons(offset = currentPage * pageSize, limit = pageSize)
            when (result) {
                is Resource.Success -> {
                    pokemonListState = pokemonListState.copy(
                        pokemons = pokemonListState.pokemons + (result.data ?: emptyList()),
                        isLoading = false,
                        hasError = false
                    )
                    currentPage++
                    delay(1000)
                }

                is Resource.Error -> pokemonListState = pokemonListState.copy(
                    isLoading = false,
                    hasError = true
                )
            }
        }
    }

    private var isSearchStarting = true
    var isSearching = mutableStateOf(false)
    private var pokemonListTmp = listOf<Pokemon>()

    fun searchPokemonList(query: String) {
        val listToSearch = if (isSearchStarting) {
            pokemonListState.pokemons
        } else {
            pokemonListTmp
        }
        viewModelScope.launch(Dispatchers.Default) {
            if (query.isEmpty()) {
                pokemonListState = pokemonListState.copy(
                    pokemons = pokemonListTmp
                )
                isSearching.value = false
                isSearchStarting = true
                return@launch
            }
            val results = listToSearch.filter { pokemon ->
                pokemon.name.contains(
                    query.trim(),
                    ignoreCase = true
                )
            }
            if (isSearchStarting) {
                pokemonListTmp = pokemonListState.pokemons
                isSearchStarting = false
            }
            pokemonListState = pokemonListState.copy(pokemons = results)
            isSearching.value = true
        }

    }

    val listQuery = userPreferencesRepository.query.map { query ->
        query.split(", ")
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    fun savePokemonQuery(query: String) {
        if (query.isEmpty() || listQuery.value.contains(query)) return
        val listQueryTmp = listQuery.value + query
        viewModelScope.launch {
            userPreferencesRepository.savePokemonQuery(listQueryTmp.joinToString(", "))
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as PokedexApplication)
                val pokemonRepository = application.container.pokemonRepository
                PokemonListViewModel(pokemonRepository = pokemonRepository, application.userPreferencesRepository)
            }
        }
    }
}