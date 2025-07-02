package com.example.ui_components.components.bottomBars.custom

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.model.enums.BottomBarState
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.buttons.HospitalAutomationButton

@Composable
fun ClinicDetailsBottomBar(
    title: String,
    state: BottomBarState,
    onClick: () -> Unit,
    onSuccess: ()-> Unit,
    modifier: Modifier = Modifier,
) {
    AnimatedContent(
        targetState = state,
        modifier = modifier,
        transitionSpec = {
            slideInVertically(
                initialOffsetY = {it}
            ).togetherWith(slideOutVertically(
                targetOffsetY = { it }
            ))
        }
    ) {target->
        when(target){
            BottomBarState.DISABLED->{
                HospitalAutomationButton(
                    onClick = onClick,
                    text = title,
                    modifier = Modifier.fillMaxWidth(),
                    enabled = false
                )
            }
            BottomBarState.IDLE ->{
                HospitalAutomationButton(
                    onClick = onClick,
                    text = title,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
            BottomBarState.LOADING ->{
                HospitalAutomationButton(
                    onClick = onClick,
                    text = title,
                    modifier = Modifier.fillMaxWidth(),
                    isLoading = true,
                    enabled = false
                )
            }
            BottomBarState.SUCCESS ->{
                LaunchedEffect(Unit) {
                    onSuccess()
                }
            }
            else -> Unit
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ClinicDetailsBottomBarPreview() {
    Hospital_AutomationTheme {
        var state by remember { mutableStateOf(BottomBarState.IDLE) }
        ClinicDetailsBottomBar(
            title = stringResource(R.string.send_request),
            state = state,
            onClick = {},
            onSuccess = {},
            modifier = Modifier.padding(MaterialTheme.spacing.medium16)
        )
    }
}