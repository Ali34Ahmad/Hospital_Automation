package com.example.ui_components.components.bottomBars.custom

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.model.doctor.appointment.AppointmentState
import com.example.model.enums.BottomBarState
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.buttons.HospitalAutomationButton
import com.example.ui_components.components.buttons.HospitalAutomationOutLinedButton

@Composable
fun AppointmentBottomBar(
    markAsMissedButtonState: BottomBarState,
    markAsPassedButtonState: BottomBarState,
    onMarkAsMissed: () -> Unit,
    onMarkAsPassed: ()-> Unit,
    onMissedMarkingSucceeded: () -> Unit,
    onPassedMarkingSucceeded: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium16)
    ) {

            AnimatedContent(
                modifier = Modifier.weight(1f),
                targetState = markAsMissedButtonState,
                transitionSpec = {
                    fadeIn(tween(300)).togetherWith(fadeOut(tween(300)))
                },
                label = "Button state animation"
            ) { state ->
                when (state) {
                    BottomBarState.IDLE -> {
                        HospitalAutomationOutLinedButton(
                            onClick = onMarkAsMissed,
                            text = stringResource(R.string.mark_as_missed),
                        )
                    }

                    BottomBarState.LOADING -> {
                        HospitalAutomationOutLinedButton(
                            onClick = { },
                            text = stringResource(R.string.mark_as_missed),
                            isLoading = true,
                        )
                    }

                    BottomBarState.SUCCESS -> {
                        LaunchedEffect(Unit) {
                            onMissedMarkingSucceeded()
                        }
                        HospitalAutomationOutLinedButton(
                            onClick = onMarkAsMissed,
                            text = stringResource(R.string.mark_as_missed),
                        )
                    }

                    BottomBarState.FAILURE -> {
                        HospitalAutomationOutLinedButton(
                            onClick = onMarkAsMissed,
                            text = stringResource(R.string.error),
                            enabled = false,
                            hasError = true
                        )
                    }

                    BottomBarState.DISABLED -> Unit
                }
            }


            AnimatedContent(
                modifier = Modifier.weight(1f),
                targetState = markAsPassedButtonState,
                transitionSpec = {
                    fadeIn(tween(300)).togetherWith(fadeOut(tween(300)))
                },
                label = "Button state animation"
            ) { state ->
                when (state) {
                    BottomBarState.IDLE -> {
                        HospitalAutomationButton(
                            onClick = onMarkAsPassed,
                            text = stringResource(R.string.mark_as_passed),
                        )
                    }
                    BottomBarState.LOADING -> {
                        HospitalAutomationButton(
                            onClick = onMarkAsPassed,
                            text = stringResource(R.string.mark_as_passed),
                            isLoading = true,
                            enabled = false
                        )
                    }

                    BottomBarState.SUCCESS -> {
                        LaunchedEffect(Unit) {
                            onPassedMarkingSucceeded()
                        }
                        HospitalAutomationButton(
                            onClick = onMarkAsPassed,
                            text = stringResource(R.string.mark_as_passed),
                        )
                    }

                    BottomBarState.FAILURE -> {
                        HospitalAutomationButton(
                            onClick = onMarkAsPassed,
                            text = stringResource(R.string.error),
                            enabled = false,
                            hasError = true
                        )
                    }

                    BottomBarState.DISABLED -> Unit
                }
            }
        }

}

@Preview(showSystemUi = true)
@Composable
fun AppointmentBottomBarUpcomingPreview() {
    Hospital_AutomationTheme {
        AppointmentBottomBar(
            markAsMissedButtonState = BottomBarState.IDLE,
            markAsPassedButtonState = BottomBarState.IDLE,
            onMarkAsMissed = {},
            onMarkAsPassed = {},
            onMissedMarkingSucceeded = {},
            onPassedMarkingSucceeded = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.medium16)
        )
    }
}
@Preview(showSystemUi = true)
@Composable
fun AppointmentBottomBarPassedPreview() {
    Hospital_AutomationTheme {
        AppointmentBottomBar(
            markAsMissedButtonState = BottomBarState.IDLE,
            markAsPassedButtonState = BottomBarState.IDLE,
            onMarkAsMissed = {},
            onMarkAsPassed = {},
            onMissedMarkingSucceeded = {},
            onPassedMarkingSucceeded = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.medium16)
        )
    }
}
@Preview(showSystemUi = true)
@Composable
fun AppointmentBottomBarMissedPreview() {
    Hospital_AutomationTheme {
        AppointmentBottomBar(
            markAsMissedButtonState = BottomBarState.IDLE,
            markAsPassedButtonState = BottomBarState.IDLE,
            onMarkAsMissed = {},
            onMarkAsPassed = {},
            onMissedMarkingSucceeded = {},
            onPassedMarkingSucceeded = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.medium16)
        )
    }
}