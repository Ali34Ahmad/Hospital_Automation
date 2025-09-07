package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Sizing(
    val default: Dp = 0.dp,
    val extraSmall1: Dp = 1.dp,
    val extraSmall2: Dp = 2.dp,
    val extraSmall4: Dp = 4.dp,
    val extraSmall8: Dp = 8.dp,
    val small1: Dp = 1.dp,
    val small2: Dp = 2.dp,
    val small6: Dp = 6.dp,
    val small8: Dp = 8.dp,
    val small12: Dp = 12.dp,
    val small16: Dp = 16.dp,
    val small18: Dp = 18.dp,
    val small20: Dp = 20.dp,
    val small24: Dp = 24.dp,
    val medium32: Dp = 32.dp,
    val circularProgressIndicatorSize36: Dp = 36.dp,
    val circularProgressIndicatorSize24: Dp = 24.dp,
    val medium40: Dp = 40.dp,
    val medium44: Dp = 44.dp,
    val medium48: Dp = 48.dp,
    val medium56: Dp = 56.dp,
    val medium64: Dp = 64.dp,
    val circularProgressIndicatorSize: Dp = 48.dp,
    val circularProgressIndicatorStrokeSizeSmall: Dp = 2.dp,
    val profileImageFailureIconSize: Dp = 110.dp,
    val illustrationImageSize: Dp = 124.dp,
    val extraLarge200: Dp = 200.dp,
    val extraLarge164: Dp = 164.dp,
    val profileImageHeight: Dp = 300.dp,
)

val LocalSizing = compositionLocalOf { Sizing() }

val MaterialTheme.sizing
    @Composable
    @ReadOnlyComposable
    get() = LocalSizing.current