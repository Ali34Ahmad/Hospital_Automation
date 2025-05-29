package com.example.guardian_profile

import com.example.model.enums.FetchingDataState
import com.example.model.guardian.GuardianFullData

sealed interface GuardianProfileActions {

    object NavigateBack: GuardianProfileActions

    data class UpdateGuardianData(val data: GuardianFullData): GuardianProfileActions
    data class UpdateFetchGuardianState(val state: FetchingDataState): GuardianProfileActions
    data class UpdateBottomBarState(val state: FetchingDataState): GuardianProfileActions

    data class OpenEmail(val string: String): GuardianProfileActions
    data class Open(val phone: String): GuardianProfileActions

    data object Retry: GuardianProfileActions

    data class NavigateToChildren(val guardianId: Int): GuardianProfileActions
    object SetAsGuardian: GuardianProfileActions
}