package com.example.ui_components.components.tables.vaccine_interactions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.constants.icons.AppIcons
import com.example.model.vaccine.VaccineInteraction
import com.example.ui.fake.createVaccineInteractionsSampleData
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.items.ProfileActionsItem

@Composable
fun VaccineInteractionTable(
    modifier: Modifier = Modifier,
    interactions: List<VaccineInteraction>,
    onItemClick: (index: Int) -> Unit,
    isEditable: Boolean,
    emptyTable: @Composable () -> Unit = {},
) {
    Column(
        modifier = modifier,
    ) {
        if (isEditable&&interactions.isNotEmpty()) {
            ProfileActionsItem(
                iconRes = AppIcons.Outlined.edit,
                title = stringResource(R.string.Tap_a_row_to_edit),
            )
        }
        if (interactions.isNotEmpty()) {
            VaccineInteractionTableHeader()
            interactions.forEachIndexed { index, interaction ->
                VaccineInteractionTableItem(
                    interaction = interaction,
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onItemClick(index) },
                )
            }
        } else {
            emptyTable()
        }
    }
}

@DarkAndLightModePreview
@Composable
fun VaccineInteractionTablePreview() {
    Hospital_AutomationTheme {
        Surface {
            VaccineInteractionTable(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.medium16)
                    .background(MaterialTheme.colorScheme.background),
                interactions = createVaccineInteractionsSampleData(),
                onItemClick = { },
                isEditable = true
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun VaccineInteractionTableNonEditableEmptyPreview() {
    Hospital_AutomationTheme {
        Surface {
            VaccineInteractionTable(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.medium16)
                    .background(MaterialTheme.colorScheme.background),
                interactions = emptyList(),
                onItemClick = { },
                isEditable = false,
            )
        }
    }
}
