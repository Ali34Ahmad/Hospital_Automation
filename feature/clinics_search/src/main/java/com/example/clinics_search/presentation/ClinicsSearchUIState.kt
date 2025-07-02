package com.example.clinics_search.presentation

import com.example.model.enums.ScreenState
import com.example.model.enums.TopBarState

data class ClinicsSearchUIState(
    val doctorId : Int,
    val screenStep: ScreenStep = ScreenStep.INTRO,
    val isRefreshing: Boolean = false,
    val topBarMode: TopBarState = TopBarState.DEFAULT,
    val searchQuery: String = "",
    val screenState: ScreenState = ScreenState.IDLE,
    val isDrawerOpened: Boolean = false,
    val isDarkTheme: Boolean = false
)
enum class ScreenStep{
    INTRO,
    SELECTION
}