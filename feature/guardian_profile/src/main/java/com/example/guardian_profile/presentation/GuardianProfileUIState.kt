package com.example.guardian_profile.presentation

import com.example.guardian_profile.navigation.UserProfileMode
import com.example.model.enums.BottomBarState
import com.example.model.enums.ScreenState
import com.example.model.guardian.GuardianFullData
import com.example.util.UiText

data class GuardianProfileUIState(
    val isGuardianDataLoading: Boolean = false,
    val guardianData: GuardianFullData? = null,
    val screenState: ScreenState = ScreenState.IDLE,
    val bottomBarState: BottomBarState = BottomBarState.IDLE,
    val errorMessage: UiText? = null,
    val childId: Int? = null,
    val guardianId: Int = -1,
    val userProfileMode: UserProfileMode = UserProfileMode.VIEW_ONLY
)
