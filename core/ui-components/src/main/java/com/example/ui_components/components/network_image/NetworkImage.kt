package com.example.ui_components.components.network_image

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter.State
import coil3.compose.SubcomposeAsyncImage
import coil3.compose.SubcomposeAsyncImageScope
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.ui_components.R

@Composable
fun NetworkImage(
    imageUrl: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit,
    error: @Composable (SubcomposeAsyncImageScope.(State.Error) -> Unit)? = null,
    loading: @Composable (SubcomposeAsyncImageScope.(State.Loading) -> Unit)? = null,
) {
    val isInPreview = LocalInspectionMode.current

    if (!isInPreview && imageUrl != null) {
        var successfullyLoaded by remember {
            mutableStateOf(false)
        }

        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = null,
            loading = loading,
            error = error,
        )
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            modifier = modifier,
            contentScale = contentScale,
            onSuccess = {
                successfullyLoaded = true
            }
        )
        if (!successfullyLoaded) {
            Box(
                modifier = modifier
                    .background(color = MaterialTheme.colorScheme.outlineVariant)
                    .height(150.dp)
            )
        }
    } else {
        Image(
            painter = painterResource(R.drawable.doctor2),
            contentDescription = null,
            modifier = modifier,
            contentScale = contentScale,
        )
    }
}