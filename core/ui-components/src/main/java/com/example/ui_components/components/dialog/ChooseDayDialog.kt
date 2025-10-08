package com.example.ui_components.components.dialog

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.spacing
import java.time.DayOfWeek
import com.example.ui_components.R
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChooseDayDialog(
    onDismissDialog : () -> Unit,
    disabledDays: List<DayOfWeek>,
    selectedDayOfWeek: DayOfWeek?,
    onDaySelected: (DayOfWeek)-> Unit,
    onConfirm: ()-> Unit,
    modifier: Modifier = Modifier,
    @StringRes confirmButtonText: Int = R.string.confirm,
    @StringRes dismissButtonText: Int = R.string.cancel,
    onCancel: ()-> Unit = onDismissDialog,
    @StringRes title: Int = R.string.choose_day,
    shape: Shape = RoundedCornerShape(28.dp),
    titleStyle: TextStyle = MaterialTheme.typography.headlineSmall,
    containerColor: Color = MaterialTheme.colorScheme.surfaceContainerHigh,
) {
    //To Start from Saturday
    val days = DayOfWeek.entries
    val satIndex = days.indexOf(DayOfWeek.SATURDAY)
    val daysOfWeek  = days.drop(satIndex) + days.take(satIndex)
    BasicAlertDialog(
        onDismissRequest = onDismissDialog,
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier
                .background(
                    color = containerColor,
                    shape = shape
                )
                .clip(shape)
                .padding(MaterialTheme.spacing.large24)
        ) {
            Text(
                text = stringResource(title),
                style = titleStyle
            )
            Spacer(Modifier.height(MaterialTheme.spacing.large32))
            FlowRow(
                horizontalArrangement = Arrangement.Center,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
                    .padding(
                        horizontal = MaterialTheme.spacing.medium16
                    )
            ) {
                daysOfWeek.forEach { day->
                    DayChip(
                        labelText = day.getDisplayName(
                            java.time.format.TextStyle.SHORT_STANDALONE,
                            Locale.US
                        ),
                        enabled = !disabledDays.contains(day),
                        onClick = {
                            onDaySelected(day)
                        },
                        selected = selectedDayOfWeek == day
                    )
                    Spacer(Modifier.width(MaterialTheme.spacing.medium16))
                }
            }
            Spacer(Modifier.width(MaterialTheme.spacing.large24))
            //actions
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(
                    onCancel
                ) {
                    Text(
                        text = stringResource(dismissButtonText),
                    )
                }
                Spacer(Modifier.width(MaterialTheme.spacing.small8))
                TextButton(
                    onConfirm
                ) {
                    Text(
                        text = stringResource(confirmButtonText),
                    )
                }
            }
        }
    }
}


@Composable
fun DayChip(
    labelText: String,
    enabled: Boolean,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val selectedChipColors = AssistChipDefaults.assistChipColors(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        labelColor = MaterialTheme.colorScheme.onPrimaryContainer
    )

    val chipColors = if(selected) selectedChipColors
    else AssistChipDefaults.assistChipColors()

    AssistChip(
        onClick = onClick,
        colors = chipColors,
        label = {
            Text(
                labelText,
                style = MaterialTheme.typography.labelLarge,
            )
        },
        modifier = modifier,
        enabled = enabled,
    )
}

@DarkAndLightModePreview
@Composable
fun DayChipPreview() {
    Hospital_AutomationTheme {
        Surface {
            DayChip(
                labelText = "Sunday",
                enabled = true,
                onClick = { },
                modifier = Modifier.padding(
                    MaterialTheme.spacing.medium16
                ),
                selected = true,
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun ChooseDayDialogPreview() {
    Hospital_AutomationTheme {
        Surface {
            ChooseDayDialog(
                onDismissDialog = {},
                disabledDays = listOf(DayOfWeek.MONDAY, DayOfWeek.FRIDAY),
                onDaySelected = {
                },
                onConfirm = {},
                onCancel = {},
                selectedDayOfWeek = DayOfWeek.TUESDAY
            )
        }
    }
}