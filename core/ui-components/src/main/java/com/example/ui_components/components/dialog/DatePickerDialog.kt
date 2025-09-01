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
import com.example.ext.toAppropriateAddressFormat
import com.example.ext.toAppropriateDateFormat
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.maxkeppeker.sheets.core.models.base.UseCaseState
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import java.time.LocalDate


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDialog(
    onConfirm: (LocalDate) -> Unit,
    datePickerState: UseCaseState = rememberUseCaseState()
) {
    CalendarDialog(
        state = datePickerState,
        selection = CalendarSelection.Date { date ->
            onConfirm(date)
        },
        config = CalendarConfig(
            yearSelection = true,
            monthSelection = true,
            style = CalendarStyle.MONTH,
            ),
        )
}

@DarkAndLightModePreview
@Composable
fun DatePickerDialogPreview() {
    val datePickerState: UseCaseState = rememberUseCaseState(visible = true)
    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }
    Hospital_AutomationTheme {
        Surface {
            Column{
                DatePickerDialog(
                    onConfirm = {
                        selectedDate=it
                    },
                    datePickerState=datePickerState,
                )
                Button(onClick = { datePickerState.show() }) {
                    Text("Open")
                }
                Text(selectedDate?.toAppropriateDateFormat()?:"NULL")
            }
        }
    }
}
