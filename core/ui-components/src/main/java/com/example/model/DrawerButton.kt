package com.example.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class DrawerButton(
    @StringRes val text : Int,
    @DrawableRes val image: Int,
    val onClick: () -> Unit,
    val badgeCount : Int? = null,
)