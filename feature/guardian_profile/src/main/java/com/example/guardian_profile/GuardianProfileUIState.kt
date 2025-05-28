package com.example.guardian_profile

import com.example.model.enums.FetchingDataState
import com.example.model.guardian.GuardianFullData
import com.example.util.UiText

data class GuardianProfileUIState(
    val isGuardianDataLoading: Boolean = false,
    val guardianData: GuardianFullData? = null,

    val fetchGuardianState: FetchingDataState = FetchingDataState.READY,
    val setAsGuardianState: FetchingDataState = FetchingDataState.READY,
    val hasBottomBar: Boolean = false,
    val errorMessage: UiText? = null
    )