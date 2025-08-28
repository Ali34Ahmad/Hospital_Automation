package com.example.clinics_search.presentation

import com.example.model.admin.DepartmentState
import com.example.model.admin.DepartmentStatistics
import com.example.model.enums.ScreenState
import com.example.model.enums.TopBarState

//Skip the intro screen if the user has admin access
data class ClinicsSearchUIState(
    val hasAdminAccess: Boolean,
    val screenStep: ScreenStep = if (hasAdminAccess) ScreenStep.SELECTION else ScreenStep.INTRO,
    val isRefreshing: Boolean = false,
    val topBarMode: TopBarState = TopBarState.DEFAULT,
    val searchQuery: String = "",
    val screenState: ScreenState = ScreenState.IDLE,
    val isDrawerOpened: Boolean = false,
    val isDarkTheme: Boolean = false,
    val selectedTab: DepartmentState = DepartmentState.ACTIVE,
    val statistics: DepartmentStatistics = DepartmentStatistics()
){
    val topAppBarState
        get() = if(hasAdminAccess) TopBarState.SEARCH else topBarMode
}
enum class ScreenStep{
    INTRO,
    SELECTION
}