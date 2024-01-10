package com.example.pokedex.util

import androidx.compose.ui.graphics.Color
import com.example.pokedex.network.response.Stat
import com.example.pokedex.ui.theme.*

fun parseTypeToColor(type: String): Color {
    return when (type.lowercase()) {
        "normal" -> NormalType
        "fire" -> FireType
        "water" -> WaterType
        "electric" -> ElectricType
        "grass" -> GrassType
        "ice" -> IceType
        "fighting" -> FightingType
        "poison" -> PoisonType
        "ground" -> GroundType
        "flying" -> FlyingType
        "psychic" -> PsychicType
        "bug" -> BugType
        "rock" -> RockType
        "ghost" -> GhostType
        "dragon" -> DragonType
        "dark" -> DarkType
        "steel" -> SteelType
        "fairy" -> FairyType
        else -> Color.Black
    }
}

fun parseStatToAbbr(stat: Stat): String {
    return when (stat.stat.name.lowercase()) {
        "hp" -> "HP"
        "attack" -> "ATK"
        "defense" -> "DEF"
        "special-attack" -> "SPATK"
        "special-defense" -> "SPDEF"
        "speed" -> "SPD"
        else -> ""
    }
}

fun parseStatToColor(stat: Stat): Color {
    return when(stat.stat.name.lowercase()) {
        "hp" -> HPColor
        "attack" -> AtkColor
        "defense" -> DefColor
        "special-attack" -> SpAtkColor
        "special-defense" -> SpDefColor
        "speed" -> SpdColor
        else -> Color.White
    }
}