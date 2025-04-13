package com.example.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


data class AdditionalShapes(
    val default: Shape = RectangleShape,
    val small12: Shape = RoundedCornerShape(12.dp),
)

val LocalAdditionalShapes = compositionLocalOf { AdditionalShapes() }

val MaterialTheme.additionalShapes
    @Composable
    @ReadOnlyComposable
    get() = LocalAdditionalShapes.current