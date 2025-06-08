package com.example.child_profile.presentation

import com.example.model.child.ChildFullData
import com.example.model.enums.ScreenState

data class ChildProfileUIState(
    val child: ChildFullData? = null,
    val state: ScreenState = ScreenState.IDLE
)
