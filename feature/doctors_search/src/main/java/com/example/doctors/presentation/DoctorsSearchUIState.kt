package com.example.doctors.presentation

import com.example.model.admin.EmploymentStatistics
import com.example.model.employee.EmployeeState
import com.example.model.enums.ScreenState
import com.example.model.enums.TopBarState

data class DoctorsSearchUIState(
    val clinicId: Int?,
    val clinicName: String?,
    val screenState: ScreenState = ScreenState.IDLE,
    val isRefreshing: Boolean = false,
    val selectedTab: EmployeeState = EmployeeState.EMPLOYED,
    val searchQuery: String = "",
    val isDrawerOpened: Boolean = false,
    val isDarkTheme: Boolean = false,
    val statistics: EmploymentStatistics = EmploymentStatistics(),
    private val topBarMode: TopBarState = TopBarState.DEFAULT
){
    //if you're searching globally then you have two states else you have only search state
    val topBarState
        get() = if(clinicId == null) TopBarState.SEARCH else topBarMode
}
