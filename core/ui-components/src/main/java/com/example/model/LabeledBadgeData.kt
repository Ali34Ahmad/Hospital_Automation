package com.example.model

import androidx.annotation.StringRes

data class LabeledBadgeData(
    @StringRes val label: Int,
    val badge: Int,
)
