package com.example.pokedex.data

import android.content.Context
import com.example.pokedex.data.local.PokemonDatabase
import com.example.pokedex.network.PokedexApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val pokemonRepository: PokemonRepository
}

class DefaultAppContainer(context: Context) : AppContainer {
    private val baseUrl = "https://pokeapi.co/api/v2/"

    private val json = Json { ignoreUnknownKeys = true }

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: PokedexApiService by lazy {
        retrofit.create(PokedexApiService::class.java)
    }

    override val pokemonRepository: PokemonRepository by lazy {
        PokemonRepositoryImpl(retrofitService, PokemonDatabase.getDatabase(context).pokemonDao())
    }
}