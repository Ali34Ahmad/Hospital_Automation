package com.example.ui_components.components.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.DialogProperties
import com.example.constants.days_of_week.getDaysOfWeekOptionsList
import com.example.ext.toAppropriateFormat
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui_components.R
import com.maxkeppeker.sheets.core.models.base.IconSource
import com.maxkeppeker.sheets.core.models.base.UseCaseState
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.option.OptionDialog
import com.maxkeppeler.sheets.option.models.DisplayMode
import com.maxkeppeler.sheets.option.models.Option
import com.maxkeppeler.sheets.option.models.OptionConfig
import com.maxkeppeler.sheets.option.models.OptionDetails
import com.maxkeppeler.sheets.option.models.OptionSelection
import java.time.DayOfWeek
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DayOfWeekPickerDialog(
    onConfirm: (index: Int) -> Unit,
    state: UseCaseState = rememberUseCaseState(),
    options: List<Option>,
) {
    OptionDialog(
        state = state,
        selection = OptionSelection.Single(options) { index, option ->
            onConfirm(index)
        },
        config = OptionConfig(mode = DisplayMode.LIST,),
    )
}

@DarkAndLightModePreview
@Composable
fun DayOfWeekPickerDialogPreview() {
    val datePickerState: UseCaseState = rememberUseCaseState()
    var selectedDay by remember { mutableStateOf<DayOfWeek?>(null) }
    val options = getDaysOfWeekOptionsList()
    Hospital_AutomationTheme {
        Surface {
            DayOfWeekPickerDialog(
                state = datePickerState,
                onConfirm = {selectedDayIndex->
                    selectedDay = DayOfWeek.entries.find { options[selectedDayIndex].titleText==it.name }
                },
                options = options
            )
            Column {
                Button(onClick = { datePickerState.show() }) {
                    Text("Open")
                }
                Text(selectedDay?.toString()?: "NULL")
            }
        }
    }
}