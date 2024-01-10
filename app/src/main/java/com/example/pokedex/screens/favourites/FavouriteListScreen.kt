package com.example.pokedex.screens.favourites

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.pokedex.screens.pokemonlist.PokemonCard
import com.example.pokedex.util.parseTypeToColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouriteListScreen(
    navController: NavHostController,
    viewModel: FavouriteListViewModel,
    modifier: Modifier = Modifier
) {
    val state = viewModel.state
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {navController.navigateUp()}) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(parseTypeToColor("ice")),
                title = { Text(text = "Favourite Pokemon") },
                )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = it)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(150.dp),
                contentPadding = PaddingValues(8.dp),
            ) {
                items(state.pokemonsFav) { pokemon ->
                    PokemonCard(
                        pokemon = pokemon,
                        navController = navController,
                        modifier = modifier
                            .padding(4.dp)
                    )
                }
            }
        }
    }
}