package com.example.guardians.presentation

import com.example.model.enums.ScreenState
import com.example.model.guardian.GuardianData
import com.example.util.UiText

data class GuardiansUIState(
    val childId: Int,
    val data: List<GuardianData> = emptyList(),
    val state: ScreenState = ScreenState.IDLE,
    val isRefreshing: Boolean = false,
    val toastMessage : UiText? = null,
)
