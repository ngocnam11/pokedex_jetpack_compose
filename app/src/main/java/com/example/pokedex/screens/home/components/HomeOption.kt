package com.example.pokedex.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.pokedex.R

@Composable
fun HomeOption(
    title: String,
    color: Color,
    navController: NavHostController,
    route: String,
    modifier: Modifier = Modifier,
) {
    Card(
        colors = CardDefaults.cardColors(color),
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .padding(8.dp)
            .height(80.dp)
            .clickable { navController.navigate(route) },
    ) {
        Box(
            contentAlignment = Alignment.CenterStart
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_pokeball),
                contentDescription = "",
                modifier = Modifier
                    .size(80.dp)
                    .offset(110.dp, 10.dp),
                alpha = 0.5f,
                colorFilter = ColorFilter.tint(Color.White)
            )
            Text(text = title, modifier = modifier.padding(16.dp), fontSize = 22.sp,
                fontWeight = FontWeight.Medium, color = Color.White)
        }
    }
}
//
//@Preview
//@Composable
//fun HomeOptionPreview() {
//    HomeOption(title = "Pokedex", color = Color.Cyan)
//}

