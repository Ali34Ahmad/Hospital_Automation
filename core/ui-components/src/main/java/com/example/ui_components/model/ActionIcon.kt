package com.example.ui_components.model

import androidx.annotation.DrawableRes

data class ActionIcon(
    @DrawableRes val icon: Int,
    val onCLick: () -> Unit
)
