package com.example.ui_components.components.tables.vaccination_table.generic

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import com.example.constants.icons.AppIcons
import com.example.model.vaccine.VaccineMainInfo
import com.example.ui.fake.createFakeVaccinationData
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.components.icon.IconWithBackground
import com.example.ui_components.components.tables.vaccination_table.VisitNumberColumn
import com.example.ui_components.components.tables.vaccination_table.VisitNumberCompactColumn

@Composable
fun GenericVaccinationTableItem(
    visitNumberToVaccines: Pair<Int, List<VaccineMainInfo>>,
    onClick: (Int) -> Unit,
    onItemDelete: (visitNumber: Int, vaccineIndex: Int) -> Unit,
    onAddVaccineToVisit: (visitNumber: Int) -> Unit,
    isEditable: Boolean,
    modifier: Modifier = Modifier,
) {
    val surfaceColor = MaterialTheme.colorScheme.outlineVariant
    val borderWidth = MaterialTheme.sizing.extraSmall1
    val innerRowModifier = Modifier
        .drawBehind {
            val strokeWidth = borderWidth.toPx()
            val yHorizontalLine = size.height - strokeWidth / 2
            drawLine(
                color = surfaceColor,
                start = Offset(0f, yHorizontalLine),
                end = Offset(size.width, yHorizontalLine),
                strokeWidth = strokeWidth
            )
            val yVerticalLine = size.height
            drawLine(
                color = surfaceColor,
                start = Offset(0f, 0f),
                end = Offset(0f, yVerticalLine),
                strokeWidth = strokeWidth
            )
        }

    val rowModifier = Modifier
        .drawBehind {
            val strokeWidth = borderWidth.toPx()
            val y = size.height - strokeWidth / 2
            drawLine(
                color = surfaceColor,
                start = Offset(0f, y),
                end = Offset(size.width, y),
                strokeWidth = strokeWidth
            )
        }

    val paddingModifierForText=if (!isEditable) Modifier.padding(
        vertical = MaterialTheme.spacing.medium16
    )else Modifier
    Row(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.background)
            .then(rowModifier),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (visitNumberToVaccines.second.size > 1 || !isEditable) {
            VisitNumberColumn(
                visitNumber = visitNumberToVaccines.first,
                onAddVaccineToVisit = onAddVaccineToVisit,
                modifier = Modifier.weight(0.15f),
                isEditable = isEditable,
            )
        } else {
            VisitNumberCompactColumn(
                visitNumber = visitNumberToVaccines.first,
                onAddVaccineToVisit = onAddVaccineToVisit,
                modifier = Modifier.weight(0.15f),
            )
        }
        Column(
            modifier = Modifier.weight(1f),
        ) {
            visitNumberToVaccines.second.forEachIndexed { index, vaccine ->
                Row(
                    modifier = innerRowModifier
                        .clickable { onClick(vaccine.id) }
                        .padding(
                            start = MaterialTheme.spacing.medium16,
                            end = MaterialTheme.spacing.extraSmall4,
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = vaccine.name,
                        modifier = Modifier.weight(1f)
                            .then(paddingModifierForText),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    if (isEditable){
                        IconButton(
                            onClick = { onItemDelete(visitNumberToVaccines.first, index) }
                        ) {
                            IconWithBackground(
                                iconRes = AppIcons.Outlined.delete,
                                iconColor = MaterialTheme.colorScheme.error,
                                backgroundColor = MaterialTheme.colorScheme.errorContainer.copy(
                                    alpha = 0.4f
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}


@DarkAndLightModePreview
@Composable
fun VaccinationTableItemPreview() {
    Hospital_AutomationTheme {
        Surface {
            GenericVaccinationTableItem(
                visitNumberToVaccines = Pair(
                    createFakeVaccinationData()[0].visitNumber,
                    createFakeVaccinationData()[0].vaccines
                ),
                onClick = {},
                onItemDelete = { visitNumber, vaccineIndex -> },
                onAddVaccineToVisit = {},
                isEditable = true
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun VaccinationTableItemOneVaccinePreview() {
    Hospital_AutomationTheme {
        Surface {
            GenericVaccinationTableItem(
                visitNumberToVaccines = Pair(
                    createFakeVaccinationData()[1].visitNumber,
                    createFakeVaccinationData()[0].vaccines
                ),
                onClick = {},
                onItemDelete = { visitNumber, vaccineIndex -> },
                onAddVaccineToVisit = {},true
            )
        }
    }
}


@DarkAndLightModePreview
@Composable
fun VaccinationTableItemNonEditablePreview() {
    Hospital_AutomationTheme {
        Surface {
            GenericVaccinationTableItem(
                visitNumberToVaccines = Pair(
                    createFakeVaccinationData()[0].visitNumber,
                    createFakeVaccinationData()[0].vaccines
                ),
                onClick = {},
                onItemDelete = { visitNumber, vaccineIndex -> },
                onAddVaccineToVisit = {},
                isEditable = false
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun VaccinationTableItemOneVaccineNonEditablePreview() {
    Hospital_AutomationTheme {
        Surface {
            GenericVaccinationTableItem(
                visitNumberToVaccines = Pair(
                    createFakeVaccinationData()[1].visitNumber,
                    createFakeVaccinationData()[0].vaccines
                ),
                onClick = {},
                onItemDelete = { visitNumber, vaccineIndex -> },
                onAddVaccineToVisit = {},false
            )
        }
    }
}

