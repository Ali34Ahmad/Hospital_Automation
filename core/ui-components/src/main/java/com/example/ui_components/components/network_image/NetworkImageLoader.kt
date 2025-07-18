package com.example.ui_components.components.network_image

import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.ext.shimmerEffect

@Composable
fun NetworkImageLoader(
    modifier: Modifier = Modifier,
) {
    Spacer(
        modifier = modifier
            .shimmerEffect(),
    )
}