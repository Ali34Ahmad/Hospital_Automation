package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.ProvidedValue
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.graphics.ColorUtils

// Define Light Theme Additional Colors as top-level constants
val onPrimaryContainerBlueLight = Color(0xFF333783)
val onPrimaryContainerVariantLight = Color(0xFFB2B3C3)
val primaryDimmedLight = Color(0xFFBCC7D2)
val primaryContainerTransparentLight = Color(0xFFDEDFF8)
val onBackgroundVariantLight = Color(0xFF74747A)
val onBackgroundVariant2Light = Color(0xFFC1C1C2)
val onSurfaceContainerLight = Color(0xFF1D1C1F)
val onSurfaceContainerVariantLight = Color(0xFF5C5863)
val onSurfaceContainerVariant2Light = Color(0xFF918E97)
val fileFoldLight = Color(0xFFD9D9D9)
val fileLight = Color(0xFFF74B4B)
val greenLight = Color(0xFF33A439)
val warningLight = Color(0xFF7E710A)
val warningContainerLight = Color(0xFFFFF8E1)
val surfaceErrorLight = Color(0xFFFFEAEA) // Corrected potential typo from 0xffFFEAEA
val onSurfaceErrorLight = Color(0xFFB71C1C) // Corrected potential typo from 0xffB71C1C

// Define Dark Theme Additional Colors as top-level constants
val onPrimaryContainerBlueDark = Color(0xFFDEDFFA)
val onPrimaryContainerVariantDark = Color(0xFF333783)
val primaryDimmedDark = Color(0xFFBCC7D2) // Note: Same as light in your example
val primaryContainerTransparentDark = Color(0xFFDEDFF8) // Note: Same as light in your example
val onBackgroundVariantDark = Color(0xFFC1C1C2)
val onBackgroundVariant2Dark = Color(0xFF74747A)
val onSurfaceContainerDark = Color(0xFF1D1C1F) // Note: Same as light in your example
val onSurfaceContainerVariantDark = Color(0xFF5C5863) // Note: Same as light in your example
val onSurfaceContainerVariant2Dark = Color(0xFF918E97) // Note: Same as light in your example
val fileFoldDark = Color(0xC4443838)
val fileDark = Color(0xFFCB2B2B)
val greenDark = Color(0xFF33A439) // Note: Same as light in your example
val warningDark = Color(0xFFD0AE1C)
val warningContainerDark = Color(0xFF403800)
val surfaceErrorDark = Color(0xFFFFEAEA) // Note: Same as light in your example, corrected typo
val onSurfaceErrorDark = Color(0xFFB71C1C) // Note: Same as light in your example, corrected typo


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
    val fileFold: Color
    val file: Color
    val green: Color
    val warning: Color
    val warningContainer: Color
    val surfaceError: Color
    val onSurfaceError: Color
}

data class AdditionalColorLight(
    override val onPrimaryContainerBlue: Color = onPrimaryContainerBlueLight,
    override val onPrimaryContainerVariant: Color = onPrimaryContainerVariantLight,
    override val primaryDimmed: Color = primaryDimmedLight,
    override val primaryContainerTransparent: Color = primaryContainerTransparentLight,
    override val onBackgroundVariant: Color = onBackgroundVariantLight,
    override val onBackgroundVariant2: Color = onBackgroundVariant2Light,
    override val onSurfaceContainer: Color = onSurfaceContainerLight,
    override val onSurfaceContainerVariant: Color = onSurfaceContainerVariantLight,
    override val onSurfaceContainerVariant2: Color = onSurfaceContainerVariant2Light,
    override val fileFold: Color = fileFoldLight,
    override val file: Color = fileLight,
    override val green: Color = greenLight,
    override val warning: Color = warningLight,
    override val warningContainer: Color = warningContainerLight,
    override val surfaceError: Color = surfaceErrorLight,
    override val onSurfaceError: Color = onSurfaceErrorLight,
) : AdditionalColors

data class AdditionalColorDark(
    override val onPrimaryContainerBlue: Color = onPrimaryContainerBlueDark,
    override val onPrimaryContainerVariant: Color = onPrimaryContainerVariantDark,
    override val primaryDimmed: Color = primaryDimmedDark,
    override val primaryContainerTransparent: Color = primaryContainerTransparentDark,
    override val onBackgroundVariant: Color = onBackgroundVariantDark,
    override val onBackgroundVariant2: Color = onBackgroundVariant2Dark,
    override val onSurfaceContainer: Color = onSurfaceContainerDark,
    override val onSurfaceContainerVariant: Color = onSurfaceContainerVariantDark,
    override val onSurfaceContainerVariant2: Color = onSurfaceContainerVariant2Dark,
    override val fileFold: Color = fileFoldDark,
    override val file: Color = fileDark,
    override val green: Color = greenDark,
    override val warning: Color = warningDark,
    override val warningContainer: Color = warningContainerDark,
    override val surfaceError: Color = surfaceErrorDark,
    override val onSurfaceError: Color = onSurfaceErrorDark,
) : AdditionalColors


val LocalAdditionalColors: ProvidableCompositionLocal<AdditionalColors> =
    staticCompositionLocalOf {
        AdditionalColorLight()
    }

val MaterialTheme.additionalColorScheme
    @Composable
    @ReadOnlyComposable
    get() = LocalAdditionalColors.current

