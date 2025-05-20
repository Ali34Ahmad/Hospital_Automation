package com.example.ui_components.components.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.ext.toTimeFormat
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.maxkeppeker.sheets.core.models.base.UseCaseState
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.duration.DurationDialog
import com.maxkeppeler.sheets.duration.models.DurationConfig
import com.maxkeppeler.sheets.duration.models.DurationFormat
import com.maxkeppeler.sheets.duration.models.DurationSelection

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DurationPickerDialog(
    onConfirm: (timeInSeconds: Long) -> Unit,
    durationDialogState: UseCaseState = rememberUseCaseState(),
) {
    val selectedTimeInSeconds = remember { mutableLongStateOf(0) }
    DurationDialog(
        state = durationDialogState,
        selection = DurationSelection { newTimeInSeconds ->
            selectedTimeInSeconds.longValue = newTimeInSeconds
            onConfirm(selectedTimeInSeconds.longValue)
        },
        config = DurationConfig(
            timeFormat = DurationFormat.HH_MM,
            currentTime = selectedTimeInSeconds.longValue,
        )
    )
}

@DarkAndLightModePreview
@Composable
fun DurationPickerDialogPreview() {
    val durationPickerState: UseCaseState = rememberUseCaseState()
    var selectedDuration by remember { mutableLongStateOf(0) }
    Hospital_AutomationTheme {
        Surface {
            DurationPickerDialog(
                durationDialogState = durationPickerState,
                onConfirm = {
                    selectedDuration = it
                },
            )
            Column {
                Button(onClick = { durationPickerState.show() }) {
                    Text("Open")
                }
                Text(selectedDuration.toTimeFormat())
            }
        }
    }
}