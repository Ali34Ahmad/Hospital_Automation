package com.example.login.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import com.example.constants.icons.AppIcons
import com.example.ext.clickableTextRange
import com.example.model.enums.ScreenState
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.buttons.HospitalAutomationButton
import com.example.ui_components.components.dialog.MessageDialog
import com.example.ui_components.components.text_field.HospitalAutomationTextFiled
import com.example.ui_components.components.texts.ClickableText
import com.example.ui_components.components.topbars.HospitalAutomationTopBarWithOutlinedButton

@Composable
fun LoginScreen(
    uiState: LoginUiState,
    uiActions: LoginUiActions,
    modifier: Modifier = Modifier,
) {
    LaunchedEffect(uiState.screenState){
        if (uiState.screenState== ScreenState.SUCCESS) {
            uiActions.navigateToHomeScreen()
        }
    }

    MessageDialog(
        showDialog = uiState.showErrorDialog,
        title = stringResource(R.string.login_failed),
        description = stringResource(R.string.something_went_wrong),
        onConfirm = { uiActions.onShowErrorDialogStateChange(false) },
        confirmButtonText = stringResource(R.string.ok),
        showCancelButton = false,
    )

    val  isLoading=uiState.screenState== ScreenState.LOADING

    val scrollState = rememberScrollState()
    Scaffold(
        topBar = {
            HospitalAutomationTopBarWithOutlinedButton(
                title = stringResource(R.string.login),
                buttonText = stringResource(R.string.signup),
                onButtonClick = { uiActions.navigateToSignUpScreen() },
                isButtonEnable = !isLoading,
            )
        }
    ) { contentPadding ->
        Surface(
            modifier = modifier
                .fillMaxSize()
                .padding(contentPadding),
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(scrollState),
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
                        painter = painterResource(R.drawable.account_protection),
                        contentDescription = null,
                        modifier = Modifier.size(MaterialTheme.sizing.profileImageFailureIconSize)
                    )

                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.large32))

                    Text(
                        text = stringResource(R.string.welcome_back),
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                    )

                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.small8))

                    Text(
                        text = stringResource(R.string.Sign_in_to_access_your_work_tasks),
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
                        enabled = !isLoading,
                    )

                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.small8))

                    HospitalAutomationTextFiled(
                        value = uiState.password,
                        onValueChange = {
                            uiActions.onPasswordChange(it)
                        },
                        label = R.string.password,
                        supportingText = uiState.passwordError?.asString(),
                        isError = uiState.passwordError!=null,
                        isRequired = true,
                        modifier = Modifier.fillMaxWidth(),
                        visualTransformation = if (uiState.showPassword){
                            VisualTransformation.None
                        }else{
                            PasswordVisualTransformation()
                        },
                        trailingIcon = if (uiState.showPassword){
                            AppIcons.Outlined.visible
                        }else{
                            AppIcons.Outlined.invisible
                        },
                        onTrailingIconClick = {
                            uiActions.onUpdatePasswordVisibility(!uiState.showPassword)
                        },
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Password,
                        ),
                        enabled = !isLoading,
                    )

                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.large36))

                    HospitalAutomationButton(
                        onClick = { uiActions.onLoginButtonClick() },
                        text = stringResource(R.string.login),
                        modifier = Modifier
                            .fillMaxWidth(),
                        enabled = uiState.isLoginButtonEnabled,
                        isLoading = isLoading,
                    )

                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.large36))

                    val clickableText=stringResource(R.string.forgot_password)
                    ClickableText(
                        text = clickableText,
                        onClick = {uiActions.navigateToEnterEmailScreen()},
                        clickableTextRange = clickableText.clickableTextRange(clickableText),
                        enabled = !isLoading,
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
            LoginScreen(
                uiState = LoginUiState(),
                uiActions = LoginUiActions(
                    navigationActions = mockLoginNavigationUiActions(),
                    businessActions = mockLoginBusinessUiActions(),
                ),
                modifier = Modifier,
            )
        }
    }
}