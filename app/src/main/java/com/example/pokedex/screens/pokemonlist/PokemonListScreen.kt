@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.pokedex.screens.pokemonlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.pokedex.R
import com.example.pokedex.components.SvgImage
import com.example.pokedex.data.model.Pokemon
import com.example.pokedex.screens.ErrorScreen
import com.example.pokedex.ui.PokedexTopAppBar
import com.example.pokedex.util.parseTypeToColor

@Composable
fun PokemonListScreen(
    pokemonViewModel: PokemonListViewModel,
    retryAction: () -> Unit,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val state = pokemonViewModel.pokemonListState
    Column(modifier = modifier) {
        PokedexTopAppBar(pokemonViewModel = pokemonViewModel)
        if (state.hasError) ErrorScreen(retryAction = retryAction)
        PokemonGrid(navController = navController, pokemonViewModel = pokemonViewModel)
        if (state.isLoading) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                CircularProgressIndicator(color = Color.Blue)
            }
        }
    }
}

@Composable
fun PokemonGrid(
    navController: NavHostController,
    pokemonViewModel: PokemonListViewModel,
    modifier: Modifier = Modifier
) {
    val state = pokemonViewModel.pokemonListState
    val isSearching by remember {
        pokemonViewModel.isSearching
    }

    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        contentPadding = PaddingValues(8.dp),
    ) {
        itemsIndexed(state.pokemons) { index, pokemon ->
            val size = state.pokemons.size
            if (index >= size - 1 && !state.isLoading && !isSearching) {
                pokemonViewModel.getPokemons()
            }
            PokemonCard(
                pokemon = pokemon,
                navController = navController,
                modifier = modifier
                    .padding(4.dp)
            )
        }
    }
}

@Composable
fun PokemonCard(
    pokemon: Pokemon,
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    Card(
        colors = CardDefaults.cardColors(parseTypeToColor(pokemon.types.first()).copy(alpha = 0.7f)),
        modifier = modifier
            .clickable {
                navController.navigate("pokemonDetail?pokemonName=${pokemon.name}&pokemonColor=${pokemon.types[0]}")
            },
    ) {
        val pokeType = when (pokemon.types.size) {
            1 -> pokemon.types.subList(0, 1)
            else -> pokemon.types.subList(0, 2)
        }
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.Start,
            modifier = modifier.padding(horizontal = 8.dp)
        ) {
            Text(
                text = "#${pokemon.id.toString()}",
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    color = Color.DarkGray
                )
            )
            Text(
                text = pokemon.name.replaceFirstChar { it.titlecase() },
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
            )
            Box(modifier = modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.ic_pokeball),
                    contentDescription = "",
                    modifier = Modifier
                        .size(120.dp)
                        .align(Alignment.Center),
                    alpha = 0.5f,
                    colorFilter = ColorFilter.tint(Color.White)
                )
                SvgImage(
                    imgUrl = pokemon.imageUrl,
                    modifier = modifier
                        .size(120.dp)
                        .align(Alignment.Center)
                )
            }
            LazyRow(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier.fillMaxWidth()
            ) {
                items(items = pokeType) { type ->
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
                                type
                            )
                        )
                    )
                }
            }
        }
    }
}