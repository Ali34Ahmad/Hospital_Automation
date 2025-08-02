package com.example.medical_diagnosis.presentation.preview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.medical_diagnosis.presentation.DiagnosisNavigationActions
import com.example.medical_diagnosis.presentation.DiagnosisScreen
import com.example.medical_diagnosis.presentation.DiagnosisUIState
import com.example.model.enums.BottomBarState
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.util.UiText

val mockNavigationActions = object : DiagnosisNavigationActions{
    override fun navigateToAppointmentDetails(appointmentId: Int) {

    }

    override fun navigateToMedicinesSearch(
        childId: Int?,
        patientId: Int?,
        appointmentId: Int,
    ) {
    }
}

@DarkAndLightModePreview
@Composable
fun AddDiagnosisPreview() {
    var uiState by remember { mutableStateOf(DiagnosisUIState(
        fullName = "Jamel Mahrez",
        sendDateState = BottomBarState.DISABLED,
        textFieldErrorText = UiText.DynamicString("has error"),
        text = "Ali Mansoura suffers from Ali Ahamd",
        childId = null,
        patientId = 1,
        appointmentId = 1,
        canSkip = false
    )) }
    Hospital_AutomationTheme {
        DiagnosisScreen(
            uiState = uiState,
            onAction = {
            },
            navigationActions = mockNavigationActions
        )
    }
}