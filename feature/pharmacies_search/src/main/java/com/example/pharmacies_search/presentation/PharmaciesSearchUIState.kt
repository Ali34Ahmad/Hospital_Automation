package com.example.pharmacies_search.presentation

import com.example.model.admin.DepartmentState
import com.example.model.admin.DepartmentStatistics
import com.example.model.enums.ScreenState

data class PharmaciesSearchUIState(
    val screenState: ScreenState = ScreenState.IDLE,
    val isRefreshing: Boolean = false,
    val selectedTab: DepartmentState = DepartmentState.ACTIVE,
    val searchQuery: String = "",
    val isDrawerOpened: Boolean = false,
    val isDarkTheme: Boolean = false,
    val statistics: DepartmentStatistics = DepartmentStatistics()
)
