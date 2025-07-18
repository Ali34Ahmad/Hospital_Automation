package com.example.pharmacies.preview

import androidx.compose.runtime.Composable
import com.example.model.enums.ScreenState
import com.example.pharmacies.presentaion.PharmaciesScreen
import com.example.pharmacies.presentaion.PharmaciesUIState
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme

@DarkAndLightModePreview
@Composable
fun GuardiansScreenPreview() {
    Hospital_AutomationTheme {
        val state = PharmaciesUIState(
            medicineId = 1,
            medicineName ="Citamol",
            data = mockList,
            state = ScreenState.SUCCESS,
            isRefreshing = false,
            toastMessage = null
        )

        PharmaciesScreen(
            onAction = {},
            uiState = state,
            navigationActions = mockNavigationAction
        )
    }
}