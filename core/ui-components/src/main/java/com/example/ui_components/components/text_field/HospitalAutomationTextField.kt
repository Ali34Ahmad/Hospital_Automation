package com.example.ui_components.components.text_field

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
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
import androidx.compose.ui.tooling.preview.Preview
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.additionalColorScheme
import com.example.ui_components.R
import com.example.ui_components.icons.HospitalAutomationIcons

@Composable
fun HospitalAutomationTextFiled(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean = false,
    isRequired: Boolean = false,
    @StringRes label : Int? = null,
    @StringRes placeholder : Int? = null,
    @DrawableRes icon: Int? = null,
    @StringRes supportingText: Int? = null,
    minLines: Int = 1,
    maxLines: Int = minLines,
    enabled: Boolean = true
) {

        TextField(
            modifier = modifier,
            shape = MaterialTheme.shapes.extraSmall,
            value = value,
            onValueChange = onValueChange,
            label = {
                label?.let {
                    val text = buildString {
                        append(stringResource(label))
                        if(isRequired)
                            append("*")
                    }
                    Text(
                        text,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.additionalColorScheme.onBackgroundVariantLight
                    )
                }
            },
            trailingIcon = {
                if (icon != null)
                    Icon(painter = painterResource(icon), null)
            },
            placeholder = {
                placeholder?.let {
                    Text(
                        text =  stringResource(it)
                    )
                }

            },
            isError = isError,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.background,
                unfocusedContainerColor = MaterialTheme.colorScheme.background,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,

            ),
            supportingText = {
                if(supportingText!=null){
                    Text(stringResource(supportingText))
                }
            },
            minLines = minLines,
            maxLines = maxLines,
            enabled = enabled
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
            icon = null,
            isRequired = false,
            supportingText = null,
            enabled = false
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
            icon = HospitalAutomationIcons.calender,
            isRequired = true,
            supportingText = R.string.supporting_text,
        )
    }
}






