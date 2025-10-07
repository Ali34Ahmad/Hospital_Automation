package com.example.model.dialog

data class DialogOption(
    val title: String,
    val trailingText: String? = null,
    val isSelected: Boolean,
)
