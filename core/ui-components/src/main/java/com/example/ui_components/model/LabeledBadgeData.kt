package com.example.ui_components.model

import androidx.annotation.StringRes

data class LabeledBadgeData(
    @StringRes val label: Int,
    val badge: Int,
)
