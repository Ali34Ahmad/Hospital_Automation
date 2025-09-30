package com.example.doctor_signup.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.constants.icons.AppIcons
import com.example.model.enums.ScreenState
import com.example.model.enums.Gender
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.buttons.HospitalAutomationButton
import com.example.ui_components.components.buttons.OptionButton
import com.example.ui_components.components.dialog.MessageDialog
import com.example.ui_components.components.list_items.CheckBoxWithDetails
import com.example.ui_components.components.screen_section.SectionWithTitle
import com.example.ui_components.components.text_field.HospitalAutomationTextFiled
import com.example.ui_components.components.topbars.HospitalAutomationTopBarWithOutlinedButton

@Composable
fun DoctorSignUpScreen(
    uiState: DoctorSignUpUiState,
    uiActions: DoctorDoctorSignUpUiActions,
    modifier: Modifier = Modifier,
) {
    LaunchedEffect(uiState.screenState) {
        if (uiState.screenState== ScreenState.SUCCESS) {
            uiActions.navigateToEmailVerificationScreen()
        }
    }

    MessageDialog(
        showDialog = uiState.showErrorDialog,
        title = stringResource(R.string.signup_error),
        description = stringResource(R.string.something_went_wrong),
        onConfirm = { uiActions.onShowErrorDialogStateChange(false) },
        confirmButtonText = stringResource(R.string.ok),
        showCancelButton = false,
    )

    val isLoading=uiState.screenState== ScreenState.LOADING

    val scrollState = rememberScrollState()
    Scaffold(
        topBar = {
            HospitalAutomationTopBarWithOutlinedButton(
                title = stringResource(R.string.signup),
                buttonText = stringResource(R.string.login),
                onButtonClick = { uiActions.navigateToLoginScreen() },
                isButtonEnable = !isLoading,
            )
        }
    ) { contentPadding ->
        Surface(
            modifier = modifier.padding(contentPadding),
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
            ) {
                Column(
                    modifier = Modifier.padding(
                        start = MaterialTheme.spacing.medium16,
                        end = MaterialTheme.spacing.medium16,
                        top = MaterialTheme.spacing.large24,
                    ),
                    verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small8),
                ) {
                    HospitalAutomationTextFiled(
                        value = uiState.firstName,
                        onValueChange = {
                            uiActions.onFirstNameChange(it)
                        },
                        label = R.string.first_name,
                        supportingText = uiState.firstNameError?.asString(),
                        isError = uiState.firstNameError != null,
                        isRequired = true,
                        modifier = Modifier.fillMaxWidth(),
                        enabled = !isLoading,
                    )

                    HospitalAutomationTextFiled(
                        value = uiState.middleName,
                        onValueChange = {
                            uiActions.onMiddleNameChange(it)
                        },
                        label = R.string.middle_name,
                        supportingText = uiState.middleNameError?.asString(),
                        isError = uiState.middleNameError != null,
                        isRequired = true,
                        modifier = Modifier.fillMaxWidth(),
                        enabled = !isLoading,
                    )

                    HospitalAutomationTextFiled(
                        value = uiState.lastName,
                        onValueChange = {
                            uiActions.onLastNameChange(it)
                        },
                        label = R.string.last_name,
                        supportingText = uiState.lastNameError?.asString(),
                        isError = uiState.lastNameError != null,
                        isRequired = true,
                        modifier = Modifier.fillMaxWidth(),
                        enabled = !isLoading,
                    )

                    HospitalAutomationTextFiled(
                        value = uiState.specialty,
                        onValueChange = {
                            uiActions.onSpecialtyChange(it)
                        },
                        label = R.string.specialty,
                        supportingText = uiState.specialtyError?.asString(),
                        isError = uiState.specialtyError != null,
                        isRequired = true,
                        modifier = Modifier.fillMaxWidth(),
                        enabled = !isLoading,
                    )

                    HospitalAutomationTextFiled(
                        value = uiState.email,
                        onValueChange = {
                            uiActions.onEmailChange(it)
                        },
                        label = R.string.email,
                        supportingText = uiState.emailError?.asString(),
                        isError = uiState.emailError != null,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next,
                            keyboardType = KeyboardType.Email,
                        ),
                        isRequired = true,
                        modifier = Modifier.fillMaxWidth(),
                        enabled = !isLoading,
                    )
                    HospitalAutomationTextFiled(
                        value = uiState.phoneNumber,
                        onValueChange = {
                            uiActions.onPhoneNumberChange(it)
                        },
                        label = R.string.phone_number,
                        supportingText = uiState.phoneNumberError?.asString(),
                        isError = uiState.phoneNumberError != null,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next,
                            keyboardType = KeyboardType.Phone,
                        ),
                        isRequired = true,
                        modifier = Modifier.fillMaxWidth(),
                        enabled = !isLoading,
                    )

                    HospitalAutomationTextFiled(
                        value = uiState.password,
                        onValueChange = {
                            uiActions.onPasswordChange(it)
                        },
                        label = R.string.password,
                        supportingText = uiState.passwordError?.asString(),
                        isError = uiState.passwordError != null,
                        isRequired = true,
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Next
                        ),
                        visualTransformation = if (uiState.showPasswordText) {
                            VisualTransformation.None
                        } else {
                            PasswordVisualTransformation()
                        },
                        trailingIcon = if (uiState.showPasswordText) {
                            AppIcons.Outlined.visible
                        } else {
                            AppIcons.Outlined.invisible
                        },
                        onTrailingIconClick = {
                            uiActions.onUpdatePasswordVisibilityChange(!uiState.showPasswordText)
                        },
                        enabled = !isLoading,
                    )

                    HospitalAutomationTextFiled(
                        value = uiState.confirmPassword,
                        onValueChange = {
                            uiActions.onConfirmPasswordChange(it)
                        },
                        label = R.string.confirm_password,
                        supportingText = uiState.confirmPasswordError?.asString(),
                        isError = uiState.confirmPasswordError != null,
                        isRequired = true,
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        ),
                        visualTransformation = if (uiState.showConfirmPasswordText) {
                            VisualTransformation.None
                        } else {
                            PasswordVisualTransformation()
                        },
                        trailingIcon = if (uiState.showConfirmPasswordText) {
                            AppIcons.Outlined.visible
                        } else {
                            AppIcons.Outlined.invisible
                        },
                        onTrailingIconClick = {
                            uiActions.onUpdateConfirmPasswordVisibilityChange(!uiState.showConfirmPasswordText)
                        },
                        enabled = !isLoading,
                    )
                }
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.small8))
                Column(
                    modifier = Modifier.padding(
                        start = MaterialTheme.spacing.medium16,
                        end = MaterialTheme.spacing.medium16,
                        bottom = MaterialTheme.spacing.large24,
                    ),
                ) {
                    SectionWithTitle(
                        title = stringResource(R.string.gender)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium16),
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            OptionButton(
                                icon = AppIcons.Outlined.male,
                                text = R.string.male,
                                isSelected = uiState.gender == Gender.MALE,
                                onClick = {
                                    uiActions.onGenderChange(Gender.MALE)
                                },
                                modifier = Modifier.weight(1f),
                                enabled = !isLoading,
                            )
                            OptionButton(
                                icon = AppIcons.Outlined.female,
                                text = R.string.female,
                                isSelected = uiState.gender == Gender.FEMALE,
                                onClick = {
                                    uiActions.onGenderChange(Gender.FEMALE)
                                },
                                modifier = Modifier.weight(1f),
                                enabled = !isLoading,
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.large32))

                    CheckBoxWithDetails(
                        checked = uiState.isTermsAndConditionsChecked,
                        onCheckedChange = { uiActions.onTermsAndConditionsCheckChange(it) },
                        title = R.string.I_agree,
                        subTitle = R.string.terms_and_conditions,
                        modifier = Modifier.fillMaxWidth(),
                        enabled = !isLoading,
                    )
                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.large24))

                    CheckBoxWithDetails(
                        checked = uiState.isPersonalDataAccessibilityChecked,
                        onCheckedChange = { uiActions.onPersonalDataAccessibilityCheckChange(it) },
                        title = R.string.I_agree,
                        subTitle = R.string.personal_data_access,
                        modifier = Modifier.fillMaxWidth(),
                        enabled = !isLoading,
                    )
                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.large36))

                    HospitalAutomationButton(
                        onClick = { uiActions.onSignUpButtonClick() },
                        text = stringResource(R.string.signup),
                        modifier = Modifier.fillMaxWidth(),
                        enabled = uiState.isSignUpButtonEnabled && !isLoading,
                        isLoading = isLoading,
                    )

                }
            }
        }
    }
}


@DarkAndLightModePreview
@Composable
fun DoctorSignUpScreenPreview() {
    Hospital_AutomationTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface),
        ) {
            DoctorSignUpScreen(
                uiState = DoctorSignUpUiState(),
                uiActions = DoctorDoctorSignUpUiActions(
                    navigationActions = mockNavigationAction(),
                    businessActions = mockBusinessUiActions(),
                ),
                modifier = Modifier,
            )
        }
    }
}