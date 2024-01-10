package com.example.pokedex.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import com.example.pokedex.R

@Composable
fun SvgImage(
    modifier: Modifier = Modifier,
    imgUrl: String
) {
    val context = LocalContext.current
    val painter = rememberAsyncImagePainter(
        model = imgUrl,
        imageLoader = ImageLoader.Builder(context)
            .components {
                add(SvgDecoder.Factory())
            }
            .placeholder(R.drawable.loading_img) // sửa lại placeholder
            .build()
    )
    Image(
        painter = painter,
        contentDescription = "Pokemon image",
        contentScale = ContentScale.Fit,
        modifier = modifier
    )
}