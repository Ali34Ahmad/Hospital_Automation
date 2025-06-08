package com.example.enter_email.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import com.example.model.enums.ScreenState
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.buttons.HospitalAutomationButton
import com.example.ui_components.components.dialog.MessageDialog
import com.example.ui_components.components.text_field.HospitalAutomationTextFiled

@Composable
fun EnterEmailScreen(
    uiState: EnterEmailUiState,
    uiActions: EnterEmailUiActions,
    modifier: Modifier = Modifier,
) {
    LaunchedEffect(uiState.screenState){
        if (uiState.screenState== ScreenState.Success) {
            uiActions.navigateToEmailOtpVerificationScreen()
        }
    }

    MessageDialog(
        showDialog = uiState.showErrorDialog,
        title = stringResource(R.string.sending_otp_failed),
        description = stringResource(R.string.something_went_wrong),
        onConfirm = { uiActions.onShowErrorDialogStateChange(false) },
        confirmButtonText = stringResource(R.string.ok),
        showCancelButton = false,
    )

    val isLoading=uiState.screenState== ScreenState.LOADING

//    val scrollState = rememberScrollState()
    Scaffold { contentPadding ->
        Surface(
            modifier = modifier
                .fillMaxSize()
                .padding(contentPadding),
        ) {
            Column(
                modifier = Modifier,
//                    .verticalScroll(scrollState),
                verticalArrangement = Arrangement.Center,
            ) {
                Column(
                    modifier = Modifier
                        .padding(
                            start = MaterialTheme.spacing.medium16,
                            end = MaterialTheme.spacing.medium16,
                            top = MaterialTheme.spacing.large24,
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        painter = painterResource(R.drawable.ill_email),
                        contentDescription = null,
                        modifier = Modifier.size(MaterialTheme.sizing.profileImageFailureIconSize)
                    )

                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.large32))

                    Text(
                        text = stringResource(R.string.enter_email),
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                    )

                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.small8))

                    Text(
                        text = stringResource(R.string.write_down_your_email_to_verify_that_it_is_you),
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.bodyMedium,
                    )

                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.large32))

                    HospitalAutomationTextFiled(
                        value = uiState.email,
                        onValueChange = {
                            uiActions.onEmailChange(it)
                        },
                        label = R.string.email,
                        supportingText = uiState.emailError?.asString(),
                        isError = uiState.emailError!=null,
                        isRequired = true,
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done
                        ),
                        enabled = !isLoading,
                    )

                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.large36))

                    HospitalAutomationButton(
                        onClick = { uiActions.onSendOtpCodeButtonClick() },
                        text = stringResource(R.string.send_otp_code),
                        modifier = Modifier
                            .fillMaxWidth(),
                        enabled = uiState.isSendOtpCodeButtonEnabled && !isLoading,
                        isLoading = isLoading,
                    )

                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.large24))
                }
            }
        }
    }
}

@DarkAndLightModePreview
@Composable
fun LoginScreenPreview() {
    Hospital_AutomationTheme {
        Surface {
            EnterEmailScreen(
                uiState = EnterEmailUiState(),
                uiActions = EnterEmailUiActions(
                    navigationActions = mockEnterEmailNavigationUiActions(),
                    businessActions = mockEnterEmailBusinessUiActions(),
                ),
                modifier = Modifier,
            )
        }
    }
}