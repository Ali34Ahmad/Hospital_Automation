package com.example.hospital_automation.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color


data class AdditionalColor(
    val onPrimaryContainerBlueLight:Color = Color(0xFF333783),
    val onPrimaryContainerVariantLight:Color = Color(0xFFB2B3C3),
    val primaryDimmedLight:Color = Color(0xFFBCC7D2),
    val primaryContainerTransparentLight:Color = Color(0xFFDEDFF8),
    val onBackgroundVariantLight:Color = Color(0xFF99999F),
    val onBackgroundVariant2Light:Color = Color(0xFFC1C1C2),
    val onSurfaceContainerLight:Color = Color(0xFF1D1C1F),
    val onSurfaceContainerVariantLight:Color = Color(0xFF5C5863),
    val onSurfaceContainerVariant2Light:Color = Color(0xFF918E97),
)

val LocalAdditionalColor = compositionLocalOf { AdditionalColor() }

val MaterialTheme.additionalColorScheme
    @Composable
    @ReadOnlyComposable
    get() = LocalAdditionalColor.current