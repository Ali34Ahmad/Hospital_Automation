package com.example.guardians.presentation

import com.example.model.enums.ScreenState
import com.example.model.guardian.GuardianData

data class GuardiansUIState(
    val childId: Int,
    val data: List<GuardianData> = emptyList(),
    val state: ScreenState = ScreenState.IDLE
)
