package com.example.ui_components.components.text_field

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.unit.dp
import com.example.ui_components.R
import com.example.ui_components.icons.HospitalAutomationIcons
import com.example.ui.theme.Hospital_AutomationTheme

@Composable
fun HospitalAutomationTextFiled(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean = false,
    isRequired: Boolean = false,
    @StringRes label : Int?,
    @DrawableRes icon: Int?,
    @StringRes supportingText: Int?,
    maxLines: Int = 1
) {

        TextField(
            modifier = modifier.fillMaxWidth(1f),
            shape = MaterialTheme.shapes.small,
            value = value,
            maxLines = maxLines,
            onValueChange = onValueChange,
            label = {
                label?.let {
                    val text = buildString {
                        append(stringResource(label))
                        if(isRequired)
                            append("*")
                    }
                    Text(text)
                }
            },
            trailingIcon = {
                if (icon != null)
                    Icon(painter = painterResource(icon), null)
            },
            isError = isError,
            colors = TextFieldDefaults.colors(
                disabledContainerColor = Color(0xff99999F),
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
            ),
            supportingText = {
                if(supportingText!=null){
                    Text(stringResource(supportingText))
                }
            },
        )
}

@Preview(showBackground = true)
@Composable
fun HospitalAutomationTextFiledPreview() {
    Hospital_AutomationTheme {
        var value by remember {
            mutableStateOf(value = "")
        }
        HospitalAutomationTextFiled(
            value = value,
            onValueChange = {
                value = it
            },
            modifier = Modifier.width(380.dp),
            label = R.string.first_name,
            icon = null,
            isRequired = false,
            supportingText = null,
        )
    }
}
@Preview(showBackground = true)
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
            modifier = Modifier.width(380.dp),
            label = R.string.first_name,
            icon = HospitalAutomationIcons.calender,
            isRequired = true,
            supportingText = R.string.supporting_text,
        )
    }
}










