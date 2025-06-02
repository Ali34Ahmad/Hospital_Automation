package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Spacing(
    val default: Dp =0.dp,
    val extraSmall2: Dp =2.dp,
    val extraSmall4: Dp =4.dp,
    val small8: Dp =8.dp,
    val small10: Dp =10.dp,
    val small12: Dp =12.dp,
    val small13: Dp =13.dp,
    val medium16: Dp =16.dp,
    val large24: Dp =24.dp,
    val large32: Dp =32.dp,
    val large36: Dp =36.dp,
    val extraLarge64: Dp =64.dp,
    val extraLarge72: Dp =72.dp,
    val extraLarge94: Dp =94.dp,
    val extraLarge104: Dp =104.dp,
)

val LocalSpacing =compositionLocalOf { Spacing() }

val MaterialTheme.spacing
@Composable
@ReadOnlyComposable
get() = LocalSpacing.current