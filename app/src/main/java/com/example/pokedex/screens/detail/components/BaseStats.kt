package com.example.pokedex.screens.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.pokedex.data.model.Pokemon
import com.example.pokedex.util.parseStatToAbbr
import com.example.pokedex.util.parseStatToColor

@Composable
fun BaseStats(
    modifier: Modifier = Modifier,
    pokemonInfo: Pokemon,
) {
    val maxStatValue = pokemonInfo.stats.maxOf { it.baseStat }
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        pokemonInfo.stats.forEach { stat ->
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = parseStatToAbbr(stat),
                )
                Text(
                    text = stat.baseStat.toString(),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .width(80.dp)
                        .graphicsLayer { translationX = 60.dp.toPx() }
                )
                StatItem(
                    statValue = stat.baseStat,
                    maxStatValue = maxStatValue,
                    statColor = parseStatToColor(stat)
                )
            }
        }
    }
}