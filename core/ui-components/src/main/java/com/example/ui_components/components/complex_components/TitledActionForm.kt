package com.example.ui_components.components.complex_components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.text_field.HospitalAutomationTextFiled
import com.example.ui_components.icons.HospitalAutomationIcons
import com.example.model.TextFieldData

@Composable
fun TitledActionForm(
    @StringRes title: Int,
    @DrawableRes icon: Int,
    onActionClick: () -> Unit,
    textFields: List<TextFieldData>,
    modifier: Modifier = Modifier,
) {
    var enabled by remember { mutableStateOf(true) }
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        //title
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(title),
                style = MaterialTheme.typography.titleSmall
            )
            IconButton(
                onClick = {
                    onActionClick()
                    enabled = false
                },
                modifier = Modifier
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .size(MaterialTheme.sizing.small24)
                    .padding(MaterialTheme.spacing.extraSmall4)
            ) {
                Icon(
                    painter = painterResource(icon),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }

        Spacer(Modifier.height(MaterialTheme.spacing.medium16))

        textFields.forEachIndexed {index , textField->
            HospitalAutomationTextFiled(
                modifier = Modifier.fillMaxWidth(),
                value = textField.value,
                onValueChange = textField.onValueChange,
                isRequired = textField.isRequired,
                label = textField.label,
                minLines = textField.minLines,
                enabled = enabled
            )
            if (index < textFields.size-1)
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.large24))
        }
    }

}

@Preview
@Composable
fun TitledActionFormPreview() {
    Hospital_AutomationTheme {
        var text1 by remember { mutableStateOf("") }
        var text2 by remember { mutableStateOf("") }
        TitledActionForm(
            title = R.string.interactions,
            icon = HospitalAutomationIcons.add,
            onActionClick = {},
            textFields = listOf(
                TextFieldData(
                    value = text1,
                    onValueChange = {text1 = it},
                    isRequired = true,
                    label = R.string.interaction_name
                ),
                TextFieldData(
                    value = text2,
                    onValueChange = {text2 = it},
                    isRequired = true,
                    label = R.string.interaction_description,
                    minLines = 2,
                ),
            )
            ,
        )
    }
}