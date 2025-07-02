package com.example.medical_diagnosis.presentation.preview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.medical_diagnosis.presentation.DiagnosisNavigationActions
import com.example.medical_diagnosis.presentation.DiagnosisScreen
import com.example.medical_diagnosis.presentation.DiagnosisUIAction
import com.example.medical_diagnosis.presentation.DiagnosisUIState
import com.example.model.enums.BottomBarState
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.util.UiText

val mockNavigationActions = object : DiagnosisNavigationActions{
    override fun navigateUp() {

    }

    override fun navigateToPrescriptionScreen(appointmentId: Int) {
    }
}

@DarkAndLightModePreview
@Composable
fun AddDiagnosisPreview() {
    var uiState by remember { mutableStateOf(DiagnosisUIState(
        fullName = "Jamel Mahrez",
        sendDateState = BottomBarState.DISABLED,
        textFieldErrorText = UiText.DynamicString("has error"),
        text = "Ali Mansoura suffers from Ali Ahamd"
    )) }
    Hospital_AutomationTheme {
        DiagnosisScreen(
            uiState = uiState,
            onAction = {action->
                when(action){
                    DiagnosisUIAction.AddDiagnosis -> Unit
                    is DiagnosisUIAction.UpdateText -> {
                        uiState = uiState.copy(text = action.text)
                    }
                }
            },
            navigationActions = mockNavigationActions
        )
    }
}