package com.example.ui_components.components.bottomBars.custom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.model.enums.BottomBarState
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.buttons.HospitalAutomationButton
import com.example.ui_components.components.buttons.HospitalAutomationOutLinedButton

@Composable
fun MedicinesSearchBottomBar(
    onFirstButtonClick: () -> Unit,
    onSecondButtonClick: () -> Unit,
    firstButtonState: BottomBarState,
    secondButtonState: BottomBarState,
    modifier: Modifier = Modifier,
    firstButtonText: String = stringResource(R.string.clear),
    secondButtonText: String = stringResource(R.string.finish),
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.large24),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        HospitalAutomationOutLinedButton(
            onClick = onFirstButtonClick,
            text = firstButtonText,
            modifier = Modifier.weight(1f),
            isLoading = firstButtonState == BottomBarState.LOADING,
            enabled = firstButtonState != BottomBarState.DISABLED && firstButtonState != BottomBarState.LOADING,
        )
        HospitalAutomationButton(
            onClick = onSecondButtonClick,
            text = secondButtonText,
            modifier = Modifier.weight(1f),
            isLoading = secondButtonState == BottomBarState.LOADING,
            enabled = secondButtonState != BottomBarState.DISABLED && secondButtonState != BottomBarState.LOADING
        )
    }
}

@Preview(showBackground = true,)
@Composable
fun MedicinesSearchBottomBarPreview() {
    Hospital_AutomationTheme {
        Surface(Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.surface)) {
            MedicinesSearchBottomBar(
                onFirstButtonClick = {},
                onSecondButtonClick = {},
                firstButtonState = BottomBarState.IDLE,
                secondButtonState = BottomBarState.IDLE,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.medium16),
            )
        }
    }
}