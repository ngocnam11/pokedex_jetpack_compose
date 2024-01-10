package com.example.pokedex.screens.detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CharacteristicsAbout(
    modifier: Modifier = Modifier,
    painter: Int,
    value: Int,
    dataUnit: String,
    imageSize: Dp = 60.dp,
    textFontSize: TextUnit = 25.sp
) {
    val fValue = (value / 10f)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
    ) {
        Image(
            painter = painterResource(painter),
            contentDescription = null,
            colorFilter = ColorFilter.tint(Color.DarkGray),
            modifier = Modifier.size(imageSize)
        )
        Text(
            text = "$fValue $dataUnit",
            fontSize = textFontSize
        )
    }
}