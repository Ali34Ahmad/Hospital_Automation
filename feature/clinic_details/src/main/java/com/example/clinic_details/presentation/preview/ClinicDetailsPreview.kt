package com.example.clinic_details.presentation.preview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.clinic_details.presentation.ClinicDetailsScreen
import com.example.clinic_details.presentation.ClinicDetailsUIState
import com.example.model.enums.ScreenState
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme

@DarkAndLightModePreview
@Composable
fun ClinicDetailsPreview(){
    Hospital_AutomationTheme {
        var state by remember{ mutableStateOf(
            ClinicDetailsUIState(
                clinicId = 1,
                clinic = mockClinic,
                screenState = ScreenState.SUCCESS
            )) }
        ClinicDetailsScreen(
            uiState = state,
            navigationAction = mockAction,
            onAction = {},
        )
    }
}