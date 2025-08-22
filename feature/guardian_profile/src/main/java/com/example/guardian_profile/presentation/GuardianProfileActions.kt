package com.example.guardian_profile.presentation

import com.example.model.enums.BottomBarState
import com.example.model.enums.ScreenState
import com.example.model.guardian.GuardianFullData
import com.example.util.UiText

sealed interface GuardianProfileActions {


    data class UpdateGuardianData(val data: GuardianFullData): GuardianProfileActions
    data class UpdateScreenState(val state: ScreenState): GuardianProfileActions
    data class UpdateBottomBarState(val state: BottomBarState): GuardianProfileActions



    object SetAsGuardian: GuardianProfileActions
    data class UpdateRefreshState(val isRefreshing: Boolean): GuardianProfileActions
    data object Refresh : GuardianProfileActions
    data class ShowToast(val message: UiText): GuardianProfileActions

    //Account management
    object DeactivateAccount: GuardianProfileActions
    object ReactivateAccount: GuardianProfileActions

    //warning dialog
    object ShowWarningDialog: GuardianProfileActions
    object HideWarningDialog: GuardianProfileActions
    object ClearDeactivationReason: GuardianProfileActions
    data class UpdateDeactivationReason(val  newValue: String) : GuardianProfileActions
    //loading dialog
    object ShowLoadingDialog: GuardianProfileActions
    object HideLoadingDialog:GuardianProfileActions
}

interface GuardianProfileNavigationAction{
    fun navigateUp()
    fun openEmail(email: String)
    fun openContacts(phone: String)
    fun navigateToAddChild(guardianId: Int)
    fun navigateToChildren(guardianId: Int)
    fun navigateToAppointments(guardianId: Int)
    fun navigateToPrescriptions(guardianId: Int)
    fun navigateToMedicalRecord(guardianId: Int)
}