package com.example.ui_components.components.tables.vaccination_table.generic

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import com.example.constants.icons.AppIcons
import com.example.ui.fake.createFakeVaccinationData
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.items.ProfileActionsItem
import com.example.model.vaccine.GenericVaccinationTableData

@Composable
fun GenericVaccinationTable(
    genericVaccinationTableData: GenericVaccinationTableData,
    onAddNewVisit: () -> Unit,
    onAddNewVaccineToVisit: (visitNumber: Int) -> Unit,
    onDeleteVaccine: (visitNumber: Int, vaccineIndex: Int) -> Unit,
    onVaccineItemClick: (Int) -> Unit,
    isEditable: Boolean,
    loadingVisitNumber: Int?,
    vaccinesIdsToDelete: List<Int>?,
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
        GenericVaccinationTableHeader()
        genericVaccinationTableData.visits.forEach { visit ->
            GenericVaccinationTableItem(
                visitNumberToVaccines = Pair(visit.visitNumber, visit.vaccines),
                onClick = onVaccineItemClick,
                onItemDelete = onDeleteVaccine,
                onAddVaccineToVisit = onAddNewVaccineToVisit,
                isEditable = isEditable,
                isLoadingVisit = visit.visitNumber == loadingVisitNumber,
                isAddingEnabled = loadingVisitNumber == null && vaccinesIdsToDelete.isNullOrEmpty(),
                vaccinesIdsToDelete = vaccinesIdsToDelete ?: emptyList(),
            )
        }
        if (isEditable) {
            ProfileActionsItem(
                onClick = onAddNewVisit,
                iconRes = R.drawable.ic_add,
                title = stringResource(R.string.add_visit),
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun VaccinationTablePreview() {
    Hospital_AutomationTheme {
        Surface {
            GenericVaccinationTable(
                genericVaccinationTableData = GenericVaccinationTableData(createFakeVaccinationData()),
                onDeleteVaccine = { visitNumber, vaccineIndex -> },
                onVaccineItemClick = {},
                onAddNewVisit = {},
                onAddNewVaccineToVisit = {},
                modifier = Modifier.padding(MaterialTheme.spacing.medium16),
                isEditable = true,
                loadingVisitNumber = 1,
                vaccinesIdsToDelete = listOf(1, 3),
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun VaccinationTableNonEditablePreview() {
    Hospital_AutomationTheme {
        Surface {
            GenericVaccinationTable(
                genericVaccinationTableData = GenericVaccinationTableData(createFakeVaccinationData().filter { it.vaccines.isNotEmpty() }),
                onDeleteVaccine = { visitNumber, vaccineIndex -> },
                onVaccineItemClick = {},
                onAddNewVisit = {},
                onAddNewVaccineToVisit = {},
                modifier = Modifier.padding(MaterialTheme.spacing.medium16),
                isEditable = false,
                loadingVisitNumber = null,
                vaccinesIdsToDelete = emptyList(),
            )
        }
    }
}
