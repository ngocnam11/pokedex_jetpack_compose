package com.example.pokedex.data

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class UserPreferencesRepository(
    private val dataStore: DataStore<Preferences>
) {
    private companion object {
        val POKEMON_QUERY = stringPreferencesKey("pokemon_query")
        const val TAG = "UserPreferencesRepo"
    }

    val query: Flow<String> = dataStore.data.catch {
        if (it is IOException) {
            Log.e(TAG, "Error reading preferences.", it)
            emit(emptyPreferences())
        } else {
            throw it
        }
    }.map {preferences ->
        preferences[POKEMON_QUERY] ?: ""
    }

    suspend fun savePokemonQuery(query: String) {
        dataStore.edit { preferences ->
            preferences[POKEMON_QUERY] = query
        }
    }
}