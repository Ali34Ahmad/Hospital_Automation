package com.example.model

import androidx.annotation.DrawableRes

data class ActionIcon(
    @DrawableRes val icon: Int,
    val onCLick: () -> Unit
)
