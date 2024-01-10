package com.example.pokedex.util

import com.example.pokedex.data.local.PokemonEntity
import com.example.pokedex.data.model.MoveEntry
import com.example.pokedex.data.model.Pokemon
import com.example.pokedex.data.model.TypeEntry
import com.example.pokedex.network.response.MoveResponse
import com.example.pokedex.network.response.PokemonResponse
import com.example.pokedex.network.response.ResultX

fun PokemonResponse.toPokemon(): Pokemon {
    return Pokemon(
        id = id,
        name = name,
        imageUrl = sprites.other.dreamWorld.frontDefault,
        height = height,
        types = types.map { it.type.name },
        weight = weight,
        moves = moves.map { it.move.name },
        stats = stats,
        abilities = abilities.map { it.ability.name },
    )
}

fun ResultX.toTypeEntry(): TypeEntry {
    return TypeEntry(
        name = name,
        color = parseTypeToColor(name)
    )
}

fun MoveResponse.toMoveEntry(): MoveEntry {
    return MoveEntry(
        name = name,
        type = type.name,
        id = id
    )
}

fun Pokemon.toPokemonEntity(): PokemonEntity {
    return PokemonEntity(
        name = name,
        imageUrl = imageUrl,
        height = height,
        types = types,
        weight = weight,
        moves = moves,
        stats = stats,
        abilities = abilities,
        id = id,
        isSelectedFavorite = isFavorite
    )
}

fun PokemonEntity.toPokemon(): Pokemon {
    return Pokemon(
        name = name,
        imageUrl = imageUrl,
        height = height,
        types = types,
        weight = weight,
        moves = moves,
        stats = stats,
        abilities = abilities,
        id = id,
        isFavorite = isSelectedFavorite
    )
}