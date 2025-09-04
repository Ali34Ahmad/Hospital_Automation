package com.example.ui_components.components.dialog

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ext.toAppropriateTimeFormat
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.additionalColorScheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.items.DialogOption
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTimeRangeDialog(
    startTime: LocalTime,
    endTime: LocalTime,
    onStartTimeOptionClick: (LocalTime) -> Unit,
    onEndTimeOptionClick: (LocalTime) -> Unit,
    onDismissRequest: () -> Unit,
    onConfirm: ()-> Unit,
    modifier: Modifier = Modifier,
    @StringRes title: Int = R.string.edit_time_range,
    titleStyle: TextStyle = MaterialTheme.typography.titleLarge,
    paddingValues: PaddingValues = PaddingValues(MaterialTheme.spacing.large24),
    @StringRes startTimeTitle: Int = R.string.start_time,
    @StringRes endTimeTitle: Int = R.string.end_time,
    @StringRes confirmText: Int = R.string.done,
    shape: Shape = RoundedCornerShape(28.dp),
    containerColor: Color = MaterialTheme.colorScheme.surfaceContainerHigh
) {
    BasicAlertDialog(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
    ) {
        Column(
            modifier = Modifier
                .background(
                    color = containerColor,
                    shape = shape
                )
                .clip(shape),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
        ) {
            Column(
                modifier = Modifier.padding(paddingValues)
            ) {
                Text(
                    text = stringResource(title),
                    style = titleStyle
                )
                Spacer(Modifier.height(MaterialTheme.spacing.large24))
                //options
                //  start time
                DialogOption(
                    modifier = Modifier.clickable{
                        onStartTimeOptionClick(startTime)
                    },
                    title = stringResource(startTimeTitle),
                    subTitle = startTime.toAppropriateTimeFormat(),
                )
                Spacer(Modifier.height(MaterialTheme.spacing.small8))
                //  end time
                DialogOption(
                    modifier = Modifier.clickable{
                        onEndTimeOptionClick(endTime)
                    },
                    title = stringResource(endTimeTitle),
                    subTitle = startTime.toAppropriateTimeFormat(),
                )
            }
            Spacer(Modifier.height(MaterialTheme.spacing.large24))
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = MaterialTheme.sizing.extraSmall1,
                color = MaterialTheme.additionalColorScheme.onBackgroundVariant
            )
            Row(
                modifier = Modifier.padding(paddingValues),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Spacer(Modifier.weight(1f))
                TextButton(
                    onClick = onConfirm
                ) {
                    Text(
                        text = stringResource(confirmText)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun EditTimeRangeDialogPreview() {
    Hospital_AutomationTheme {
        EditTimeRangeDialog(
            startTime = LocalTime.of(8,0,0),
            endTime = LocalTime.of(4,0,0),
            onStartTimeOptionClick = {  },
            onEndTimeOptionClick = {},
            onDismissRequest = {},
            onConfirm = {},
        )
    }
}