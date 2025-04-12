package com.example.ui_components.components.network_image

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import coil3.compose.AsyncImage
import com.example.ui_components.R

@Composable
fun NetworkImage(
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    val isInPreview = LocalInspectionMode.current

    if (!isInPreview) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            modifier = modifier,
            contentScale = ContentScale.Fit,
            placeholder = painterResource(R.drawable.doctor1),
        )
    } else {
        Image(
            painter = painterResource(R.drawable.doctor2),
            contentDescription = null,
            modifier = modifier,
            contentScale = ContentScale.Fit,
        )
    }
}