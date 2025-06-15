package com.example.child_profile.presentation

import com.example.model.child.ChildFullData
import com.example.model.enums.ScreenState
import com.example.util.UiText

sealed interface ChildProfileUIAction {
    data class UpdateState(val newState: ScreenState): ChildProfileUIAction
    data class UpdateChild(val child: ChildFullData): ChildProfileUIAction
    data class UpdateRefreshState(val isRefreshing: Boolean): ChildProfileUIAction
    data object Refresh : ChildProfileUIAction
    data class ShowToast(val message: UiText): ChildProfileUIAction
}

interface ChildProfileNavigationAction{
    fun navigateUp()
    fun navigateToAddGuardianScreen(childId: Int)
    fun navigateToEmployeeProfileScreen(employeeId: Int)
    fun navigateToGuardiansScreen(childId: Int)
}