package com.example.reset_password.main

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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import com.example.constants.icons.AppIcons
import com.example.model.enums.ScreenState
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.components.buttons.HospitalAutomationButton
import com.example.ui_components.components.dialog.MessageDialog
import com.example.ui_components.components.text_field.HospitalAutomationTextFiled
import com.example.ui_components.R
import com.example.ui_components.components.items.EmailTextWithImageItem

@Composable
fun ResetPasswordScreen(
    uiState: ResetPasswordUiState,
    uiActions: ResetPasswordUiActions,
    modifier: Modifier = Modifier,
) {
    LaunchedEffect(uiState.screenState) {
        if (uiState.screenState== ScreenState.SUCCESS) {
            uiActions.navigateToHomeScreen()
        }
    }

    MessageDialog(
        showDialog = uiState.showErrorDialog,
        title = stringResource(R.string.error_reseting_password),
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
                        painter = painterResource(R.drawable.ill_reset_password),
                        contentDescription = null,
                        modifier = Modifier.size(MaterialTheme.sizing.profileImageFailureIconSize)
                    )

                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.large32))

                    Text(
                        text = stringResource(R.string.reset_password),
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                    )

                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.small8))

                    Text(
                        text = stringResource(R.string.reset_password_description),
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                    )

                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium16))

                    EmailTextWithImageItem(
                        image = AppIcons.Outlined.mail,
                        email = uiState.email,
                    )

                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.large32))

                    HospitalAutomationTextFiled(
                        value = uiState.password,
                        onValueChange = {
                            uiActions.onPasswordChange(it)
                        },
                        label = R.string.new_password,
                        supportingText = uiState.passwordError?.asString(),
                        isError = uiState.passwordError != null,
                        isRequired = true,
                        trailingIcon = if(uiState.showPassword){
                            AppIcons.Outlined.visible
                        }else{
                            AppIcons.Outlined.invisible
                        },
                        visualTransformation = if(uiState.showPassword){
                            VisualTransformation.None
                        }else{
                            PasswordVisualTransformation()
                        },
                        onTrailingIconClick ={
                            uiActions.onUpdatePasswordVisibility(!uiState.showPassword)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Password,
                        ),
                        enabled = !isLoading,
                    )

                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.large36))

                    HospitalAutomationButton(
                        onClick = { uiActions.onResetPasswordButtonClick() },
                        text = stringResource(R.string.reset_password),
                        modifier = Modifier
                            .fillMaxWidth(),
                        enabled = uiState.isResetPasswordButtonEnabled && !isLoading,
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
            ResetPasswordScreen(
                uiState = ResetPasswordUiState(),
                uiActions = ResetPasswordUiActions(
                    navigationActions = mockResetPasswordNavigationUiActions(),
                    businessActions = mockResetPasswordBusinessUiActions(),
                ),
                modifier = Modifier,
            )
        }
    }
}