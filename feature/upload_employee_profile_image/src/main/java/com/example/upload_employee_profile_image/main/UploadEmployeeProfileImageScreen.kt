package com.example.upload_employee_profile_image.main

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.core.net.toUri
import com.example.constants.enums.FileUploadingState
import com.example.ui_components.components.card.IllustrationCard
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.buttons.HospitalAutomationButton
import com.example.ui_components.components.card.ImageUploaderCard
import com.example.ui_components.components.dialog.MessageDialog


@Composable
fun UploadEmployeeProfileImageScreen(
    uiState: UploadEmployeeProfileImageUiState,
    uiActions: UploadEmployeeProfileImageUiActions,
    modifier: Modifier = Modifier,
) {

    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { contentUri ->
        if (contentUri != null) {
            uiActions.onUploadImage(contentUri)
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
                if (uiState.uri == null || uiState.imageFileInfo == null) {
                    IllustrationCard(
                        title = stringResource(R.string.upload_profile_image),
                        modifier = Modifier.fillMaxWidth(),
                        illustration = {
                            Image(
                                painter = painterResource(R.drawable.ill_photo),
                                contentDescription = null,
                                modifier = Modifier.size(MaterialTheme.sizing.illustrationImageSize)
                            )
                        },
                        actionButtonsSection = {
                            HospitalAutomationButton(
                                onClick = { filePickerLauncher.launch("image/*") },
                                text = stringResource(R.string.upload),
                                modifier = Modifier.fillMaxWidth()
                            )
                        },
                        description = stringResource(R.string.upload_profile_image_description)
                    )
                } else {
                    ImageUploaderCard(
                        imageUri = uiState.uri,
                        onNextButtonClick = { uiActions.navigateToHomeScreenScreen() },
                        onReplaceFileButtonClick = { filePickerLauncher.launch("image/*") },
                        fileInfo = uiState.imageFileInfo,
                        fileUploadingState = uiState.uploadingState,
                        enableNextButton=uiState.uploadingState== FileUploadingState.COMPLETE,
                    )
                }
            }
        }
    }
}

@DarkAndLightModePreview
@Composable
fun UploadEmployeeDocumentsScreenPreview() {
    Hospital_AutomationTheme {
        Surface {
            UploadEmployeeProfileImageScreen(
                uiState = UploadEmployeeProfileImageUiState(),
                uiActions = UploadEmployeeProfileImageUiActions(
                    navigationActions = mockUploadEmployeeProfileImageNavigationUiActions(),
                    businessActions = mockUploadEmployeeProfileImageBusinessUiActions(),
                ),
                modifier = Modifier,
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun UploadEmployeeDocumentsScreenWithImagePreview() {
    Hospital_AutomationTheme {
        Surface {
            UploadEmployeeProfileImageScreen(
                uiState = UploadEmployeeProfileImageUiState(
                    uri = "".toUri()
                ),
                uiActions = UploadEmployeeProfileImageUiActions(
                    navigationActions = mockUploadEmployeeProfileImageNavigationUiActions(),
                    businessActions = mockUploadEmployeeProfileImageBusinessUiActions(),
                ),
                modifier = Modifier,
            )
        }
    }
}