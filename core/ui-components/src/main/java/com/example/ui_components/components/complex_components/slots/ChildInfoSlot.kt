package com.example.ui_components.components.complex_components.slots

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.ext.toAppropriateFormat
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.dialog.DatePickerDialog
import com.example.ui_components.components.text_field.HospitalAutomationTextFiled
import com.example.ui_components.icons.HospitalAutomationIcons
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState

@Composable
fun ChildInfoSlot(
    firstName: String,
    onFirstNameChanged: (String)-> Unit,
    lastName: String,
    onLastNameChanged: (String)-> Unit,
    dateOfBirth: String,
    onDateOfBirthChanged: (String)-> Unit,
    modifier: Modifier = Modifier,
    firstNameError: String? = null,
    lastNameError: String? = null,
    dateOfBirthError: String? = null,
    isDatePickerShown: Boolean,
    onDatePickerVisibilityChanged: (Boolean) -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(R.string.child_info),
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(Modifier.height(MaterialTheme.spacing.medium16))
        HospitalAutomationTextFiled(
            isError = firstNameError!= null,
            supportingText = firstNameError,
            modifier = Modifier.fillMaxWidth(),
            value = firstName,
            onValueChange = onFirstNameChanged,
            isRequired = true,
            label = R.string.first_name,
        )
        Spacer(Modifier.height(MaterialTheme.spacing.large24))
        HospitalAutomationTextFiled(
            isError = lastNameError!= null,
            supportingText = lastNameError,
            modifier = Modifier.fillMaxWidth(),
            value = lastName,
            onValueChange = onLastNameChanged,
            isRequired = true,
            label = R.string.last_name,
        )
        Spacer(Modifier.height(MaterialTheme.spacing.large24))
        HospitalAutomationTextFiled(
            isError = dateOfBirthError!= null,
            supportingText = dateOfBirthError,
            modifier = Modifier.fillMaxWidth()
                .focusRequester(
                    focusRequester
                )
                .onFocusChanged{
                    onDatePickerVisibilityChanged(it.hasFocus)
                }
                .focusable(),
            value = dateOfBirth,
            onValueChange = {},
            icon = HospitalAutomationIcons.calender,
            isRequired = true,
            label = R.string.date_of_birth,
            readOnly = true
        )
    }
    AnimatedVisibility(
        isDatePickerShown,
        enter = fadeIn() + scaleIn(),
        exit = fadeOut() + scaleOut()
    ){
        Surface {
            DatePickerDialog(
                onConfirm = {
                    //to appropriat fromat
                    onDateOfBirthChanged(it.toAppropriateFormat())
                    onDatePickerVisibilityChanged(false)
                },
                datePickerState = rememberUseCaseState(
                    visible = true,
                    onCloseRequest = {
                        onDatePickerVisibilityChanged(false)
                    }
                )
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun ChildInfoSlotPreview() {
    Hospital_AutomationTheme {
        var date by remember { mutableStateOf("") }
        var isDatePickerShown by remember { mutableStateOf(false) }
        Surface(modifier = Modifier.fillMaxSize()) {
            ChildInfoSlot(
                firstName = "$isDatePickerShown",
                onFirstNameChanged = {},
                lastName = "",
                onLastNameChanged = {},
                dateOfBirth = date,
                onDateOfBirthChanged = {
                    date = it
                },
                modifier = Modifier.padding(MaterialTheme.spacing.medium16),
                onDatePickerVisibilityChanged = {
                    isDatePickerShown = it
                },
                isDatePickerShown = isDatePickerShown
            )
        }
    }
}