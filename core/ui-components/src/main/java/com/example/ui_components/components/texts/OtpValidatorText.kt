package com.example.ui_components.components.texts

import androidx.annotation.StringRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.ui_components.R
import com.example.ui_components.theme.Hospital_AutomationTheme

@Composable
fun OtpValidatorText(
    @StringRes text: Int,
    isValid: Boolean,
    modifier: Modifier = Modifier,
) {
    val color = if(isValid) MaterialTheme.colorScheme.primary
        else MaterialTheme.colorScheme.error

    Text(
        text = stringResource(text),
        style = MaterialTheme.typography.labelLarge,
        color = color,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun OtpValidatorTextPreview() {
    Hospital_AutomationTheme {
        OtpValidatorText(
            text = R.string.supporting_text,
            isValid = true
        )
    }
}
@Preview(showBackground = true)
@Composable
fun OtpValidatorTextErrorPreview() {
    Hospital_AutomationTheme {
        OtpValidatorText(
            text = R.string.supporting_text,
            isValid = false
        )
    }
}