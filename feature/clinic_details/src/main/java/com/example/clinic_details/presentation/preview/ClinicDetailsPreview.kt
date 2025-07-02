package com.example.clinic_details.presentation.preview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.clinic_details.presentation.ClinicDetailsScreen
import com.example.clinic_details.presentation.ClinicDetailsUIState
import com.example.model.enums.BottomBarState
import com.example.model.enums.ScreenState
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme

@DarkAndLightModePreview
@Composable
fun ClinicDetailsPreview(){
    Hospital_AutomationTheme {
        var state by remember{ mutableStateOf(
            ClinicDetailsUIState(
                doctorId = 1,
                clinicId = 1,
                screenState = ScreenState.SUCCESS,
                clinic = mockClinic,
                sendRequestState = BottomBarState.DISABLED
            )) }
        ClinicDetailsScreen(
            uiState = state,
            navigationAction = mockAction,
            onAction = {},
        )
    }
}