package com.example.ui_components.components.buttons

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui_components.R
import com.example.ui_components.theme.Hospital_AutomationTheme

@Composable
fun HospitalAutomationButton(
    onClick : () -> Unit,
    @StringRes text : Int,
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier
            .height(40.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp)),
        onClick = onClick
    ) {
        Text(
            stringResource(text),
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HospitalAutomationButtonPreview() {
    Hospital_AutomationTheme {
        HospitalAutomationButton(
            onClick = {},
            modifier = Modifier.width(380.dp),
            text = R.string.signup
        )
    }
}