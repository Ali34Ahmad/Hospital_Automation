package com.example.child_profile

import com.example.model.child.ChildFullData

sealed interface ChildProfileUIAction {
    object NavigateUp: ChildProfileUIAction
    data class NavigateToAddGuardianScreen(val childId: Int): ChildProfileUIAction
    data class NavigateToEmployeeProfileScreen(val employeeId: Int): ChildProfileUIAction
    data class NavigateToBirthCertificateScreen(val childId: Int): ChildProfileUIAction
    data class NavigateToGuardiansScreen(val childId: Int): ChildProfileUIAction

    object ShowError: ChildProfileUIAction
    object HideError: ChildProfileUIAction
    data class UpdateLoadingState(val isLoading: Boolean): ChildProfileUIAction
    data class UpdateChild(val child: ChildFullData): ChildProfileUIAction
}