package com.example.email_verification.email_verified_successfully.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.model.enums.ScreenState
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.card.VerifiedAccountCard
import com.example.ui_components.components.dialog.ConfirmationDialog

@Composable
fun EmailVerifiedSuccessfullyScreen(
    uiState: EmailVerifiedSuccessfullyUiState,
    uiActions: EmailVerifiedSuccessfullyUiActions,
    modifier: Modifier = Modifier,
) {
    LaunchedEffect(uiState.screenState) {
        if (uiState.screenState== ScreenState.SUCCESS) {
            uiActions.navigateToUploadEmployeeDocumentsScreen()
        }
    }

    ConfirmationDialog(
        showDialog = uiState.showErrorDialog,
        title = stringResource(R.string.login_failed),
        description = stringResource(R.string.failed_to_login_try_again),
        onConfirm = { uiActions.onShowErrorDialogStateChange(false) },
        confirmButtonText = stringResource(R.string.ok),
        showCancelButton = false,
    )

    val isLoading=uiState.screenState== ScreenState.LOADING

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
                VerifiedAccountCard(
                    title = stringResource(R.string.verified),
                    description = stringResource(R.string.email_verified_description),
                    onButtonClick = {
                        if (uiState.navigateToResetPassword) {
                            uiActions.navigateToResetPasswordScreen()
                        } else {
                            uiActions.onNextButtonClick()
                        }
                    },
                    isButtonLoading=isLoading,
                )
            }
        }
    }
}

@DarkAndLightModePreview
@Composable
fun EmailVerifiedSuccessfullyScreenPreview() {
    Hospital_AutomationTheme {
        Surface {
            EmailVerifiedSuccessfullyScreen(
                uiState = EmailVerifiedSuccessfullyUiState(
                    email = "aliahmad@gmail.com"
                ),
                uiActions = EmailVerifiedSuccessfullyUiActions(
                    navigationActions = mockEmailVerifiedSuccessfullyScreenNavigationUiActions(),
                    businessActions = mockEmailVerifiedSuccessfullyScreenBusinessUiActions(),
                ),
                modifier = Modifier,
            )
        }
    }
}