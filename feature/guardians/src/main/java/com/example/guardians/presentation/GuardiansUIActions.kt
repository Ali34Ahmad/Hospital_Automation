package com.example.guardians.presentation

import com.example.model.enums.ScreenState
import com.example.util.UiText

sealed interface GuardiansUIActions {
    data class UpdateState(val newState: ScreenState): GuardiansUIActions
    data class UpdateRefreshState(val isRefreshing: Boolean): GuardiansUIActions
    data object Refresh : GuardiansUIActions
    data class ShowToast(val message: UiText): GuardiansUIActions

}

interface GuardianNavigationAction{
    fun navigateUp()
    fun navigateToGuardianProfile(guardianId: Int)
}

