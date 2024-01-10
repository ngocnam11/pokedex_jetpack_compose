@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.pokedex.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pokedex.R
import com.example.pokedex.screens.LoadingScreen
import com.example.pokedex.screens.detail.DetailViewModel
import com.example.pokedex.screens.detail.PokemonDetailScreen
import com.example.pokedex.screens.favourites.FavouriteListScreen
import com.example.pokedex.screens.favourites.FavouriteListViewModel
import com.example.pokedex.screens.home.HomeScreen
import com.example.pokedex.screens.pokemonlist.PokemonListScreen
import com.example.pokedex.screens.pokemonlist.PokemonListViewModel

@Composable
fun PokedexApp(
    navController: NavHostController = rememberNavController()
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val pokemonViewModel: PokemonListViewModel = viewModel(factory = PokemonListViewModel.Factory)
        val detailViewModel: DetailViewModel = viewModel(factory = DetailViewModel.Factory)
        val favouriteListViewModel: FavouriteListViewModel =
            viewModel(factory = FavouriteListViewModel.Factory)

        NavHost(navController = navController, startDestination = "home") {
            composable("home") {
                HomeScreen(
                    navController = navController
                )
            }
            composable("pokemonList") {
                PokemonListScreen(
                    pokemonViewModel = pokemonViewModel,
                    retryAction = pokemonViewModel::getPokemons,
                    navController = navController
                )
            }
            composable(
                "pokemonDetail?pokemonName={pokemonName}&pokemonColor={pokemonColor}",
                arguments = listOf(navArgument("pokemonName") { type = NavType.StringType }, navArgument("pokemonColor") { type = NavType.StringType })
            ) { backStackEntry ->
                backStackEntry.arguments?.getString("pokemonName")?.let {
                    PokemonDetailScreen(
                        onUpClick = {
                            navController.navigateUp()
                        },
                        it,
                        detailViewModel,
                        backStackEntry.arguments?.getString("pokemonColor")!!)
                }
            }
            composable("favorites") {
                FavouriteListScreen(
                    navController = navController,
                    viewModel = favouriteListViewModel,
                )
            }
            composable("moves") {
                LoadingScreen()
            }
            composable("abilities") {
                LoadingScreen()
            }
            composable("items") {
                LoadingScreen()
            }
            composable("locations") {
                LoadingScreen()
            }
            composable("types") {
                LoadingScreen()
            }
        }
    }
}

@Composable
fun PokedexTopAppBar(modifier: Modifier = Modifier, pokemonViewModel: PokemonListViewModel) {
    var searchValue by remember { mutableStateOf("") }
    var isVisibleSearchBox by remember {
        mutableStateOf(false)
    }
    val listValue = pokemonViewModel.listQuery.collectAsState().value.reversed()
    val listQuery = if (listValue.size < 3) listValue else listValue.subList(0, 3)
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_international_pok_mon_logo),
            contentDescription = "Pokemon"
        )
        Spacer(modifier = modifier.height(16.dp))
        CustomTextField(
            label = R.string.search,
            placeholder = R.string.search_pokemon,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            value = searchValue,
            onValueChange = {
                searchValue = it
                pokemonViewModel.searchPokemonList(it)
            },
            keyboardActions = KeyboardActions(onDone = {
                pokemonViewModel.savePokemonQuery(
                    searchValue
                )
            }),
            trailingIcon = {
                IconButton(onClick = { isVisibleSearchBox = !isVisibleSearchBox }) {
                    if (isVisibleSearchBox) Icon(
                        Icons.Filled.KeyboardArrowUp,
                        contentDescription = "Arrow Up"
                    ) else Icon(Icons.Filled.KeyboardArrowDown, contentDescription = "Arrow Down")
                }
            }
        )
        if (isVisibleSearchBox) Card(
            elevation = CardDefaults.cardElevation(1.dp),
            modifier = modifier.padding(horizontal = 16.dp)
        ) {
            LazyColumn(modifier = modifier) {
                items(listQuery) { query ->
                    if (query.isNotEmpty()) {
                        ListItem(
                            headlineText = { Text(text = query) },
                            modifier.clickable {
                                searchValue = query
                                pokemonViewModel.searchPokemonList(query)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CustomTextField(
    @StringRes label: Int,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    value: String,
    onValueChange: (String) -> Unit,
    trailingIcon: @Composable() (() -> Unit)?,
    @StringRes placeholder: Int,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        label = { Text(stringResource(label)) },
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        trailingIcon = trailingIcon,
        placeholder = { Text(stringResource(placeholder)) },
        shape = RoundedCornerShape(16.dp),
        maxLines = 1,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}
