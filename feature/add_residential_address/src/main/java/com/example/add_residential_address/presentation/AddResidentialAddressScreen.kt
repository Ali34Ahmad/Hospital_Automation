package com.example.add_residential_address.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import com.example.model.enums.ScreenState
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.buttons.HospitalAutomationButton
import com.example.ui_components.components.dialog.ConfirmationDialog
import com.example.ui_components.components.list_items.CheckBoxWithDetails
import com.example.ui_components.components.text_field.HospitalAutomationTextFiled
import com.example.ui_components.components.topbars.HospitalAutomationTopBarWithTitle

@Composable
fun AddResidentialAddressScreen(
    uiState: AddAddressUiState,
    uiActions: AddResidentialAddressUiActions,
    modifier: Modifier = Modifier,
) {
    LaunchedEffect(uiState.screenState) {
        if (uiState.screenState== ScreenState.SUCCESS) {
            uiActions.navigateToUploadProfileImageScreen()
        }
    }

    ConfirmationDialog(
        showDialog = uiState.showErrorDialog,
        title = stringResource(R.string.error),
        description = stringResource(R.string.something_went_wrong),
        onConfirm = { uiActions.onShowErrorDialogStateChange(false) },
        confirmButtonText = stringResource(R.string.ok),
        showCancelButton = false,
    )

    val isLoading = uiState.screenState == ScreenState.LOADING

    val scrollState = rememberScrollState()
    Scaffold(
        topBar = {
            HospitalAutomationTopBarWithTitle(
                title = stringResource(R.string.residential_address),
                modifier = Modifier.fillMaxWidth(),
            )
        }
    ) { contentPadding ->
        Surface(
            modifier = modifier.padding(contentPadding)
                .fillMaxSize(),
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
                        value = uiState.governorate,
                        onValueChange = {
                            uiActions.onGovernorateTextChange(it)
                        },
                        label = R.string.governorate,
                        supportingText = uiState.governorateError?.asString(),
                        isError = uiState.governorateError != null,
                        isRequired = true,
                        modifier = Modifier.fillMaxWidth(),
                        enabled = !isLoading,
                    )

                    HospitalAutomationTextFiled(
                        value = uiState.city,
                        onValueChange = {
                            uiActions.onCityTextChange(it)
                        },
                        label = R.string.city,
                        supportingText = uiState.cityError?.asString(),
                        isError = uiState.cityError != null,
                        isRequired = true,
                        modifier = Modifier.fillMaxWidth(),
                        enabled = !isLoading,
                    )

                    HospitalAutomationTextFiled(
                        value = uiState.region,
                        onValueChange = {
                            uiActions.onRegionTextChange(it)
                        },
                        label = R.string.region,
                        supportingText = uiState.regionError?.asString(),
                        isError = uiState.regionError != null,
                        isRequired = true,
                        modifier = Modifier.fillMaxWidth(),
                        enabled = !isLoading,
                    )
                    HospitalAutomationTextFiled(
                        value = uiState.street,
                        onValueChange = {
                            uiActions.onStreetTextChange(it)
                        },
                        label = R.string.street,
                        supportingText = uiState.streetError?.asString(),
                        isError = uiState.streetError != null,
                        isRequired = true,
                        modifier = Modifier.fillMaxWidth(),
                        enabled = !isLoading,
                    )
                    HospitalAutomationTextFiled(
                        value = uiState.note,
                        onValueChange = {
                            uiActions.onNoteTextChange(it)
                        },
                        label = R.string.note,
                        supportingText = uiState.noteError?.asString(),
                        isError = uiState.noteError != null,
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done
                        ),
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
                    CheckBoxWithDetails(
                        checked = uiState.isAddressInfoPolicyChecked,
                        onCheckedChange = {
                            uiActions.onAddressInfoVisibilityPolicyCheckChange(
                                it
                            )
                        },
                        title = R.string.I_agree,
                        subTitle = R.string.address_privacy_policy,
                        modifier = Modifier.fillMaxWidth(),
                        enabled = !isLoading,
                    )
                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.large36))

                    HospitalAutomationButton(
                        onClick = { uiActions.onSubmitButtonClick() },
                        text = stringResource(R.string.submit),
                        modifier = Modifier.fillMaxWidth(),
                        enabled = uiState.isSubmitButtonEnabled && !isLoading,
                        isLoading = isLoading,
                    )
                }

            }
        }
    }
}


@DarkAndLightModePreview
@Composable
fun AddResidentialAddressScreenPreview() {
    Hospital_AutomationTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface),
        ) {
            AddResidentialAddressScreen(
                uiState = AddAddressUiState(),
                uiActions = AddResidentialAddressUiActions(
                    navigationActions = mockNavigationAction(),
                    businessActions = mockBusinessUiActions(),
                ),
                modifier = Modifier,
            )
        }
    }
}