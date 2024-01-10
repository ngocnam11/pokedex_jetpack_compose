package com.example.pokedex.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.pokedex.R

@Composable
fun PokemonNewsItem(
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = modifier.padding(end = 16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.pokemon_rumble),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(id = R.string.date),
                fontSize = 18.sp,
                fontWeight = FontWeight.W300
            )
        }
        AsyncImage(
            model = "https://www.pokemon.com/static-assets/content-assets/cms2/img/video-games/video-games/pokemon_rumble_rush/pokemon-rumble-rush-169.jpg",
            contentDescription = "Pokemon News Item",
            contentScale = ContentScale.Crop,
            modifier = modifier.size(150.dp, 80.dp).clip(RoundedCornerShape(16.dp))
        )
    }
}
