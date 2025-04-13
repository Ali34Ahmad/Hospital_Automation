package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


data class AdditionalTypography(
    val labelSmallest: TextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 8.sp,
        lineHeight = 8.sp,
        letterSpacing = 0.5.sp
    ),
)

val LocalAdditionalTypography = compositionLocalOf { AdditionalTypography() }

val MaterialTheme.additionalTypography
    @Composable
    @ReadOnlyComposable
    get() = LocalAdditionalTypography.current