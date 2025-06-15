package com.example.ui_components.components.text_field

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui_components.R
import com.example.constants.icons.AppIcons

@Composable
fun HospitalAutomationTextFiled(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean = false,
    isRequired: Boolean = false,
    @StringRes label: Int? = null,
    @StringRes placeholder: Int? = null,
    @DrawableRes trailingIcon: Int? = null,
    onTrailingIconClick: () -> Unit = {},
    supportingText: String? = null,
    minLines: Int = 1,
    maxLines: Int = minLines,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        imeAction = ImeAction.Next,
    ),
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    val singleLine = maxLines <= 1
    TextField(
        modifier = modifier,
        readOnly = readOnly,
        keyboardOptions = keyboardOptions,
        shape = MaterialTheme.shapes.extraSmall,
        value = value,
        onValueChange = onValueChange,
        visualTransformation = visualTransformation,
        label = {
            label?.let {
                val text = buildString {
                    append(stringResource(label))
                    if (isRequired)
                        append("*")
                }
                Text(
                    text,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        },
        trailingIcon = {
            if (trailingIcon != null) {
                IconButton(
                    onClick = onTrailingIconClick,
                    enabled = enabled
                ) {
                    Icon(painter = painterResource(trailingIcon), null)
                }
            }
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.background,
            unfocusedContainerColor = MaterialTheme.colorScheme.background,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            errorLabelColor = MaterialTheme.colorScheme.error,
            errorCursorColor = MaterialTheme.colorScheme.error,
            errorSupportingTextColor = MaterialTheme.colorScheme.error,
            errorContainerColor = MaterialTheme.colorScheme.background,
            errorTextColor = MaterialTheme.colorScheme.onBackground,
            errorPlaceholderColor = MaterialTheme.colorScheme.error,
        ),
        placeholder = {
            placeholder?.let {
                Text(
                    text = stringResource(it)
                )
            }

        },
        isError = isError,
        supportingText = {
            if (supportingText != null) {
                Text(supportingText)
            }
        },
        minLines = minLines,
        maxLines = maxLines,
        singleLine = singleLine,
        enabled = enabled,
    )
}

@Preview
@Composable
fun HospitalAutomationTextFiledPreview() {
    Hospital_AutomationTheme {
        var value by remember {
            mutableStateOf(value = "Ali Mansoura")
        }
        HospitalAutomationTextFiled(
            value = value,
            onValueChange = {
                value = it
            },
            modifier = Modifier.fillMaxWidth(),
            label = R.string.first_name,
            trailingIcon = null,
            isRequired = false,
            supportingText = null,
            enabled = false,
            onTrailingIconClick = {}
        )
    }
}


@DarkAndLightModePreview
@Composable
fun HospitalAutomationTextFiled2Preview() {
    Hospital_AutomationTheme {
        var value by remember {
            mutableStateOf(value = "")
        }
        HospitalAutomationTextFiled(
            value = value,
            onValueChange = {
                value = it
            },
            modifier = Modifier.fillMaxWidth(),
            label = R.string.first_name,
            trailingIcon = AppIcons.Outlined.calender,
            isRequired = true,
            supportingText = stringResource(R.string.supporting_text),
            onTrailingIconClick = {}
        )
    }
}

@DarkAndLightModePreview
@Composable
fun HospitalAutomationTextFiledErrorPreview() {
    Hospital_AutomationTheme {
        var value by remember {
            mutableStateOf(value = "")
        }
        HospitalAutomationTextFiled(
            value = value,
            onValueChange = {
                value = it
            },
            modifier = Modifier.fillMaxWidth(),
            label = R.string.first_name,
            trailingIcon = AppIcons.Outlined.calender,
            isRequired = true,
            supportingText = stringResource(R.string.supporting_text),
            isError = true,
            readOnly = true,
            placeholder = R.string.first_name,
            onTrailingIconClick = {}
        )
    }
}






