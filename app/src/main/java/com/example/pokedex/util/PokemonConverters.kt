package com.example.pokedex.util

import androidx.room.TypeConverter
import com.example.pokedex.network.response.Stat
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class PokemonConverters {
    @TypeConverter
    fun fromJson(json: String): List<String> {
        return Json.decodeFromString(json)
    }

    @TypeConverter
    fun toJson(types: List<String>): String {
        return Json.encodeToString(types)
    }

    @TypeConverter
    fun fromStatsJson(json: String): List<Stat> {
        return Json.decodeFromString(json)
    }

    @TypeConverter
    fun toStatsJson(stats: List<Stat>): String {
        return Json.encodeToString(stats)
    }
}