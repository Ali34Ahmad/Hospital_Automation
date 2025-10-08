package com.example.ui_components.components.dialog

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.additionalShapes
import com.example.ui.theme.spacing
import com.example.ui_components.R
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerDialog(
    onConfirm: (LocalTime) -> Unit,
    onDismiss: () -> Unit,
    timePickerState: TimePickerState = rememberTimePickerState(),
    @StringRes title: Int = R.string.select_start_time
) {

    Dialog(
        onDismissRequest = onDismiss,
    ) {
        Surface(
            modifier = Modifier
                .clip(MaterialTheme.additionalShapes.small12)
                .background(
                    color = MaterialTheme.colorScheme.surfaceContainerLow,
                ),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium16),
                modifier = Modifier.padding(
                    horizontal = MaterialTheme.spacing.medium16,
                    vertical = MaterialTheme.spacing.large24
                ),
            ) {
                Text(
                    text = stringResource(title),
                    modifier = Modifier.fillMaxWidth(),
                )
                TimeInput(state = timePickerState)
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextButton(onClick = onDismiss) {
                        Text(
                            text = stringResource(R.string.cancel)
                        )
                    }
                    Spacer(modifier = Modifier.width(MaterialTheme.spacing.small8))
                    TextButton(onClick = {
                        onConfirm(
                            LocalTime.of(
                                timePickerState.hour,
                                timePickerState.minute
                            )
                        )
                    }) {
                        Text(
                            text = stringResource(R.string.ok)
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@DarkAndLightModePreview
@Composable
fun TimePickerDialogPreview() {
    Hospital_AutomationTheme {
        Surface {
            TimePickerDialog(
                onConfirm = {},
                onDismiss = {},

            )
        }
    }
}