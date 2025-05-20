package com.example.ui_components.components.complex_components.slots

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.text_field.HospitalAutomationTextFiled

@Composable
fun ParentInfoSlots(
    fatherFirstName: String,
    onFatherFirstNameChange: (String)-> Unit,
    fatherLastName: String,
    onFatherLastNameChanged: (String)-> Unit,
    motherFirstName: String,
    onMotherFirstNameChange: (String)-> Unit,
    motherLastName: String,
    onMotherLastNameChanged: (String)-> Unit,
    modifier: Modifier = Modifier,
    fatherFirstNameErrorMessage: String? = null,
    fatherLastNameErrorMessage: String? = null,
    motherFirstNameErrorMessage: String? = null,
    motherLastNameErrorMessage: String? = null,

) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(R.string.parents_info),
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(Modifier.height(MaterialTheme.spacing.medium16))
        HospitalAutomationTextFiled(
            modifier = Modifier.fillMaxWidth(),
            value = fatherFirstName,
            onValueChange = onFatherFirstNameChange,
            isRequired = true,
            label = R.string.father_first_name,
            isError = fatherFirstNameErrorMessage != null,
            supportingText = fatherFirstNameErrorMessage
        )
        Spacer(Modifier.height(MaterialTheme.spacing.large24))
        HospitalAutomationTextFiled(
            modifier = Modifier.fillMaxWidth(),
            value = fatherLastName,
            onValueChange = onFatherLastNameChanged,
            isRequired = true,
            label = R.string.father_last_name,
            isError = fatherLastNameErrorMessage != null,
            supportingText = fatherLastNameErrorMessage
        )
        Spacer(Modifier.height(MaterialTheme.spacing.large24))
        HospitalAutomationTextFiled(
            modifier = Modifier.fillMaxWidth(),
            value = motherFirstName,
            onValueChange = onMotherFirstNameChange,
            isRequired = true,
            label = R.string.mother_first_name,
            isError = motherFirstNameErrorMessage != null,
            supportingText = motherFirstNameErrorMessage
        )
        Spacer(Modifier.height(MaterialTheme.spacing.large24))
        HospitalAutomationTextFiled(
            modifier = Modifier.fillMaxWidth(),
            value = motherLastName,
            onValueChange = onMotherLastNameChanged,
            isRequired = true,
            label = R.string.mother_last_name,
            isError = motherLastNameErrorMessage != null,
            supportingText = motherLastNameErrorMessage
        )
    }
}

@DarkAndLightModePreview
@Composable
fun ParentsInfoSlotsPreview() {
    Hospital_AutomationTheme {
        Surface {
            ParentInfoSlots(
                fatherFirstName = "",
                onFatherFirstNameChange = { },
                fatherLastName = "",
                onFatherLastNameChanged = {},
                motherFirstName = "",
                onMotherFirstNameChange = {},
                motherLastName = "",
                onMotherLastNameChanged = {},
                modifier = Modifier.padding(MaterialTheme.spacing.medium16)
            )
        }
    }
}