package com.example.medical_diagnosis.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.bottomBars.custom.AddDiagnosisBottomBar
import com.example.ui_components.components.text_field.TextFieldWithTitleAndDescription
import com.example.ui_components.components.topbars.HospitalAutomationTopBar

@Composable
fun DiagnosisScreen(
    viewModel: DiagnosisViewModel,
    navigationActions: DiagnosisNavigationActions,
    modifier: Modifier = Modifier
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    DiagnosisScreen(
        uiState = uiState.value,
        onAction = viewModel::onAction,
        navigationActions = navigationActions,
        modifier = modifier
    )
}

@Composable
fun DiagnosisScreen(
    uiState: DiagnosisUIState,
    onAction: (DiagnosisUIAction)-> Unit,
    navigationActions: DiagnosisNavigationActions,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val message = uiState.toastMessage?.asString()
    LaunchedEffect(message) {
        if (message!=null){
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            onAction(DiagnosisUIAction.ClearToast)
        }
    }
    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            HospitalAutomationTopBar(
                title = uiState.fullName,
                onNavigationIconClick = {},
                imageUrl = uiState.imgUrl,
                showImagePlaceHolder = uiState.showChildIcon ,
                hasTrailingContent = uiState.canSkip,
                trailingContent = {
                    TextButton(
                        onClick = {
                            navigationActions.navigateToMedicinesSearch(
                                childId = uiState.childId,
                                patientId = uiState.patientId,
                                appointmentId = uiState.appointmentId
                            )
                        }
                    ) {
                        Text(
                            text = stringResource(R.string.skip),
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                }
            )
        },
        bottomBar = {
            AddDiagnosisBottomBar(
                state = uiState.sendDateState,
                sendData = {
                    onAction(
                        DiagnosisUIAction.AddDiagnosis
                    )
                },
                onFinish = { navigationActions.navigateToAppointmentDetails(uiState.appointmentId) },
                onAddPrescription = {
                    navigationActions.navigateToMedicinesSearch(
                        childId = uiState.childId,
                        patientId = uiState.patientId,
                        uiState.appointmentId,
                    )
                },
                modifier = Modifier.padding(MaterialTheme.spacing.medium16)
            )
        },
        modifier = modifier,
    ) { innerPadding->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(MaterialTheme.spacing.medium16),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextFieldWithTitleAndDescription(
                title = R.string.add_medical_dignosis,
                description = R.string.add_medical_dignosis_description,
                text = uiState.text,
                onTextChange = {
                    onAction(
                        DiagnosisUIAction.UpdateText(it)
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = uiState.textFieldEnabled,
                hasError = uiState.textFieldHasError,
            )
        }
    }
}