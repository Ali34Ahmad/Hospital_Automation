package com.example.employees_search.presentation.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.employees_search.presentation.EmployeesSearchScreen
import com.example.employees_search.presentation.EmployeesSearchUIState
import com.example.model.admin.EmploymentStatistics
import com.example.model.employee.EmployeeState
import com.example.model.enums.ScreenState
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme

@DarkAndLightModePreview
@Composable
fun EmployeeSearchPreview() {
    Hospital_AutomationTheme {
        EmployeesSearchScreen(
            onAction = {},
            uiState = EmployeesSearchUIState(
                screenState = ScreenState.SUCCESS,
                isRefreshing = false,
                selectedTab = EmployeeState.EMPLOYED,
                searchQuery = "",
                statistics = EmploymentStatistics(120,4,10)
            ),
            navigationActions = mockNavigationActions,
            employees = employeesFlow.collectAsLazyPagingItems()
        )
    }
}