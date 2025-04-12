package com.example.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color

data class ProfileActionsItem (
    @DrawableRes val iconRes: Int,
    val iconBackgroundColor: Color,
    val iconColor: Color,
    @StringRes val title: Int,
    val showUnderline: Boolean,
)