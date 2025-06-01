package com.example.add_child_screen.presentation.preview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.add_child_screen.presentation.AddChildScreen
import com.example.add_child_screen.presentation.AddChildUIState
import com.example.model.enums.BottomBarState
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.util.UiText
import com.example.utility.validation.TextValidator

@DarkAndLightModePreview
@Composable
fun AddChildScreenPreview() {
    Hospital_AutomationTheme {
        var uiState by remember { mutableStateOf(AddChildUIState()) }
        AddChildScreen(
            uiState = uiState,
            onFirstNameChanged = {
                uiState = uiState.copy(firstNameErrorMessage = null)
                uiState = uiState.copy(firstName = it)
            },
            onLastNameChanged = {
                uiState = uiState.copy(lastNameErrorMessage = null)
                uiState = uiState.copy(lastName = it)
            },
            onDateOfBirthChanged = {
                uiState = uiState.copy(dateOfBirth = it)
            },
            onGenderChange = {
                uiState = uiState.copy(gender = it)
            },
            onFatherFirstNameChange = {
                uiState = uiState.copy(fatherFirstNameErrorMessage = null)
                uiState = uiState.copy(fatherFirstName = it)
            },
            onFatherLastNameChanged = {
                uiState = uiState.copy(fatherLastNameErrorMessage = null)
                uiState = uiState.copy(fatherLastName = it)
            },
            onMotherFirstNameChange = {
                uiState = uiState.copy(motherFirstNameErrorMessage = null)
                uiState = uiState.copy(motherFirstName = it)
            },
            onMotherLastNameChanged = {
                uiState = uiState.copy(motherLastNameErrorMessage = null)
                uiState = uiState.copy(motherLastName = it)
            },
            onNavigateToNextScreen = {},
            onDatePickerVisibilityChanged = {},
            onSendingData = {
                val error = TextValidator.validate(uiState.firstName)
                error?.let {
                    val text = UiText.StringResource(resId = it.string)
                    uiState = uiState.copy(firstName = "")
                    uiState = uiState.copy(firstNameErrorMessage = text)
                }
            },
            onNavigateBack = {}
        )
    }
}
@DarkAndLightModePreview
@Composable
fun AddChildScreenSuccessPreview() {
    Hospital_AutomationTheme {
        var uiState by remember { mutableStateOf(AddChildUIState(
            sendingDataButtonState = BottomBarState.SUCCESS,
            isValid = true,
            isSendingDataButtonVisible = false
        )) }
        AddChildScreen(
            uiState = uiState,
            onFirstNameChanged = {},
            onLastNameChanged = {},
            onDateOfBirthChanged = {},
            onGenderChange ={},
            onFatherFirstNameChange = {},
            onFatherLastNameChanged ={},
            onMotherFirstNameChange = {},
            onMotherLastNameChanged = {},
            onNavigateToNextScreen = {},
            onDatePickerVisibilityChanged = {},
            onSendingData = {},
            onNavigateBack = {}
        )
    }
}
