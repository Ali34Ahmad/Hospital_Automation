package com.example.ui_components.components.bottomBars.custom

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.model.enums.BottomBarState
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.buttons.HospitalAutomationButton

@Composable
fun SendingDataBottomBar(
    text: String,
    onButtonClick: () -> Unit,
    state: BottomBarState,
    onSuccess: () -> Unit,
    modifier: Modifier ,
) {
    //add animations here
    AnimatedContent(
        targetState = state,
        modifier = Modifier.background(MaterialTheme.colorScheme.surface)
    ) { state ->
        when (state) {
            BottomBarState.IDLE -> {
                HospitalAutomationButton(
                    onClick = onButtonClick,
                    text = text,
                    modifier = Modifier
                        .navigationBarsPadding()
                        .then(modifier),
                    isLoading = false,
                    enabled = true,
                    hasError = false
                )
            }

            BottomBarState.LOADING -> {
                HospitalAutomationButton(
                    onClick = onButtonClick,
                    text = text,
                    modifier = Modifier
                        .navigationBarsPadding()
                        .then(modifier),
                    isLoading = true,
                    enabled = false,
                    hasError = false
                )
            }

            BottomBarState.FAILURE -> Unit
            BottomBarState.SUCCESS -> {
                LaunchedEffect(Unit) {
                        onSuccess()
                }
            }

            BottomBarState.DISABLED -> {
                HospitalAutomationButton(
                    onClick = onButtonClick,
                    text = text,
                    modifier = Modifier
                        .navigationBarsPadding()
                        .then(modifier),
                    enabled = false,
                )
            }
        }
    }
}

@Preview
@Composable
fun SendingDataBottomBarPreview() {
    Hospital_AutomationTheme {
        Scaffold(
            bottomBar = {
                SendingDataBottomBar(
                    onSuccess = {},
                    text = stringResource(R.string.send_data),
                    onButtonClick = {
                    },
                    state = BottomBarState.LOADING,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            MaterialTheme.spacing.medium16
                        ),
                )
            },
        ) {
            Box(modifier = Modifier.padding(it)) {}
        }
    }
}