package com.example.ui_components.components.network_image

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import coil3.ImageLoader
import coil3.compose.AsyncImagePainter.State
import coil3.compose.SubcomposeAsyncImage
import coil3.compose.SubcomposeAsyncImageScope
import coil3.network.okhttp.OkHttpNetworkFetcherFactory
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.ui_components.R

@Composable
fun NetworkImage(
    model: Any?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit,
    errorCompose: @Composable (SubcomposeAsyncImageScope.(State.Error) -> Unit)? = null,
    loading: @Composable (SubcomposeAsyncImageScope.(State.Loading) -> Unit)? = null,
) {
    val isInPreview = LocalInspectionMode.current

    val context = LocalContext.current


    if (!isInPreview) {
        val imageLoader = remember(model) {
            ImageLoader.Builder(context)
                .components {
                    add(OkHttpNetworkFetcherFactory())
                }
                .build()
        }
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(context)
                .data(model)
                .crossfade(true)
                .build(),
            contentDescription = null,
            imageLoader = imageLoader,
            loading = loading,
            error = { errorState ->
                Log.e(
                    "ImageLoadError",
                    "Error loading image: ${errorState.result.throwable.message}",
                    errorState.result.throwable
                )
                errorCompose?.let {
                    errorCompose(errorState)
                }
            },
            contentScale = contentScale,
            modifier = modifier,
        )
    } else {
        Image(
            painter = painterResource(R.drawable.doctor2),
            contentDescription = null,
            modifier = modifier,
            contentScale = contentScale,
        )
    }
}