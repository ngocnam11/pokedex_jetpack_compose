package com.example.pokedex.screens.detail.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.pokedex.R
import com.example.pokedex.data.model.Pokemon
import com.example.pokedex.ui.theme.IceType
import kotlinx.coroutines.launch

sealed class Page(val title: String) {
    object About : Page("About")
    object BaseStats : Page("Base Stats")
    object Moves : Page("Moves")
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun InfoTabs(
    modifier: Modifier = Modifier,
    pokemonInfo: Pokemon,
    pagerState: PagerState,
    backgroundColor: Color,
    pages: List<Page>
) {
    val scope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        indicator = { /* Set empty indicator */ },
        divider = { /* Set empty divider */ },
        modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        pages.forEachIndexed { index, page ->
            ItemTypeStatAb(
                text = page.title,
                selected = index == pagerState.currentPage,
                boxColor = backgroundColor,
                modifier = modifier
                    .height(40.dp)
                    .clip(CircleShape)
                    .clickable {
                        scope.launch {
                            pagerState.scrollToPage(index)
                        }
                    }
            )
        }
    }
    HorizontalPager(
        pageCount = pages.size,
        state = pagerState
    ) {
        val page = pages[pagerState.currentPage]

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (page) {
                is Page.About -> {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(
                            space = 64.dp,
                            alignment = Alignment.CenterHorizontally
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        CharacteristicsAbout(
                            painter = R.drawable.ic_balance,
                            value = pokemonInfo.weight,
                            dataUnit = "Kg"
                        )
                        Divider(
                            modifier = Modifier
                                .height(50.dp)
                                .width(2.dp)
                        )
                        CharacteristicsAbout(
                            painter = R.drawable.ic_height,
                            value = pokemonInfo.height,
                            dataUnit = "m"
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = stringResource(R.string.abilities),
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth()
                    ) {
                        items(pokemonInfo.abilities) { ability ->
                            ItemTypeStatAb(
                                text = ability,
                                boxColor = IceType,
                                boxWidth = 130.dp,
                                boxHeight = 40.dp,
                                boxShape = RoundedCornerShape(16.dp)
                            )
                        }
                    }
                }

                is Page.BaseStats -> {
                    BaseStats(
                        pokemonInfo = pokemonInfo,
                        modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth()
                    )
                }

                is Page.Moves -> {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = modifier.padding(horizontal = 16.dp)
                    ) {
                        val moves = if (pokemonInfo.moves.size >= 10) {
                            pokemonInfo.moves.subList(0, 10)
                        } else {
                            pokemonInfo.moves
                        }
                        items(moves) {
                            ItemTypeStatAb(
                                text = it,
                                boxHeight = 35.dp,
                                boxColor = IceType
                            )
                        }
                    }
                }
            }
        }
    }
}