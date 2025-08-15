package com.example.clinics_search.presentation.preview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.clinics_search.presentation.ClinicsSearchScreen
import com.example.clinics_search.presentation.ClinicsSearchUIState
import com.example.clinics_search.presentation.ScreenStep
import com.example.model.enums.ScreenState
import com.example.model.enums.TopBarState
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme

@DarkAndLightModePreview
@Composable
fun ClinicsSearchPreview() {
    Hospital_AutomationTheme{
            var uiState by remember{ mutableStateOf(ClinicsSearchUIState(
                screenStep = ScreenStep.SELECTION,
                topBarMode = TopBarState.DEFAULT,
                searchQuery = "clinic",
                screenState = ScreenState.SUCCESS,
                isDrawerOpened = true,
                hasAdminAccess = false,
            )
            ) }
            ClinicsSearchScreen(
                uiState = uiState,
                clinics = clinicsFlow.collectAsLazyPagingItems(),
                onAction = {},
                navigationActions = mockNavigationActions,
            )
        }
}