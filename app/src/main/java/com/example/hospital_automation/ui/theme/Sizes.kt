package com.example.hospital_automation.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Sizing(
    val default: Dp = 0.dp,
    val small24: Dp = 24.dp,
    val small18: Dp = 18.dp,
    val extraLarge124: Dp = 124.dp,
)

val LocalSizing = compositionLocalOf { Sizing() }

val MaterialTheme.sizing
    @Composable
    @ReadOnlyComposable
    get() = LocalSizing.current