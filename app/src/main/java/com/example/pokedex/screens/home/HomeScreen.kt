package com.example.pokedex.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.pokedex.R
import com.example.pokedex.screens.home.components.HomeOption
import com.example.pokedex.screens.home.components.PokemonNewsItem
import com.example.pokedex.ui.theme.DarkType
import com.example.pokedex.ui.theme.ElectricType
import com.example.pokedex.ui.theme.FightingType
import com.example.pokedex.ui.theme.IceType
import com.example.pokedex.ui.theme.PoisonType
import com.example.pokedex.ui.theme.WaterType

sealed class HomeOpt(val title: String, val color: Color, val route: String) {
    object Pokedex : HomeOpt("Pokedex", IceType, "pokemonList")
    object Moves : HomeOpt("Moves", FightingType, "moves")
    object Abilities : HomeOpt("Abilities", WaterType, "abilities")
    object Items : HomeOpt("Items", ElectricType, "items")
    object Locations : HomeOpt("Locations", PoisonType, "locations")
    object TypeCharts : HomeOpt("Type Charts", DarkType, "types")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("favorites") },
                containerColor = WaterType
            ) {
                Icon(Icons.Filled.Favorite, tint = Color.Red, contentDescription = "")
            }
        }
    ) {
        Column(
            modifier = modifier
                .background(Color.LightGray)
                .verticalScroll(rememberScrollState())
                .padding(paddingValues = it)
        ) {
            val homeOptList = listOf(
                HomeOpt.Pokedex,
                HomeOpt.Moves,
                HomeOpt.Abilities,
                HomeOpt.Items,
                HomeOpt.Locations,
                HomeOpt.TypeCharts
            )
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .background(
                        Color.Red,
                        RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp)
                    )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_pokeball),
                    contentDescription = "",
                    modifier = modifier
                        .size(300.dp)
                        .offset((190).dp, (-80).dp),
                    alpha = 0.2f
                )
                Column(modifier = modifier.align(Alignment.BottomStart)) {
                    Text(
                        text = stringResource(id = R.string.what_pokemon),
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        lineHeight = 40.sp,
                        modifier = modifier.padding(horizontal = 16.dp)
                    )
                    Button(
                        onClick = { navController.navigate("pokemonList") },
                        colors = ButtonDefaults.buttonColors(Color.White, Color.Black),
                        contentPadding = PaddingValues(16.dp),
                        modifier = modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    ) {
                        Icon(
                            Icons.Outlined.Search,
                            contentDescription = "Search",
                            modifier = modifier.size(32.dp)
                        )
                        Spacer(modifier = modifier.width(8.dp))
                        Text(
                            text = stringResource(id = R.string.placeholder_search),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Gray
                        )
                    }
                }
            }
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = modifier
                    .background(
                        color = Color.White,
                        RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp)
                    )
                    .padding(8.dp)
                    .heightIn(max = 500.dp),

                ) {
                items(homeOptList) { homeOpt ->
                    HomeOption(
                        title = homeOpt.title,
                        color = homeOpt.color,
                        navController,
                        homeOpt.route
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.news),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                TextButton(onClick = { /*TODO*/ }) {
                    Text(text = stringResource(id = R.string.view_all), fontSize = 20.sp)
                }
            }
            LazyColumn(
                modifier = modifier.heightIn(max = 500.dp)
            ) {
                items(count = 3) {
                    PokemonNewsItem()
                    Divider()
                }
            }

        }
    }
}