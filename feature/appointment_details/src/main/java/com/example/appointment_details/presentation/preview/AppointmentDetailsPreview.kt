package com.example.appointment_details.presentation.preview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.appointment_details.presentation.AppointmentDetailsScreen
import com.example.appointment_details.presentation.AppointmentDetailsUIState
import com.example.model.enums.ScreenState
import com.example.ui.theme.Hospital_AutomationTheme

@Preview
@Composable
fun AppointmentDetailsPreview() {
    Hospital_AutomationTheme {
        var uiState by remember { mutableStateOf(
            AppointmentDetailsUIState(
                appointmentId = 1,
                appointment = mockVaccine,
                screenState = ScreenState.SUCCESS,
                canEdit = true,
            )
        ) }
        AppointmentDetailsScreen(
            uiState = uiState,
            onAction = {},
            navigationActions = mockNavigationActions
        )
    }
}