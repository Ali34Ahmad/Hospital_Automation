package com.example.child_profile.presentation

import com.example.model.child.ChildFullData

data class ChildProfileUIState(
    val child: ChildFullData? = null,
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
)
