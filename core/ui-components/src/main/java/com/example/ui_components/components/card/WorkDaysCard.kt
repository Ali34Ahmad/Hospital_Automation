package com.example.ui_components.components.card

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.model.WorkDayItem
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.additionalColorScheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.components.list_items.SimpleCheckBoxItem
import java.time.DayOfWeek

@Composable
fun WorkDaysCard(
    workDays: List<WorkDayItem>,
    onItemCheckedChanged: (Int,Boolean)-> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(8.dp),
    paddingValues: PaddingValues = PaddingValues(
        vertical = MaterialTheme.spacing.small8
    ),
    border: BorderStroke = BorderStroke(
        width =  MaterialTheme.sizing.extraSmall1,
        color = MaterialTheme.additionalColorScheme.onBackgroundVariant
    ),
    containerColor: Color = MaterialTheme.colorScheme.background
) {
    Card(
        modifier = modifier
            .clip(shape),
        shape = shape,
        border = border,
        colors = CardDefaults.cardColors(
            containerColor = containerColor
        )
    ) {
        Column(
            modifier = Modifier.padding(paddingValues),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            workDays.forEachIndexed { index,workDay->
                SimpleCheckBoxItem(
                    title = workDay.dayName,
                    isChecked = workDay.isChecked,
                    onCheckedChange = {
                        onItemCheckedChanged(index,it)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = workDay.enabled,
                )
            }
        }
    }
}

@DarkAndLightModePreview
@Composable
fun WorkDaysCardPreview(){
    Hospital_AutomationTheme {
        val days = listOf(
            WorkDayItem(
                isChecked = false,
                enabled = false,
                dayOfWeek = DayOfWeek.SATURDAY,
            ),
            WorkDayItem(
                isChecked = true,
                enabled = true,
                dayOfWeek = DayOfWeek.SUNDAY,
            ),
            WorkDayItem(
                isChecked = true,
                enabled = true,
                dayOfWeek = DayOfWeek.MONDAY,
            ),
            WorkDayItem(
                isChecked = false,
                enabled = true,
                dayOfWeek = DayOfWeek.TUESDAY,
            ),
            WorkDayItem(
                isChecked = true,
                enabled = true,
                dayOfWeek = DayOfWeek.WEDNESDAY,
            ),
            WorkDayItem(
                isChecked = true,
                enabled = true,
                dayOfWeek = DayOfWeek.THURSDAY,
            ),
            WorkDayItem(
                isChecked = false,
                enabled = false,
                dayOfWeek = DayOfWeek.FRIDAY,
            )
        )
        var list by remember { mutableStateOf(days) }
        Box(
            modifier = Modifier.padding(MaterialTheme.spacing.medium16)
        ) {
            WorkDaysCard(
                workDays = list,
                onItemCheckedChanged = {index,isChecked->
                    val newList = list.toMutableList()
                    val currentItem = newList[index]
                    newList[index] = currentItem.copy(isChecked = !currentItem.isChecked)
                    list = newList
                                       },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}