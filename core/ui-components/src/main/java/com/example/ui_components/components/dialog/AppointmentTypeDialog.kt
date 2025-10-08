package com.example.ui_components.components.dialog

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.buttons.HospitalAutomationButton
import com.example.ui_components.components.buttons.HospitalAutomationOutLinedButton
import com.example.ui_components.components.text_field.TitledTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppointmentTypeDialog(
    title: String,
    onDismissRequest: () -> Unit,
    appointmentTypeValue: String,
    onAppointmentTypeValueChanged: (String) -> Unit,
    isAppointmentTypeError: Boolean,
    durationValue: String,
    onDurationChanged: (String) -> Unit,
    isDurationError: Boolean,
    descriptionValue: String,
    onDescriptionChanged: (String) -> Unit,
    onSaveClick: () -> Unit,
    isSaveEnabled: Boolean,
    modifier: Modifier = Modifier,
    onCancelClick: () -> Unit = onDismissRequest,
    appointmentTypeSupportingText: String? = null,
    durationSupportingText: String? = null,
    titleStyle: TextStyle = MaterialTheme.typography.titleLarge
        .copy(fontWeight = FontWeight.Bold),
    titleColor: Color = MaterialTheme.colorScheme.onBackground,
    scrollState: ScrollState = rememberScrollState()
) {
    BasicAlertDialog(
        onDismissRequest = onDismissRequest,
        modifier = modifier
            .clip(RoundedCornerShape(MaterialTheme.sizing.small16))
            .background(MaterialTheme.colorScheme.surfaceContainerHigh)
            .padding(
                MaterialTheme.spacing.large24,
            ),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.verticalScroll(scrollState)
        ) {
            //Title
            Text(
                text = title,
                style = titleStyle,
                color = titleColor
            )
            Spacer(Modifier.height(MaterialTheme.spacing.large32))
            //Appointment type
            TitledTextField(
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                title = stringResource(R.string.appointment_type),
                value = appointmentTypeValue,
                onValueChanged = onAppointmentTypeValueChanged,
                isError = isAppointmentTypeError,
                supportingText = appointmentTypeSupportingText,
                isRequired = true,
                placeholder = R.string.name_of_service,
            )
            Spacer(Modifier.height(MaterialTheme.spacing.small12))
            //Duration
            TitledTextField(
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Number
                ),
                title = stringResource(R.string.minutes),
                value = durationValue,
                onValueChanged = onDurationChanged,
                isError = isDurationError,
                supportingText = durationSupportingText,
                isRequired = true,
                placeholder = R.string.duration_example,
            )
            Spacer(Modifier.height(MaterialTheme.spacing.small12))
            //Description
            TitledTextField(
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                title = stringResource(R.string.description),
                value = descriptionValue,
                onValueChanged = onDescriptionChanged,
                isError = false,
                isRequired = false,
                placeholder = R.string.short_description,
                minLines = 2,
            )
            Spacer(Modifier.height(MaterialTheme.spacing.large32))
            //actions
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                //Cancel
                HospitalAutomationOutLinedButton(
                    onClick = onCancelClick,
                    text = stringResource(R.string.cancel),
                )
                Spacer(Modifier.width(MaterialTheme.spacing.medium16))
                //Save
                HospitalAutomationButton(
                    enabled = isSaveEnabled,
                    onClick = onSaveClick,
                    text = stringResource(R.string.save)
                )
        }

        }
    }
}


@DarkAndLightModePreview
@Composable
fun AppointmentTypeDialogPreview() {
    Hospital_AutomationTheme {
        AppointmentTypeDialog(
            isSaveEnabled = false,
            title = stringResource(R.string.edit_appointment_type),
            onDismissRequest = {},
            appointmentTypeValue = "",
            onAppointmentTypeValueChanged = {},
            isAppointmentTypeError = false,
            durationValue = "",
            onDurationChanged = {},
            isDurationError = true,
            descriptionValue = "",
            onDescriptionChanged = { },
            onSaveClick = {},
            durationSupportingText = stringResource(R.string.supporting_text),
        )
    }
}