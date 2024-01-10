package com.example.pokedex

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.pokedex.data.AppContainer
import com.example.pokedex.data.DefaultAppContainer
import com.example.pokedex.data.UserPreferencesRepository

private const val POKEMONS_PREFERENCES_NAME = "pokemons_preferences"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = POKEMONS_PREFERENCES_NAME
)

class PokedexApplication : Application() {
    lateinit var container: AppContainer
    lateinit var userPreferencesRepository: UserPreferencesRepository

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(applicationContext)
        userPreferencesRepository = UserPreferencesRepository(dataStore)
    }
}