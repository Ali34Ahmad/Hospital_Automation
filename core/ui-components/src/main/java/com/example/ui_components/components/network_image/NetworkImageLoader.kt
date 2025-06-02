package com.example.ui_components.components.network_image

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.ui.theme.sizing

@Composable
fun NetworkImageLoader(
    modifier: Modifier = Modifier,
) {
    Spacer(
        modifier = modifier
            .background(MaterialTheme.colorScheme.outlineVariant),
    )
}