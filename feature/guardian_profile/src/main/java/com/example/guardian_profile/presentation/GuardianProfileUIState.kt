package com.example.guardian_profile.presentation

import com.example.guardian_profile.navigation.UserProfileMode
import com.example.model.enums.BottomBarState
import com.example.model.enums.ScreenState
import com.example.model.guardian.GuardianFullData
import com.example.util.UiText

data class GuardianProfileUIState(
    val guardianData: GuardianFullData? = null,
    val screenState: ScreenState = ScreenState.IDLE,
    val bottomBarState: BottomBarState = BottomBarState.IDLE,
    val childId: Int?,
    val guardianId: Int,
    val userProfileMode: UserProfileMode = UserProfileMode.VIEW_ONLY,
    val isRefreshing: Boolean = false,
    val toastMessage: UiText? = null,
    val isLoadingDialogShown: Boolean = false,
    val isWarningDialogShown: Boolean = false,
    val deactivationReason: String = "",
    val isValidInput: Boolean? = null,

    ){
    val hasAdminAccess: Boolean
        get() = userProfileMode == UserProfileMode.ADMIN_ACCESS
}