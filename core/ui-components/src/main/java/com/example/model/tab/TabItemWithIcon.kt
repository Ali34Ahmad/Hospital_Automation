package com.example.model.tab

import androidx.annotation.DrawableRes
import com.example.util.UiText

data class TabItemWithIcon(
    val title: UiText,
    @DrawableRes val iconRes: Int,
)
