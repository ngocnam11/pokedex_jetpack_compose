package com.example.pokedex.screens.detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.pokedex.util.parseTypeToColor

@Composable
fun ItemTypeStatAb(
    modifier: Modifier = Modifier,
    text: String,
    selected: Boolean = true,
    boxColor: Color = LocalContentColor.current.copy(alpha = 0f),
    boxShape: Shape = CircleShape,
    boxHeight: Dp = 30.dp,
    boxWidth: Dp = 90.dp,
    img: Int? = null,
    imgSize: Dp = 30.dp
) {
    Box(
        modifier = modifier
            .width(boxWidth)
            .height(boxHeight)
            .clip(boxShape)
            .background(if (selected) boxColor else Color.Transparent),
        contentAlignment = Alignment.Center
    ) {
        if (img != null) {
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp)
            ) {
                Image(
                    painter = painterResource(img),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(parseTypeToColor(text)),
                    modifier = Modifier.size(imgSize)
                )
            }
        }
        Text(
            text = text.replaceFirstChar { it.titlecase() },
//            color = when {
//                selected -> MaterialTheme.colors.onSurface
//                else -> LocalContentColor.current.copy(ContentAlpha.disabled)
//            }
        )
    }
}