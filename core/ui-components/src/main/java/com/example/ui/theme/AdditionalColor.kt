package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.graphics.ColorUtils


data class AdditionalColor(
    val primaryText: Color = Color(0xff8188F0),
    val onPrimaryContainerBlueLight:Color = Color(0xFF333783),
    val onPrimaryContainerVariantLight:Color = Color(0xFFB2B3C3),
    val primaryDimmedLight:Color = Color(0xFFBCC7D2),
    val primaryContainerTransparentLight:Color = Color(0xFFDEDFF8),
    val onBackgroundVariantLight:Color = Color(0xFF99999F),
    val onBackgroundVariant2Light:Color = Color(0xFFC1C1C2),
    val onSurfaceContainerLight:Color = Color(0xFF1D1C1F),
    val onSurfaceContainerVariantLight:Color = Color(0xFF5C5863),
    val onSurfaceContainerVariant2Light:Color = Color(0xFF918E97),
    val fileFoldLight:Color = Color(0xFFD9D9D9),
    val fileFoldDark:Color = Color(0xC4443838),
    val fileLight:Color = Color(0xFFF74B4B),
    val fileDark:Color = Color(0xFFCB2B2B),
    val warningLight: Color = Color(0xffD3BF12),
    val warningContainer: Color = Color(0x43FFEB3A),
    val primaryContainerVariant : Color = Color(0xffB2B3C3)
)

val LocalAdditionalColor = compositionLocalOf { AdditionalColor() }

val MaterialTheme.additionalColorScheme
    @Composable
    @ReadOnlyComposable
    get() = LocalAdditionalColor.current

fun getDarkThemeEquivalent(lightColor: Color): Color {
    val hsl = FloatArray(3)
    ColorUtils.colorToHSL(lightColor.toArgb(), hsl)

    // Adjust Value/Brightness (reduce by 60%)
    hsl[2] *= 2.7f

    // Adjust Saturation (increase by 15%, but clamp to 1.0)
    hsl[1] = (hsl[1] * 1.15f).coerceAtMost(1.0f)

    return Color(ColorUtils.HSLToColor(hsl))
}

@Composable
fun adjustColorByTheme(lightColor:Color):Color{
    return if(!isSystemInDarkTheme()) {
        lightColor
    }else{
        getDarkThemeEquivalent(lightColor)
    }
}