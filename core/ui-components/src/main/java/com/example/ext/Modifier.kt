package com.example.ext

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.input.pointer.pointerInput

fun Modifier.disabled(alpha: Float = 0.4f): Modifier {
    val disableModifier = Modifier.alpha(alpha)
        .pointerInput(Unit){}
    return this then disableModifier
}