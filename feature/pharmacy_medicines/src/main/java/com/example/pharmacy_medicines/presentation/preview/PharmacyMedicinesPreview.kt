package com.example.pharmacy_medicines.presentation.preview

import androidx.compose.runtime.Composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.model.enums.ScreenState
import com.example.model.enums.TopBarState
import com.example.pharmacy_medicines.presentation.PharmacyMedicineScreen
import com.example.pharmacy_medicines.presentation.PharmacyMedicinesUIState
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme

@DarkAndLightModePreview
@Composable
fun PharmacyMedicinesPreview(){
    Hospital_AutomationTheme {
        PharmacyMedicineScreen(
            uiState = PharmacyMedicinesUIState(
                pharmacyId = 1,
                title = "Ali Ahmad",
                imageUrl = "fake image url",
                screenState = ScreenState.SUCCESS,
                topBarState = TopBarState.SEARCH,
                query = "",
                isRefreshing = false
            ),
            medicines = medicinesFlow.collectAsLazyPagingItems(),
            onAction = {  },
            navigationActions = fakeNavigationActions,
        )
    }
}