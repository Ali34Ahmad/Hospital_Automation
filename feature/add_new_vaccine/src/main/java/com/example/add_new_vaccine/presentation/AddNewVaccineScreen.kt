package com.example.add_new_vaccine.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.example.constants.icons.AppIcons
import com.example.model.enums.AgeUnit
import com.example.model.enums.ScreenState
import com.example.model.helper.ext.capitalFirstChar
import com.example.model.menu.DropDownMenuItem
import com.example.model.tab.TabItemWithIcon
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.bottomBars.custom.SendingDataBottomBar
import com.example.ui_components.components.dialog.MessageDialog
import com.example.ui_components.components.dialog.TwoFieldsInputDialog
import com.example.ui_components.components.tab.TabLayoutWithIcons
import com.example.ui_components.components.tables.vaccine_interactions.EmptyVaccineInteractionsTable
import com.example.ui_components.components.tables.vaccine_interactions.VaccineInteractionTable
import com.example.ui_components.components.text_field.HospitalAutomationTextFiled
import com.example.ui_components.components.text_field.InputWithDropdownSelector
import com.example.ui_components.components.topbars.HospitalAutomationTopBar
import com.example.util.UiText

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
        title = stringResource(R.string.add_vaccine_error),
        description = stringResource(R.string.something_went_wrong),
        onConfirm = { uiActions.onShowErrorDialogStateChange(false) },
        confirmButtonText = stringResource(R.string.ok),
        showCancelButton = false,
    )

    val dialogTitle=if(uiState.isAddingNewInteraction)
        stringResource(R.string.new_interaction)
    else
        stringResource(R.string.edit_interaction)

    TwoFieldsInputDialog(
        title = dialogTitle,
        firstFieldText = uiState.interactionName,
        firstFieldTextError = uiState.interactionNameError?.asString(),
        isFirstFieldError = uiState.interactionNameError != null,
        onFirstFieldTextChange = { uiActions.onInteractionNameChange(it) },
        secondFieldText = uiState.interactionDescription,
        isSecondFieldError = uiState.interactionDescriptionError != null,
        secondFieldTextError = uiState.interactionDescriptionError?.asString(),
        onSecondFieldTextChange = { uiActions.onInteractionDescriptionChange(it) },
        showDialog = uiState.showAddInteractionDialog,
        onDismiss = { uiActions.onUpdateVaccineInteractionDialogVisibilityState(false) },
        showCancelButton = true,
        dismissButtonText = stringResource(R.string.cancel),
        onConfirm = {
            if (uiState.isAddingNewInteraction) {
                uiActions.onAddInteractionClick()
            } else {
                uiActions.onSaveInteractionClick()
            }
        },
        confirmButtonText = if (uiState.isAddingNewInteraction) {
            stringResource(R.string.add)
        } else {
            stringResource(R.string.save)
        },
        firstFieldLabel = R.string.interaction_name,
        secondFieldLabel = R.string.interaction_description,
        isConfirmButtonEnabled=uiState.isInteractionDialogConfirmButtonEnabled,
    )

    val isLoading = uiState.screenState == ScreenState.LOADING

    val tabs = listOf(
        TabItemWithIcon(
            title = UiText.StringResource(R.string.show_all),
            iconRes = AppIcons.Outlined.list
        ),
        TabItemWithIcon(
            title = UiText.StringResource(R.string.new_),
            iconRes = AppIcons.Outlined.add
        ),
    )
    
    val scrollState = rememberScrollState()
    Scaffold(
        topBar = {
            HospitalAutomationTopBar(
                title = stringResource(R.string.add_new_vaccine),
                onNavigationIconClick = { uiActions.navigateUp() },
                modifier = Modifier.fillMaxWidth(),
                navigationIcon = AppIcons.Outlined.arrowBack,
            )
        },
        bottomBar = {
            SendingDataBottomBar(
                onSuccess = {},
                text = stringResource(R.string.submit),
                onButtonClick = {
                    uiActions.onSubmitButtonClick()
                },
                state = uiState.bottomBarState,
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(
                        MaterialTheme.spacing.medium16
                    ),
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
                        onDropDownItemSelected = {
                            uiActions.onUpdateSelectedFromAgeUnitIndex(
                                AgeUnit.entries[it]
                            )
                        },
                        selectedIndexItem = uiState.fromAgeUnit.ordinal,
                        isDropDownMenuError = uiState.fromAgeUnitError != null,
                        dropDownMenuItems = AgeUnit.entries.map { DropDownMenuItem(it.name.capitalFirstChar()) },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
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
                        onDropDownItemSelected = { uiActions.onUpdateSelectedToAgeUnitIndex(AgeUnit.entries[it]) },
                        selectedIndexItem = uiState.toAgeUnit.ordinal,
                        isDropDownMenuError = uiState.toAgeUnitError != null,
                        dropDownMenuItems = AgeUnit.entries.map { DropDownMenuItem(it.name.capitalFirstChar()) },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                    )

                    HospitalAutomationTextFiled(
                        value = uiState.quantity,
                        onValueChange = {
                            uiActions.onQuantityChange(it)
                        },
                        label = R.string.quantity,
                        supportingText = uiState.quantityError?.asString(),
                        isError = uiState.quantityError != null,
                        isRequired = true,
                        modifier = Modifier.fillMaxWidth(),
                        enabled = !isLoading,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                    )

                    HospitalAutomationTextFiled(
                        value = uiState.vaccineDescription,
                        onValueChange = {
                            uiActions.onVaccineDescriptionChange(it)
                        },
                        label = R.string.vaccine_description,
                        supportingText = uiState.vaccineDescriptionError?.asString(),
                        isError = uiState.vaccineDescriptionError != null,
                        isRequired = true,
                        modifier = Modifier.fillMaxWidth(),
                        enabled = !isLoading,
                        maxLines = 3,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Done
                        ),
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(
                            horizontal = MaterialTheme.spacing.medium16
                        )
                        .clip(MaterialTheme.shapes.small)
                        .background(MaterialTheme.colorScheme.background),
                ) {
                    Text(
                        text = stringResource(R.string.interactions),
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.padding(
                            vertical = MaterialTheme.spacing.small8,
                            horizontal = MaterialTheme.spacing.medium16
                        ),
                    )
                    TabLayoutWithIcons(
                        tabs = tabs,
                        selectedTabIndex = uiState.selectedTabItem,
                        onTabClick = { index ->
                            uiActions.onTabItemClick(index)
                        }
                    )
                    VaccineInteractionTable(
                        interactions = uiState.interactions,
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.background),
                        onItemClick = { index ->
                            uiActions.onVaccineInteractionTableItemClick(index)
                        },
                        isEditable = true,
                        emptyTable = {
                            EmptyVaccineInteractionsTable(
                                modifier = Modifier.padding(
                                    MaterialTheme.spacing.medium16
                                ),
                                onAddInteractionButtonClick = {
                                    uiActions.onTabItemClick(1)
                                },
                            )
                        }
                    )
                }

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.large36))
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