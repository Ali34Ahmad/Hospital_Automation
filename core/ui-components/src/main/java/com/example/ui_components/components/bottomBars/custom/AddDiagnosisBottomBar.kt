package com.example.ui_components.components.bottomBars.custom

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.model.enums.BottomBarState
import com.example.ui.theme.spacing
import com.example.ui_components.components.buttons.HospitalAutomationOutLinedButton
import com.example.ui_components.R
import com.example.ui_components.components.buttons.HospitalAutomationButton

@Composable
fun AddDiagnosisBottomBar(
    state: BottomBarState,
    sendData: ()-> Unit,
    onFinish:() -> Unit,
    onAddPrescription:()-> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
    ) {
        AnimatedContent(
            state != BottomBarState.SUCCESS,
            transitionSpec = {
                slideInVertically(
                    initialOffsetY = { it.times(1.5f).toInt() }
                ).togetherWith(slideOutVertically(
                    targetOffsetY = { it.times(1.5f).toInt() }
                ))
            }
        ) {target->
            when(target){
                true ->
                    AnimatedContent(
                        state
                    ) {target->
                        when (target) {
                            BottomBarState.IDLE -> {
                                HospitalAutomationButton(
                                    onClick = sendData,
                                    text = stringResource(R.string.add_prescription),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(MaterialTheme.spacing.medium16),
                                )
                            }

                            BottomBarState.LOADING -> {
                                HospitalAutomationButton(
                                    onClick = sendData,
                                    text = stringResource(R.string.add_prescription),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(MaterialTheme.spacing.medium16),
                                    isLoading = true,
                                    enabled = false
                                )
                            }

                            BottomBarState.FAILURE -> {
                                HospitalAutomationButton(
                                    onClick = sendData,
                                    text = stringResource(R.string.something_went_wrong),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(MaterialTheme.spacing.medium16),
                                    enabled = false,
                                    hasError = true
                                )
                            }

                            BottomBarState.DISABLED -> {
                                HospitalAutomationButton(
                                    onClick = sendData,
                                    text = stringResource(R.string.add_prescription),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(MaterialTheme.spacing.medium16),
                                    enabled = false,
                                )
                            }

                            else -> Unit
                        }
                    }
                false -> {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium16)
                    ) {
                        HospitalAutomationOutLinedButton(
                            onClick = onAddPrescription,
                            text = stringResource(R.string.add_prescription),
                            modifier = Modifier.weight(1f),
                        )
                        HospitalAutomationButton(
                            onClick = onFinish,
                            text = stringResource(R.string.finish),
                            modifier = Modifier.weight(1f),
                        )
                    }
                }
            }
        }
    }
}