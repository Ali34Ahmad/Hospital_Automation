package com.example.add_child_screen.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.constants.enums.Gender
import com.example.ui.theme.spacing
import com.example.ui_components.components.bottomBars.custom.SendingDataBottomBar
import com.example.ui_components.components.complex_components.slots.ChildInfoSlot
import com.example.ui_components.components.complex_components.slots.ParentInfoSlots
import com.example.ui_components.components.complex_components.slots.SelectGenderSlot
import com.example.ui_components.components.topbars.custom.AddChildTopBar

import com.example.util.UiText
import com.example.utility.validation.validator.TextValidator
import kotlinx.coroutines.CoroutineScope
import com.example.ui_components.R

@Composable
fun AddChildScreen(
    viewModel: AddChildViewModel,
    onAction: (AddChildUIActions) -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    AddChildScreen(
        uiState = uiState,
        modifier = modifier,
        onFirstNameChanged = {
            onAction(AddChildUIActions.OnFirstNameChanged(it))
        },
        onLastNameChanged = {
            onAction(AddChildUIActions.OnLastNameChanged(it))
        },
        onDateOfBirthChanged = {
            onAction(AddChildUIActions.OnDateChanged(it))
        },
        onGenderChange = {
            onAction(AddChildUIActions.OnGenderChanged(it))
        },
        onFatherFirstNameChange = {
            onAction(AddChildUIActions.OnFatherFirstNameChanged(it))
        },
        onFatherLastNameChanged = {
            onAction(AddChildUIActions.OnFatherLastNameChanged(it))
        },
        onMotherFirstNameChange = {
            onAction(AddChildUIActions.OnMotherFirstNameChanged(it))
        },
        onMotherLastNameChanged = {
            onAction(AddChildUIActions.OnMotherLastNameChanged(it))
        },
        onSendingData = {
            onAction(AddChildUIActions.SendData)
        },
        onNavigateToNextScreen = {
            onAction(AddChildUIActions.NavigateToNextScreen)
        },
        onNavigateBack = {
            onAction(AddChildUIActions.NavigateBack)
        },
        onDatePickerVisibilityChanged = {
            onAction(AddChildUIActions.ChangeDatePickerVisibility(it))
        }
    )
}

@Composable
fun AddChildScreen(
    uiState: AddChildUIState,
    onFirstNameChanged: (String) -> Unit,
    onLastNameChanged: (String) -> Unit,
    onDateOfBirthChanged: (String) -> Unit,
    onGenderChange: (Gender) -> Unit,
    onFatherFirstNameChange: (String) -> Unit,
    onFatherLastNameChanged: (String) -> Unit,
    onMotherFirstNameChange: (String) -> Unit,
    onMotherLastNameChanged: (String) -> Unit,
    onNavigateToNextScreen: (childId: Int) -> Unit,
    onDatePickerVisibilityChanged: (Boolean) -> Unit,
    onSendingData: () -> Unit,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            SendingDataBottomBar(
                text = stringResource(R.string.send_data),
                onButtonClick = onSendingData,
                state = uiState.sendingDataButtonState,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.medium16),
                onSuccess = {
                    uiState.childId?.let {
                        onNavigateToNextScreen(it)
                    }
                }
            )
        },
        topBar = {
            AddChildTopBar(
                modifier = Modifier.fillMaxWidth(),
                onNavigateBack = onNavigateBack
            )
        }
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(innerPadding)
                .padding(MaterialTheme.spacing.medium16)
                .verticalScroll(
                    state = rememberScrollState()
                )
        ) {
            ChildInfoSlot(
                firstName = uiState.firstName,
                onFirstNameChanged = onFirstNameChanged,
                lastName = uiState.lastName,
                onLastNameChanged = onLastNameChanged,
                dateOfBirth = uiState.dateOfBirth,
                onDateOfBirthChanged = onDateOfBirthChanged,
                modifier = Modifier.fillMaxWidth(),
                firstNameError = uiState.firstNameErrorMessage?.asString(),
                lastNameError = uiState.lastNameErrorMessage?.asString(),
                dateOfBirthError = uiState.dateOfBirthErrorMessage?.asString(),
                isDatePickerShown = uiState.isDatePickerVisible,
                onDatePickerVisibilityChanged = onDatePickerVisibilityChanged
            )
            Spacer(Modifier.height(MaterialTheme.spacing.large24))
            SelectGenderSlot(
                gender = uiState.gender,
                onGenderChange = onGenderChange,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(MaterialTheme.spacing.large24))
            ParentInfoSlots(
                modifier = Modifier.fillMaxWidth(),
                fatherFirstName = uiState.fatherFirstName,
                onFatherFirstNameChange = onFatherFirstNameChange,
                fatherLastName = uiState.fatherLastName,
                onFatherLastNameChanged = onFatherLastNameChanged,
                motherFirstName = uiState.motherFirstName,
                onMotherFirstNameChange = onMotherFirstNameChange,
                motherLastName = uiState.motherLastName,
                onMotherLastNameChanged = onMotherLastNameChanged,
                fatherFirstNameErrorMessage = uiState.fatherFirstNameErrorMessage?.asString(),
                fatherLastNameErrorMessage = uiState.fatherLastNameErrorMessage?.asString(),
                motherFirstNameErrorMessage = uiState.motherFirstNameErrorMessage?.asString(),
                motherLastNameErrorMessage = uiState.motherLastNameErrorMessage?.asString(),
            )
            Spacer(Modifier.height(MaterialTheme.spacing.large24))
        }
    }
}