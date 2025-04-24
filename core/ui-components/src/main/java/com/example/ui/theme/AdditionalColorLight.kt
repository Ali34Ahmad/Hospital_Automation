package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.graphics.ColorUtils

interface AdditionalColors {
    val onPrimaryContainerBlue: Color
    val onPrimaryContainerVariant: Color
    val primaryDimmed: Color
    val primaryContainerTransparent: Color
    val onBackgroundVariant: Color
    val onBackgroundVariant2: Color
    val onSurfaceContainer: Color
    val onSurfaceContainerVariant: Color
    val onSurfaceContainerVariant2: Color
    val fileFold:Color
    val file:Color
    val green: Color
}

data class AdditionalColorLight(
    override val onPrimaryContainerBlue: Color = Color(0xFF333783),
    override val onPrimaryContainerVariant: Color = Color(0xFFB2B3C3),
    override val primaryDimmed: Color = Color(0xFFBCC7D2),
    override val primaryContainerTransparent: Color = Color(0xFFDEDFF8),
    override val onBackgroundVariant: Color = Color(0xFF74747A),
    override val onBackgroundVariant2: Color = Color(0xFFC1C1C2),
    override val onSurfaceContainer: Color = Color(0xFF1D1C1F),
    override val onSurfaceContainerVariant: Color = Color(0xFF5C5863),
    override val onSurfaceContainerVariant2: Color = Color(0xFF918E97),
    override val fileFold:Color = Color(0xFFD9D9D9),
    override val file:Color = Color(0xFFF74B4B),
    override val green:Color = Color(0xFF33A439),
) : AdditionalColors

data class AdditionalColorDark(
    override val onPrimaryContainerBlue: Color = Color(0xFFDEDFFA),
    override val onPrimaryContainerVariant: Color = Color(0xFF333783),
    override val primaryDimmed: Color = Color(0xFFBCC7D2),
    override val primaryContainerTransparent: Color = Color(0xFFDEDFF8),
    override val onBackgroundVariant: Color = Color(0xFFC1C1C2),
    override val onBackgroundVariant2: Color = Color(0xFF74747A),
    override val onSurfaceContainer: Color = Color(0xFF1D1C1F),
    override val onSurfaceContainerVariant: Color = Color(0xFF5C5863),
    override val onSurfaceContainerVariant2: Color = Color(0xFF918E97),
    override val fileFold:Color = Color(0xC4443838),
    override val file:Color = Color(0xFFCB2B2B),
    override val green:Color = Color(0xFF33A439),
) : AdditionalColors




val LocalAdditionalColors = compositionLocalOf<AdditionalColors> {
    error("No AdditionalColors provided")
}

val MaterialTheme.additionalColorScheme
    @Composable
    @ReadOnlyComposable
    get() = LocalAdditionalColors.current

fun getDarkThemeEquivalent(lightColor: Color): Color {
    val hsl = FloatArray(3)
    ColorUtils.colorToHSL(lightColor.toArgb(), hsl)

    // Adjust Value/Brightness (reduce by 60%)
    hsl[2] *= 2.7f

    // Adjust Saturation (increase by 15%, but clamp to 1.0)
    hsl[1] = (hsl[1] * 1.15f).coerceAtMost(1.0f)

    return Color(ColorUtils.HSLToColor(hsl))
}
