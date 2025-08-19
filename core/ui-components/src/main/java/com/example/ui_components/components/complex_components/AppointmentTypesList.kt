package com.example.ui_components.components.complex_components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.model.doctor.appointment.AppointmentTypeData
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.card.EditableAppointmentTypeCard

@Composable
fun AppointmentTypesList(
    items: List<AppointmentTypeData>,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    onTitleChange: (index: Int, newTitle: String) -> Unit,
    onSubtitleClick: (index: Int) -> Unit,
    onDescriptionChanged: (index: Int, newDescription: String) -> Unit,
    onDelete: (index: Int) -> Unit,
    @StringRes title: Int = R.string.appointments,
    @DrawableRes actionIcon: Int = R.drawable.ic_add,
) {

    LazyColumn(
        modifier = modifier
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = MaterialTheme.spacing.medium16),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(title),
                    style = MaterialTheme.typography.titleLarge
                )
                FilledIconButton(
                    modifier = Modifier
                        .size(MaterialTheme.sizing.small24),
                    onClick = onClick,
                    colors = IconButtonDefaults.filledIconButtonColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                ) {
                    Icon(
                        painter = painterResource(actionIcon),
                        contentDescription = null,
                    )
                }
            }
        }

        itemsIndexed(items) { index, data ->
            EditableAppointmentTypeCard(
                index = index,
                title = data.name,
                onTitleChanged = { newTitle ->
                    onTitleChange(index, newTitle)
                },
                subtitle = data.description,
                onDelete = {
                    onDelete(index)
                },
                description = data.description,
                onDescriptionChanged = { newDescription ->
                    onDescriptionChanged(index, newDescription)
                },
                onSubtitleClick = {
                    onSubtitleClick(index)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = MaterialTheme.spacing.small8),

                )
        }
    }

}

@Preview
@Composable
fun AppointmentsTypesListPreview() {
    Hospital_AutomationTheme {
        Surface {
            AppointmentTypesList(
                items = listOf(
                    AppointmentTypeData(
                        name = "Check up",
                        description = """
                        A Review uses a special type of energy to create pictures of the inside of your body.
                         It's like taking a photograph, but instead of light, it uses invisible rays to see 
                         what's happening inside. A Review uses a special type of energy to create pictures 
                         of the inside of your body. It's like taking a photograph, but instead of light, 
                         it uses invisible rays to see what's happening inside.
                    """.trimIndent(),
                        id = 1,
                        standardDurationInMinutes = 10,
                        doctorId = 1,
                    ),
                    AppointmentTypeData(
                        name = "Review",
                        description = """
                        A Review uses a special type of energy to create pictures of the inside of your body.
                         It's like taking a photograph, but instead of light, it uses invisible rays to see 
                         what's happening inside. A Review uses a special type of energy to create pictures 
                         of the inside of your body. It's like taking a photograph, but instead of light, 
                         it uses invisible rays to see what's happening inside.
                    """.trimIndent(),
                        id = 1,
                        standardDurationInMinutes = 15,
                        doctorId = 1,
                    )
                ),
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.medium16),
                onTitleChange = { index, newTitle -> },
                onSubtitleClick = {},
                onDescriptionChanged = { index, newDescription -> },
                onDelete = {},
            )
        }

    }
}