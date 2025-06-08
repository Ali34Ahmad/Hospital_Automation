package com.example.email_verification.otp_verification.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.example.constants.icons.AppIcons
import com.example.ext.clickableTextRange
import com.example.model.enums.ScreenState
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.buttons.HospitalAutomationButton
import com.example.ui_components.components.dialog.LoadingDialog
import com.example.ui_components.components.dialog.MessageDialog
import com.example.ui_components.components.items.EmailTextWithImageItem
import com.example.ui_components.components.text_field.OtpInputRow
import com.example.ui_components.components.texts.ClickableText

@Composable
fun OtpVerificationScreen(
    uiState: OtpVerificationUiState,
    uiActions: OtpVerificationUiActions,
    modifier: Modifier = Modifier,
) {
    LaunchedEffect(uiState.screenState) {
        if (uiState.screenState== ScreenState.Success) {
            uiActions.navigateToEmailVerifiedSuccessfullyScreen()
        }
    }
    MessageDialog(
        showDialog = uiState.showErrorDialog,
        title = stringResource(R.string.error),
        description = uiState.errorDialogText?.asString() ?: "",
        onConfirm = { uiActions.onShowErrorDialogStateChange(false) },
        confirmButtonText = stringResource(R.string.ok),
        showCancelButton = false,
    )

    val isLoading = uiState.screenState == ScreenState.LOADING


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
                Image(
                    painter = painterResource(R.drawable.ill_lock),
                    contentDescription = null,
                    modifier = Modifier.size(MaterialTheme.sizing.profileImageFailureIconSize)
                )

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.large32))

                Text(
                    text = stringResource(R.string.verify_your_email),
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                )

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.small8))

                Text(
                    text = stringResource(R.string.verification_code_sent_to_email),
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodyMedium,
                )

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium16))

                EmailTextWithImageItem(
                    image = AppIcons.Outlined.mail,
                    email = uiState.email,
                )

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.large32))

                OtpInputRow(
                    otp = uiState.otpCode,
                    onOtpChange = { index, value ->
                        uiActions.onOtpCodeChange(index, value)
                    },
                    isValid = uiState.otpCodeError == null,
                    onValidate = {
                    },
                    invalidText = uiState.otpCodeError?.asString(),
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !isLoading,
                )

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.large36))

                HospitalAutomationButton(
                    onClick = { uiActions.onSubmitButtonClick() },
                    text = stringResource(R.string.submit),
                    modifier = Modifier
                        .fillMaxWidth(),
                    enabled = uiState.isVerifyButtonEnabled && !isLoading,
                    isLoading = isLoading,
                )

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge64))

                Text(
                    text = stringResource(R.string.Did_not_get_OTP_verification_code),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodyMedium,
                )

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.small12))

                val clickableText = stringResource(R.string.resend_code)
                ClickableText(
                    text = clickableText,
                    clickableTextRange = clickableText.clickableTextRange(clickableText),
                    onClick = {
                        uiActions.onResendOtpVerificationCode()
                    },
                    enabled=!isLoading,
                )
            }
        }
    }
}

@DarkAndLightModePreview
@Composable
fun OtpVerificationScreenPreview() {
    Hospital_AutomationTheme {
        Surface {
            OtpVerificationScreen(
                uiState = OtpVerificationUiState(
                    email = "aliahmad@gmail.com"
                ),
                uiActions = OtpVerificationUiActions(
                    navigationActions = mockEmailVerificationNavigationUiActions(),
                    businessActions = mockEmailVerificationBusinessUiActions(),
                ),
                modifier = Modifier,
            )
        }
    }
}