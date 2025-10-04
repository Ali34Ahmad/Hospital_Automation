package com.example.ui_components.components.tables.vaccination_table.child_table

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.constants.icons.AppIcons
import com.example.ext.toAppropriateDateFormat
import com.example.ext.toAppropriateNameFormat
import com.example.ext.toOrdinalString
import com.example.model.enums.VaccineStatus
import com.example.model.vaccine.ChildVaccinationTableVisit
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.additionalColorScheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.icon.IconWithBackground

@Composable
fun ChildVaccinationTableItem(
    visitNumberToVaccines: ChildVaccinationTableVisit,
    onItemClick: (vaccineId: Int) -> Unit,
    onProviderNameClick: (doctorId: Int) -> Unit,
    onNavigateToAppointmentClick: (appointmentId: Int) -> Unit,
    showDetails: Boolean,
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

    val paddingModifierForText = Modifier.padding(
        top = MaterialTheme.spacing.medium16,
        bottom = MaterialTheme.spacing.medium16,
        start = MaterialTheme.spacing.medium16,
        end = MaterialTheme.spacing.medium16
    )

    val leftStrokeModifier = Modifier
        .drawBehind {
            val strokeWidth = borderWidth.toPx()
            val yVerticalLine = size.height
            drawLine(
                color = surfaceColor,
                start = Offset(0f, 0f),
                end = Offset(0f, yVerticalLine),
                strokeWidth = strokeWidth
            )
        }

    val textLeftBorderAndPaddingModifier = leftStrokeModifier
        .then(paddingModifierForText)

    Row(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.background)
            .then(rowModifier),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (showDetails) {
            Box(
                modifier = Modifier
                    .weight(ChildVaccinationTableColumnWeight.VISIT_NUMBER),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = visitNumberToVaccines.visitNumber.toOrdinalString(),
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
        Column(
            modifier = Modifier.weight(1.55f),
        ) {
            visitNumberToVaccines.vaccinesWithVaccinationTableDetails.forEachIndexed { index, vaccineToVaccineDetails ->
                Row(
                    modifier = innerRowModifier
                        .clickable { onItemClick(vaccineToVaccineDetails.vaccineMainInfo.id) },
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Row(
                        modifier = Modifier
                            .weight(ChildVaccinationTableColumnWeight.VACCINE_NAME)
                            .then(textLeftBorderAndPaddingModifier),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        if (vaccineToVaccineDetails.vaccinationTableItemDetails.isAvailableNow) {
                            Box(
                                modifier = Modifier
                                    .padding(end = MaterialTheme.spacing.extraSmall4)
                                    .size(MaterialTheme.sizing.extraSmall4)
                                    .clip(CircleShape)
                                    .background(MaterialTheme.additionalColorScheme.green)
                            )
                        }
                        Text(
                            text = vaccineToVaccineDetails.vaccineMainInfo.name,
                            style = MaterialTheme.typography.bodyMedium,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    val vaccinationDate =
                        vaccineToVaccineDetails.vaccinationTableItemDetails.vaccinationDate?.toAppropriateDateFormat()
                    val vaccinationDateTextAlign = if (vaccinationDate == null)
                        TextAlign.Center
                    else TextAlign.Start

                    Text(
                        text = vaccinationDate
                            ?: stringResource(R.string.triple_dashes),
                        modifier = Modifier
                            .weight(ChildVaccinationTableColumnWeight.DATE)
                            .then(textLeftBorderAndPaddingModifier),
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = vaccinationDateTextAlign,
                    )

                    val (icon, iconColor, iconBackgroundColor) = when (vaccineToVaccineDetails.vaccinationTableItemDetails.vaccineStatus) {
                        VaccineStatus.PASSED -> Triple(
                            AppIcons.Outlined.checkWithBorder,
                            MaterialTheme.additionalColorScheme.green,
                            MaterialTheme.additionalColorScheme.green.copy(alpha = 0.12f)
                        )

                        VaccineStatus.MISSED -> Triple(
                            AppIcons.Outlined.error,
                            MaterialTheme.colorScheme.onErrorContainer,
                            MaterialTheme.colorScheme.errorContainer
                        )

                        VaccineStatus.NOT_SPECIFIED -> Triple(
                            AppIcons.Outlined.notSpecified,
                            MaterialTheme.colorScheme.outlineVariant,
                            Color.Transparent
                        )

                        VaccineStatus.UPCOMING -> Triple(
                            AppIcons.Outlined.upcomingEvent,
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f),
                        )
                    }

                    val appointmentId =
                        vaccineToVaccineDetails.vaccinationTableItemDetails.appointmentId
                    val stateBoxBaseModifier = Modifier
                        .weight(ChildVaccinationTableColumnWeight.STATE)
                        .then(leftStrokeModifier)
                    val stateBoxModifier = if (appointmentId != null) {
                        stateBoxBaseModifier
                            .clickable {
                                onNavigateToAppointmentClick(appointmentId)
                            }
                    } else {
                        stateBoxBaseModifier
                    }

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = stateBoxModifier
                    ) {
                        IconWithBackground(
                            iconRes = icon,
                            iconColor = iconColor,
                            backgroundColor = iconBackgroundColor,
                            modifier = Modifier.padding(
                                vertical = MaterialTheme.spacing.small14,
                            )
                        )
                    }

                    if (showDetails) {
                        val vaccineProviderName =
                            vaccineToVaccineDetails.vaccinationTableItemDetails.administratedBy?.fullName?.toAppropriateNameFormat()
                        val vaccineProviderNameTextAlign = if (vaccineProviderName == null)
                            TextAlign.Center
                        else TextAlign.Start

                        val genericProviderNameTextModifier = Modifier
                            .weight(ChildVaccinationTableColumnWeight.VACCINE_NAME)
                            .then(textLeftBorderAndPaddingModifier)

                        val vaccineProvider =
                            vaccineToVaccineDetails.vaccinationTableItemDetails.administratedBy
                        val (providerNameTextStyle, providerNameTextModifier) =
                            if (vaccineProvider?.id != null) {
                                MaterialTheme.typography.bodyMedium.copy(
                                    textDecoration = TextDecoration.Underline
                                ) to
                                        genericProviderNameTextModifier.clickable {
                                            onProviderNameClick(vaccineProvider.id!!)
                                        }
                            } else {
                                MaterialTheme.typography.bodyMedium to genericProviderNameTextModifier
                            }

                        Text(
                            text = vaccineProviderName
                                ?: stringResource(R.string.triple_dashes),
                            modifier = providerNameTextModifier,
                            style = providerNameTextStyle,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = vaccineProviderNameTextAlign,
                        )
                        val nextVisitDate =
                            vaccineToVaccineDetails.vaccinationTableItemDetails.nextVisitDate?.toAppropriateDateFormat()
                        val nextVisitDateTextAlign = if (nextVisitDate == null)
                            TextAlign.Center
                        else TextAlign.Start

                        Text(
                            text = nextVisitDate
                                ?: stringResource(R.string.triple_dashes),
                            modifier = Modifier
                                .weight(ChildVaccinationTableColumnWeight.DATE)
                                .then(textLeftBorderAndPaddingModifier),
                            style = MaterialTheme.typography.bodyMedium,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = nextVisitDateTextAlign,
                        )
                    }
                }
            }
        }
    }
}


@Preview(widthDp = 300)
@Composable
fun VaccinationTableItemTakenPreview() {
    Hospital_AutomationTheme {
        Surface {
            ChildVaccinationTableItem(
                visitNumberToVaccines = createChildVaccinationTableDataSample().visitNumbersToVaccines[0],
                onItemClick = {},
                showDetails = false,
                onProviderNameClick = {},
                onNavigateToAppointmentClick = {},
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun VaccinationTableItemNotSpecifiedPreview() {
    Hospital_AutomationTheme {
        Surface {
            ChildVaccinationTableItem(
                visitNumberToVaccines = createChildVaccinationTableDataSample().visitNumbersToVaccines[1],
                onItemClick = {},
                showDetails = false,
                onProviderNameClick = {},
                onNavigateToAppointmentClick = {}
            )
        }
    }
}


@Preview(widthDp = 800)
@Composable
fun VaccinationTableItemMissedPreview() {
    Hospital_AutomationTheme {
        Surface {
            ChildVaccinationTableItem(
                visitNumberToVaccines = createChildVaccinationTableDataSample().visitNumbersToVaccines[2],
                onItemClick = {},
                showDetails = true,
                onProviderNameClick = {},
                onNavigateToAppointmentClick = {}
            )
        }
    }
}
