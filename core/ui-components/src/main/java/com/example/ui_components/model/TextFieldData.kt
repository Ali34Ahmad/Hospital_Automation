package com.example.ui_components.model

import androidx.annotation.StringRes

data class TextFieldData(
    val value: String,
    val onValueChange: (String) -> Unit,
    val isRequired: Boolean = false,
    @StringRes val label: Int,
    val minLines: Int = 1,
    val maxLines: Int = minLines
)