package com.example.ui_components.components.buttons.custom

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.ui_components.components.buttons.HospitalAutomationButton
import com.example.ui_components.R
@Composable
fun AddGuardianButton(
    onClick: ()-> Unit,
    modifier: Modifier = Modifier,
) {
    HospitalAutomationButton(
        onClick = onClick,
        text = stringResource(R.string.add_guardian),
        modifier = modifier,
    )
}