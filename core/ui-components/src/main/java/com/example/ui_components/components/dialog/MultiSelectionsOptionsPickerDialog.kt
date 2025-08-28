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
import com.example.constants.days_of_week.getDaysOfWeekOptionsList
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.maxkeppeker.sheets.core.models.base.UseCaseState
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.option.OptionDialog
import com.maxkeppeler.sheets.option.models.DisplayMode
import com.maxkeppeler.sheets.option.models.Option
import com.maxkeppeler.sheets.option.models.OptionConfig
import com.maxkeppeler.sheets.option.models.OptionSelection
import java.time.DayOfWeek

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MultiSelectionsOptionsPickerDialog(
    onConfirm: (indexes: List<Int>) -> Unit,
    state: UseCaseState = rememberUseCaseState(),
    options: List<Option>,
) {
    OptionDialog(
        state = state,
        selection = OptionSelection.Multiple(options) { indexes, option ->
            onConfirm(indexes)
        },
        config = OptionConfig(mode = DisplayMode.LIST),
    )
}

@DarkAndLightModePreview
@Composable
fun MultiDaysOfWeekPickerDialogPreview() {
    val datePickerState: UseCaseState = rememberUseCaseState()
    var selectedDays = remember { mutableListOf<DayOfWeek>() }
    val options = getDaysOfWeekOptionsList()

    Hospital_AutomationTheme {
        Surface {
            MultiSelectionsOptionsPickerDialog(
                state = datePickerState,
                onConfirm = { selectedDaysIndexes ->
                    selectedDaysIndexes.forEach {index->
                        selectedDays.add(
                            DayOfWeek.entries[index]
                        )
                    }
                },
                options = options
            )
            Column {
                Button(onClick = { datePickerState.show() }) {
                    Text("Open")
                }
                Text(selectedDays.toString())
            }
        }
    }
}
