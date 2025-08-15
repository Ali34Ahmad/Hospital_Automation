package com.example.pharmacies_search.presentation.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.model.admin.DepartmentState
import com.example.model.admin.DepartmentStatistics
import com.example.model.enums.ScreenState
import com.example.pharmacies_search.presentation.PharmaciesSearchScreen
import com.example.pharmacies_search.presentation.PharmaciesSearchUIState
import com.example.ui.theme.Hospital_AutomationTheme

@Preview
@Composable
fun PharmaciesSearchPreview() {
    Hospital_AutomationTheme {
        PharmaciesSearchScreen(
            onAction = {},
            navigationActions = mockNavigation,
            uiState = PharmaciesSearchUIState(
                screenState = ScreenState.SUCCESS,
                isRefreshing = false,
                selectedTab = DepartmentState.ACTIVE,
                searchQuery = "Ahmad",
                isDrawerOpened = false,
                isDarkTheme = false,
                statistics = DepartmentStatistics(100,12,20),
            ),
            pharmacies = pharmaciesFlow.collectAsLazyPagingItems()
        )
    }
}