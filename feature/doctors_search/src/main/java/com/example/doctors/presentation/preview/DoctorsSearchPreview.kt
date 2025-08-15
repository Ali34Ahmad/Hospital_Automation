package com.example.doctors.presentation.preview

import androidx.compose.runtime.Composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.doctors.presentation.DoctorSearchScreen
import com.example.doctors.presentation.DoctorsSearchUIState
import com.example.model.employee.EmployeeState
import com.example.model.enums.ScreenState
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme

@DarkAndLightModePreview
@Composable
fun DoctorsSearchPreview() {
    Hospital_AutomationTheme {
        DoctorSearchScreen(
            onAction = {},
            navigationActions = mockActions,
            uiState = DoctorsSearchUIState(
                screenState = ScreenState.SUCCESS,
                isRefreshing = false,
                selectedTab = EmployeeState.EMPLOYED,
                searchQuery = "",
                isDrawerOpened = false,
                clinicId = 1,
                clinicName = null,
            ),
            doctors = doctorsFlow.collectAsLazyPagingItems(),
        )
    }
}
@DarkAndLightModePreview
@Composable
fun DoctorsSearchByClinicPreview() {
    Hospital_AutomationTheme {
        DoctorSearchScreen(
            onAction = {},
            navigationActions = mockActions,
            uiState = DoctorsSearchUIState(
                screenState = ScreenState.SUCCESS,
                isRefreshing = false,
                selectedTab = EmployeeState.EMPLOYED,
                searchQuery = "",
                isDrawerOpened = false,
                clinicId = 1,
                clinicName = "Department of Surgery",
            ),
            doctors = doctorsFlow.collectAsLazyPagingItems(),
        )
    }
}