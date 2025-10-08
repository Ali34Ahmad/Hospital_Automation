package com.example.upload_child_documents.presentation

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
import com.example.ui_components.components.card.ChildAddedSuccessfullyCard
import com.example.ui_components.components.card.FileUploadingCard
import com.example.ui_components.components.dialog.ConfirmationDialog

@Composable
fun UploadChildDocumentsScreen(
    uiState: UploadChildDocumentsUiState,
    uiActions: UploadChildDocumentsUiActions,
    modifier: Modifier = Modifier,
) {

    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { contentUri ->
        if (contentUri != null) {
            uiActions.onUploadFile(contentUri)
        }
    }

    ConfirmationDialog(
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
                if (!uiState.showSuccessCard){
                    FileUploadingCard(
                        title = stringResource(R.string.child_birth_certification_is_required),
                        description = stringResource(R.string.upload_employee_document_description),
                        fileInfo = uiState.fileInfo,
                        loadingState = uiState.fileUploadingState,
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
                            uiActions.showSuccessCard()
                        },
                        enableNextButton = uiState.fileUploadingState == FileUploadingState.COMPLETE,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }else{
                    ChildAddedSuccessfullyCard(
                        title = stringResource(R.string.child_added_successfully),
                        description = stringResource(R.string.child_added_successfully_description),
                        onAddGuardianButtonClick = {
                            uiActions.navigateToSearchGuardiansScreen()
                        },
                        onBackToHomeButtonClick = {
                            uiActions.navigateToHomeScreen()
                        },
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            }
        }
    }
}

@DarkAndLightModePreview
@Composable
fun UploadChildDocumentsScreenPreview() {
    Hospital_AutomationTheme {
        Surface {
            UploadChildDocumentsScreen(
                uiState = UploadChildDocumentsUiState(),
                uiActions = UploadChildDocumentsUiActions(
                    navigationActions = mockUploadEmployeeDocumentsNavigationUiActions(),
                    businessActions = mockUploadEmployeeDocumentsBusinessUiActions(),
                ),
                modifier = Modifier,
            )
        }
    }
}