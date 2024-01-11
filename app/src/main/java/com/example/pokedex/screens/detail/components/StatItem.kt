package com.example.pokedex.screens.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun StatItem(
    statValue: Int,
    statColor: Color,
    height: Dp = 16.dp,
) {
    val curPercent = statValue / 300f
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 130.dp)
            .height(height)
            .clip(CircleShape)
            .background(Color.LightGray)
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(curPercent)
                .clip(CircleShape)
                .background(statColor)
        )
    }
}