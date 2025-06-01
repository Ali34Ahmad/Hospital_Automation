package com.example.guardian_profile.presentation

import com.example.model.enums.BottomBarState
import com.example.model.enums.FetchingDataState
import com.example.model.guardian.GuardianFullData

sealed interface GuardianProfileActions {

    object NavigateBack: GuardianProfileActions

    data class UpdateGuardianData(val data: GuardianFullData): GuardianProfileActions
    data class UpdateScreenState(val state: FetchingDataState): GuardianProfileActions
    data class UpdateBottomBarState(val state: BottomBarState): GuardianProfileActions

    data class OpenEmail(val string: String): GuardianProfileActions
    data class Open(val phone: String): GuardianProfileActions

    data object RetryLoadingData: GuardianProfileActions

    data class NavigateToChildren(val guardianId: Int): GuardianProfileActions
    object SetAsGuardian: GuardianProfileActions

    /**Assign the current user as the guardian of the child to be registered in the system on the next screen*/
    data class NavigateToAddChild(val guardianId: Int)
}