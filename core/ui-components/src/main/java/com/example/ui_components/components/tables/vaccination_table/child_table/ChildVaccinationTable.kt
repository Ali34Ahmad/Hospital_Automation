package com.example.ui_components.components.tables.vaccination_table.child_table

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.constants.icons.AppIcons
import com.example.model.vaccine.ChildVaccinationTableData
import com.example.model.vaccine.ChildVaccinationTableVisit
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.items.ProfileActionsItem


@Composable
fun ChildVaccinationTable(
    childVaccinationTable: ChildVaccinationTableData,
    onVaccineItemClick: (Int) -> Unit,
    showDetails: Boolean,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .clip(MaterialTheme.shapes.small),
    ) {
        ProfileActionsItem(
            iconRes = AppIcons.Outlined.syringe,
            title = stringResource(R.string.vaccination_card),
        )
        ChildVaccinationTableHeader(showDetails = showDetails)
        childVaccinationTable.visitNumbersToVaccines.forEach { visit ->
            if (visit.vaccinesWithVaccinationTableDetails.isNotEmpty()) {
                ChildVaccinationTableItem(
                    visitNumberToVaccines = ChildVaccinationTableVisit(
                        visitNumber = visit.visitNumber,
                        vaccinesWithVaccinationTableDetails = visit.vaccinesWithVaccinationTableDetails
                    ),
                    onClick = onVaccineItemClick,
                    showDetails = showDetails,
                )
            }
        }
    }
}

@DarkAndLightModePreview
@Composable
fun VaccinationTablePreview() {
    Hospital_AutomationTheme {
        Surface {
            ChildVaccinationTable(
                childVaccinationTable = createChildVaccinationTableDataSample(),
                onVaccineItemClick = {},
                modifier = Modifier.padding(MaterialTheme.spacing.medium16),
                showDetails = false
            )
        }
    }
}

@Preview(widthDp = 800)
@Composable
fun VaccinationTableNonEditablePreview() {
    Hospital_AutomationTheme {
        Surface {
            ChildVaccinationTable(
                childVaccinationTable = createChildVaccinationTableDataSample(),
                onVaccineItemClick = {},
                modifier = Modifier.padding(MaterialTheme.spacing.medium16),
                showDetails = true,
            )
        }
    }
}
