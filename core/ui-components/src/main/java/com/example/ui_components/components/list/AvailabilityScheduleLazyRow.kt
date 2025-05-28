package com.example.ui_components.components.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.ext.toAppropriateFormat
import com.example.model.helper.ext.toCapitalizedString
import com.example.model.WorkDay
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.spacing
import com.example.ui_components.components.items.SubDetailsItem
import java.time.DayOfWeek
import java.time.LocalTime

@Composable
fun AvailabilityScheduleLazyRow(
    workDays: List<WorkDay>,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = MaterialTheme.spacing.medium16),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small8),
    ) {
        items(workDays) { workDay ->
            SubDetailsItem(
                title = workDay.day.name.toCapitalizedString(),
                description = (workDay.workStartTime..workDay.workEndTime).toAppropriateFormat(),
                onClick = {},
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun AvailabilityScheduleLazyRowPreview() {
    Hospital_AutomationTheme {
        Surface {
            AvailabilityScheduleLazyRow(
                workDays = listOf(
                    WorkDay(
                        day = DayOfWeek.SUNDAY,
                        workStartTime = LocalTime.now(),
                        workEndTime = LocalTime.now().plusHours(10L),
                    ),
                    WorkDay(
                        day = DayOfWeek.SUNDAY,
                        workStartTime = LocalTime.now(),
                        workEndTime = LocalTime.now().plusHours(10L),
                    ),
                    WorkDay(
                        day = DayOfWeek.SUNDAY,
                        workStartTime = LocalTime.now(),
                        workEndTime = LocalTime.now().plusHours(10L),
                    ),
                    WorkDay(
                        day = DayOfWeek.SUNDAY,
                        workStartTime = LocalTime.now(),
                        workEndTime = LocalTime.now().plusHours(10L),
                    ),
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = MaterialTheme.spacing.medium16)
            )
        }
    }
}