package com.example.guardians.presentation

import com.example.model.enums.ScreenState

sealed interface GuardiansUIActions {
    object Retry: GuardiansUIActions
    data class UpdateState(val newState: ScreenState): GuardiansUIActions
}

interface GuardianNavigationAction{
    fun navigateUp()
    fun navigateToGuardianProfile(guardianId: Int)
}

