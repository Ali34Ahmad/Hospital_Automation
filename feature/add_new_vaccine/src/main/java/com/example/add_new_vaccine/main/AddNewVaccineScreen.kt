package com.example.add_new_vaccine.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.constants.icons.AppIcons
import com.example.model.enums.AgeUnit
import com.example.model.enums.ScreenState
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.buttons.HospitalAutomationButton
import com.example.ui_components.components.dialog.MessageDialog
import com.example.ui_components.components.icon.IconWithBackground
import com.example.ui_components.components.screen_section.SectionWithTitle
import com.example.ui_components.components.text_field.HospitalAutomationTextFiled
import com.example.ui_components.components.text_field.InputWithDropdownSelector
import com.example.ui_components.components.topbars.HospitalAutomationTopBar

@Composable
fun AddNewVaccineScreen(
    uiState: AddNewVaccineUiState,
    uiActions: AddNewVaccineUiActions,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(uiState.screenState) {
        if (uiState.screenState == ScreenState.SUCCESS) {
            uiActions.navigateToVaccineDetailsScreenScreen()
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

    val isLoading = uiState.screenState == ScreenState.LOADING

    val scrollState = rememberScrollState()
    Scaffold(
        topBar = {
            HospitalAutomationTopBar(
                title = stringResource(R.string.add_new_vaccine),
                onNavigationIconClick = { uiActions.navigateUp() },
                modifier = Modifier.fillMaxWidth(),
                navigationIcon = AppIcons.Outlined.arrowBack,
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
                        value = uiState.vaccineName,
                        onValueChange = {
                            uiActions.onVaccineNameChange(it)
                        },
                        label = R.string.vaccine_name,
                        supportingText = uiState.vaccineNameError?.asString(),
                        isError = uiState.vaccineNameError != null,
                        isRequired = true,
                        modifier = Modifier.fillMaxWidth(),
                        enabled = !isLoading,
                    )

                    InputWithDropdownSelector(
                        supportingText = uiState.fromAgeError?.asString(),
                        isInputError = uiState.fromAgeError != null,
                        isRequired = true,
                        modifier = Modifier.fillMaxWidth(),
                        enabled = !isLoading,
                        editableTextValue = uiState.fromAge,
                        onEditableTextValueChange = {
                            uiActions.onFromAgeChange(it)
                        },
                        editableTextLabel = R.string.from_age,
                        isMenuExpanded = uiState.isFromAgeMenuExpanded,
                        onMenuExpandedChange = { uiActions.onFromAgeMenuExpandedChange(it) },
                        onDismissRequest = { uiActions.onFromAgeMenuExpandedChange(false) },
                        onDropDownItemSelected = { uiActions.updateSelectedFromAgeUnitIndex(AgeUnit.entries[it]) },
                        selectedIndexItem = uiState.fromAgeUnit.ordinal,
                        isDropDownMenuError = uiState.fromAgeUnitError != null,
                    )

                    InputWithDropdownSelector(
                        supportingText = uiState.toAgeError?.asString(),
                        isInputError = uiState.toAgeError != null,
                        isRequired = true,
                        modifier = Modifier.fillMaxWidth(),
                        enabled = !isLoading,
                        editableTextValue = uiState.toAge,
                        onEditableTextValueChange = {
                            uiActions.onToAgeChange(it)
                        },
                        editableTextLabel = R.string.to_age,
                        isMenuExpanded = uiState.isToAgeMenuExpanded,
                        onMenuExpandedChange = { uiActions.onToAgeMenuExpandedChange(it) },
                        onDismissRequest = { uiActions.onToAgeMenuExpandedChange(false) },
                        onDropDownItemSelected = { uiActions.updateSelectedToAgeUnitIndex(AgeUnit.entries[it]) },
                        selectedIndexItem = uiState.toAgeUnit.ordinal,
                        isDropDownMenuError = uiState.toAgeUnitError != null,
                    )

                    HospitalAutomationTextFiled(
                        value = uiState.quantity,
                        onValueChange = {
                            uiActions.onToAgeChange(it)
                        },
                        label = R.string.quantity,
                        supportingText = uiState.quantityError?.asString(),
                        isError = uiState.quantityError != null,
                        isRequired = true,
                        modifier = Modifier.fillMaxWidth(),
                        enabled = !isLoading,
                    )

                    HospitalAutomationTextFiled(
                        value = uiState.vaccineDescription,
                        onValueChange = {
                            uiActions.onToAgeChange(it)
                        },
                        label = R.string.vaccine_description,
                        supportingText = uiState.vaccineDescriptionError?.asString(),
                        isError = uiState.vaccineDescriptionError != null,
                        isRequired = true,
                        modifier = Modifier.fillMaxWidth(),
                        enabled = !isLoading,
                        maxLines = 3,
                    )
                }
                SectionWithTitle(
                    title = stringResource(R.string.interactions),
                    modifier = Modifier.fillMaxWidth(),
                    titleAreaPadding = PaddingValues(horizontal = MaterialTheme.spacing.medium16),
                    trailingIcon = {
                        IconButton(
                            onClick = { uiActions.onAddInteractionClick() }
                        ) {
                            IconWithBackground(
                                iconRes = AppIcons.Outlined.add,
                            )
                        }
                    }
                ) {
                    uiState.interactions.forEach { interaction ->
                        Text(interaction.title)
                    }
                    HospitalAutomationTextFiled(
                        value = uiState.interactionName,
                        onValueChange = {
                            uiActions.onToAgeChange(it)
                        },
                        label = R.string.interaction_name,
                        supportingText = uiState.interactionNameError?.asString(),
                        isError = uiState.interactionNameError != null,
                        isRequired = false,
                        modifier = Modifier.fillMaxWidth(),
                        enabled = !isLoading,
                    )

                    HospitalAutomationTextFiled(
                        value = uiState.interactionDescription,
                        onValueChange = {
                            uiActions.onToAgeChange(it)
                        },
                        label = R.string.interaction_description,
                        supportingText = uiState.interactionDescriptionError?.asString(),
                        isError = uiState.interactionDescriptionError != null,
                        isRequired = false,
                        modifier = Modifier.fillMaxWidth(),
                        enabled = !isLoading,
                        maxLines = 3,
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
fun AddNewVaccineScreenPreview() {
    Hospital_AutomationTheme {
        Surface {
            AddNewVaccineScreen(
                uiState = AddNewVaccineUiState(),
                uiActions = AddNewVaccineUiActions(
                    navigationActions = mockNavigationAction(),
                    businessActions = mockBusinessUiActions()
                )
            )
        }
    }
}