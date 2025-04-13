package com.example.ui_components.components.buttons

import androidx.annotation.StringRes
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui_components.R

@Composable
fun HospitalAutomationTextButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    @StringRes text: Int
) {
    TextButton(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.onSurface)
    ) {
        Text(
            stringResource(text),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HospitalAutomationTextButtonPreview() {
    Hospital_AutomationTheme {
        HospitalAutomationTextButton(
            onClick = {},
            text = R.string.login
        )
    }
}