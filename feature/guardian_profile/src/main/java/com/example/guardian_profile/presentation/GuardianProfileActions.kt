package com.example.guardian_profile.presentation

import com.example.model.enums.BottomBarState
import com.example.model.enums.ScreenState
import com.example.model.guardian.GuardianFullData

sealed interface GuardianProfileActions {


    data class UpdateGuardianData(val data: GuardianFullData): GuardianProfileActions
    data class UpdateScreenState(val state: ScreenState): GuardianProfileActions
    data class UpdateBottomBarState(val state: BottomBarState): GuardianProfileActions



    data object RetryLoadingData: GuardianProfileActions

    object SetAsGuardian: GuardianProfileActions

}

interface GuardianProfileNavigationAction{
    fun navigateUp()
    fun openEmail(email: String)
    fun openContacts(phone: String)
    fun navigateToAddChild(guardianId: Int)
    fun navigateToChildren(guardianId: Int)
}