package com.example.ui_components.components.text_field

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.additionalColorScheme
import com.example.ui.theme.spacing
import com.example.ui_components.R

@Composable
fun TitledTextField(
    title: String,
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    supportingText: String? = null,
    isRequired: Boolean = false,
    @StringRes label: Int? = null,
    @StringRes placeholder: Int? = null,
    minLines: Int = 1,
    maxLines: Int = minLines,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    @DrawableRes trailingIcon: Int? = null,
    onTrailingIconClick: () -> Unit = {},
    titleStyle: TextStyle = MaterialTheme.typography.titleMedium,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    val unFocusedTitleColor = MaterialTheme.additionalColorScheme.onSurfaceContainerVariant
    val focusedTitleColor = MaterialTheme.colorScheme.primary

    val isFocused by interactionSource.collectIsFocusedAsState()
    val titleColor = if(isFocused) focusedTitleColor else unFocusedTitleColor

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small8)
    ) {


        //Title
        Text(
            text = title,
            style = titleStyle,
            color = titleColor
        )

        //TextField
        HospitalAutomationTextFiled(
            keyboardOptions = keyboardOptions,
            interactionSource = interactionSource,
            modifier = Modifier.fillMaxWidth(),
            value = value,
            onValueChange = onValueChanged,
            isError = isError,
            isRequired = isRequired,
            label = label,
            placeholder = placeholder,
            trailingIcon = trailingIcon,
            onTrailingIconClick = onTrailingIconClick,
            supportingText = supportingText,
            minLines = minLines,
            maxLines = maxLines,
            enabled = enabled,
            readOnly = readOnly
        )
    }
}

@Preview
@Composable
fun TitledTextFieldPreview() {
    Hospital_AutomationTheme {
        var value by remember { mutableStateOf("") }
        Surface(

        ) {
            TitledTextField(
                modifier = Modifier.padding(MaterialTheme.spacing.medium16),
                title = stringResource(R.string.appointment_type),
                value = value,
                onValueChanged = {value = it},
                placeholder = R.string.name_of_service
            )
        }
    }
}