package com.example.pokedex.data

import com.example.pokedex.data.local.PokemonDao
import com.example.pokedex.data.model.Pokemon
import com.example.pokedex.network.PokedexApiService
import com.example.pokedex.network.response.PokemonResponse
import com.example.pokedex.screens.detail.Resource
import com.example.pokedex.util.toPokemon
import com.example.pokedex.util.toPokemonEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.io.IOException

interface PokemonRepository {
    suspend fun getPokemons(offset: Int, limit: Int): Resource<List<Pokemon>>

    suspend fun getPokemonInfo(name: String): Resource<PokemonResponse>

    //Room
    fun getFavoritePokemons(): Flow<List<Pokemon>>

    suspend fun getFavoritePokemon(name: String): Pokemon?

    suspend fun insertFavoritePokemon(pokemon: Pokemon)

    suspend fun deleteFavoritePokemon(pokemon: Pokemon)

    suspend fun updateFavoritePokemon(pokemon: Pokemon)
}

class PokemonRepositoryImpl(
    private val pokedexApiService: PokedexApiService,
    private val pokemonDao: PokemonDao
) : PokemonRepository {
    override suspend fun getPokemons(offset: Int, limit: Int): Resource<List<Pokemon>> {
        return try {
            val pokemons = pokedexApiService.getPokemons(offset = offset, limit = limit)
            val result = pokemons.results.map {
                pokedexApiService.getPokemonInfo(it.name).toPokemon()
            }
            Resource.Success(result)
        } catch (e: IOException) {
            Resource.Error("Error")
        } catch (e: HttpException) {
            Resource.Error("Something went wrong")
        }

    }

    override suspend fun getPokemonInfo(name: String): Resource<PokemonResponse> {
        return try {
            Resource.Success(pokedexApiService.getPokemonInfo(name))
        } catch (e: IOException) {
            Resource.Error("Error")
        } catch (e: HttpException) {
            Resource.Error("Something went wrong")
        }
    }

    override fun getFavoritePokemons(): Flow<List<Pokemon>> {
        return pokemonDao.getPokemons().map { pokemons ->
            pokemons.map { it.toPokemon() }
        }
    }

    override suspend fun getFavoritePokemon(name: String): Pokemon? {
        return pokemonDao.getPokemonFavorite(name)?.toPokemon()
    }

    override suspend fun insertFavoritePokemon(pokemon: Pokemon) {
        pokemonDao.insertPokemonFavorite(pokemon.toPokemonEntity())
    }

    override suspend fun deleteFavoritePokemon(pokemon: Pokemon) {
        pokemonDao.deletePokemonFavorite(pokemon.toPokemonEntity())
    }

    override suspend fun updateFavoritePokemon(pokemon: Pokemon) {
        pokemonDao.updatePokemonFavorite(pokemon.toPokemonEntity())
    }
}