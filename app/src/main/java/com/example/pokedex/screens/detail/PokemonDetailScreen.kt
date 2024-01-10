@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.pokedex.screens.detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokedex.R
import com.example.pokedex.components.SvgImage
import com.example.pokedex.screens.LoadingScreen
import com.example.pokedex.screens.detail.components.InfoTabs
import com.example.pokedex.screens.detail.components.Page
import com.example.pokedex.util.parseTypeToColor

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PokemonDetailScreen(
    onUpClick: () -> Unit,
    pokemonName: String,
    detailViewModel: DetailViewModel,
    color: String,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(key1 = true, block = {
        detailViewModel.onEvent(DetailEvent.GetPokemonDetail(pokemonName))
    })
    val state = detailViewModel.state

    if (state.isLoading) LoadingScreen()
    else Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = onUpClick) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(parseTypeToColor(color)),
                title = { Text(pokemonName.replaceFirstChar { it.titlecase() }) },
                actions = {
                    IconButton(onClick = { detailViewModel.onEvent(DetailEvent.FavoritePokemon(state.pokemon.name)) }) {
                        Icon(
                            imageVector = if (state.pokemon.isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                            tint = if (state.pokemon.isFavorite) Color.Red else Color.Blue,
                            contentDescription = "Add to Favorites",
                            modifier = modifier.size(32.dp)
                        )
                    }
                },
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Spacer(modifier = Modifier.padding(innerPadding))
            Box(
                modifier = Modifier
                    .background(
                        color = parseTypeToColor(color),
                        shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp)
                    )
                    .fillMaxWidth()
                    .height(300.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_pokeball),
                    contentDescription = "",
                    modifier = Modifier
                        .size(200.dp)
                        .align(Alignment.Center),
                    alpha = 0.5f,
                    colorFilter = ColorFilter.tint(Color.White)
                )
                SvgImage(
                    imgUrl = state.pokemon.imageUrl,
                    modifier = modifier
                        .size(230.dp)
                        .align(Alignment.Center)
                )
            }
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            ) {
                items(state.pokemon.types) { type ->
                    SuggestionChip(
                        onClick = { /*TODO*/ },
                        label = {
                            Text(
                                type.replaceFirstChar { it.titlecase() },
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        },
                        shape = RoundedCornerShape(16.dp),
                        colors = SuggestionChipDefaults.suggestionChipColors(
                            parseTypeToColor(
                                type = type
                            )
                        )
                    )
                    Spacer(modifier = modifier.width(8.dp))
                }
            }
            InfoTabs(
                pokemonInfo = state.pokemon,
                pagerState = PagerState(0),
                backgroundColor = Color.LightGray,
                pages = listOf(
                    Page.About, Page.BaseStats, Page.Moves
                )
            )
        }
    }
}
