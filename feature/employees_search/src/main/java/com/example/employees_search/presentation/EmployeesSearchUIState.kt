package com.example.employees_search.presentation

import com.example.model.admin.EmploymentStatistics
import com.example.model.employee.EmployeeState
import com.example.model.enums.ScreenState

data class EmployeesSearchUIState(
    val screenState: ScreenState = ScreenState.IDLE,
    val isRefreshing: Boolean = false,
    val selectedTab: EmployeeState = EmployeeState.EMPLOYED,
    val searchQuery: String = "",
    val statistics: EmploymentStatistics = EmploymentStatistics(),
    val isDrawerOpened: Boolean = false,
    val isDarkTheme: Boolean = false,
)
