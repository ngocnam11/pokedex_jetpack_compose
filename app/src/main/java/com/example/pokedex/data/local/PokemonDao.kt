package com.example.pokedex.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonFavorite(pokemon: PokemonEntity)

    @Delete
    suspend fun deletePokemonFavorite(pokemon: PokemonEntity)

    @Update
    suspend fun updatePokemonFavorite(pokemon: PokemonEntity)

    @Query("SELECT * FROM pokemons WHERE name = :name")
    suspend fun getPokemonFavorite(name: String): PokemonEntity?

    @Query("SELECT * FROM pokemons WHERE isSelectedFavorite = 1")
    fun getPokemons(): Flow<List<PokemonEntity>>
}