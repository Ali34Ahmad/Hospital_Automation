package com.example.upload_employee_documents.main

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.constants.enums.FileUploadingState
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.card.FileUploadingCard
import com.example.ui_components.components.dialog.MessageDialog

@Composable
fun UploadEmployeeDocumentsScreen(
    uiState: UploadEmployeeDocumentsUiState,
    uiActions: UploadEmployeeDocumentsUiActions,
    modifier: Modifier = Modifier,
) {

    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { contentUri ->
        if (contentUri != null) {
            uiActions.onUploadFile(contentUri)
        }
    }

    MessageDialog(
        showDialog = uiState.showErrorDialog,
        title = stringResource(R.string.uploading_file_failed),
        description = uiState.errorDialogText?.asString()?:"",
        onConfirm = { uiActions.onShowErrorDialogStateChange(false) },
        confirmButtonText = stringResource(R.string.ok),
        showCancelButton = false,
    )


    Scaffold { contentPadding ->
        Surface(
            modifier = modifier
                .fillMaxSize()
                .padding(contentPadding),
        ) {
            Column(
                modifier = Modifier
                    .padding(
                        start = MaterialTheme.spacing.medium16,
                        end = MaterialTheme.spacing.medium16,
                        top = MaterialTheme.spacing.large24,
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                FileUploadingCard(
                    title = stringResource(R.string.document_upload_is_required),
                    description = stringResource(R.string.upload_employee_document_description),
                    fileInfo = uiState.fileInfo,
                    loadingState=uiState.fileUploadingState,
                    onFileUploadingPause = { },
                    onFileUploadingOpen = { },
                    onFileUploadingResume = {},
                    onUploadFileButtonClick = {
                        filePickerLauncher.launch("application/pdf")
                    },
                    onReplaceFileButtonClick = {
                        uiActions.onCancelFileUpload()
                        filePickerLauncher.launch("application/pdf")
                    },
                    onNextButtonClick = {
                        uiActions.navigateToAddResidentialAddressScreen()
                    },
                    enableNextButton=uiState.fileUploadingState== FileUploadingState.COMPLETE,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}

@DarkAndLightModePreview
@Composable
fun UploadEmployeeDocumentsScreenPreview() {
    Hospital_AutomationTheme {
        Surface {
            UploadEmployeeDocumentsScreen(
                uiState = UploadEmployeeDocumentsUiState(),
                uiActions = UploadEmployeeDocumentsUiActions(
                    navigationActions = mockUploadEmployeeDocumentsNavigationUiActions(),
                    businessActions = mockUploadEmployeeDocumentsBusinessUiActions(),
                ),
                modifier = Modifier,
            )
        }
    }
}