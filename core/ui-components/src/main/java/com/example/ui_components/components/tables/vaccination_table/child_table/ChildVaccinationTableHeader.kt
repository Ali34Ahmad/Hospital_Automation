package com.example.ui_components.components.tables.vaccination_table.child_table

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.R

object ChildVaccinationTableColumnWeight {
    const val VISIT_NUMBER = 0.1f
    const val VACCINE_NAME = 0.4f
    const val DATE = 0.3f
    const val STATE = 0.15f
    const val ADMINISTRATED_BY = 0.4f
    const val NEXT_VISIT = 0.3f
}

@Composable
fun ChildVaccinationTableHeader(
    showDetails: Boolean,
    modifier: Modifier = Modifier
) {
    val surfaceColor = MaterialTheme.colorScheme.surface
    val borderWidth = MaterialTheme.sizing.extraSmall1

    val startStrokeModifier = Modifier.drawBehind {
        val strokeWidth = borderWidth.toPx()
        val yVerticalLine = size.height
        drawLine(
            color = surfaceColor,
            start = Offset(0f, 0f),
            end = Offset(0f, yVerticalLine),
            strokeWidth = strokeWidth
        )
    }

    Row(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.primaryContainer),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (showDetails) {
            Box(
                modifier = Modifier
                    .weight(ChildVaccinationTableColumnWeight.VISIT_NUMBER),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = stringResource(R.string.visit),
                    style = MaterialTheme.typography.labelMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                            . graphicsLayer {
                        rotationZ = -90f
                    }
                )
            }
        }
        Box(
            modifier = Modifier
                .weight(ChildVaccinationTableColumnWeight.VACCINE_NAME),
        ) {
            Text(
                text = stringResource(R.string.vaccine),
                style = MaterialTheme.typography.labelMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                        . then (startStrokeModifier)
                    .padding(
                        vertical = MaterialTheme.spacing.small12,
                        horizontal = MaterialTheme.spacing.medium16
                    )
            )
        }

        Text(
            text = stringResource(R.string.date),
            modifier = Modifier
                .weight(ChildVaccinationTableColumnWeight.DATE)
                .then(startStrokeModifier)
                .padding(
                    vertical = MaterialTheme.spacing.small12,
                    horizontal = MaterialTheme.spacing.medium16
                ),
            style = MaterialTheme.typography.labelMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = stringResource(R.string.state),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .weight(ChildVaccinationTableColumnWeight.STATE)
                .then(startStrokeModifier)
                .padding(
                    vertical = MaterialTheme.spacing.small12,
                    horizontal = MaterialTheme.spacing.medium16
                ),
            style = MaterialTheme.typography.labelMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        if (showDetails) {
            Text(
                text = stringResource(R.string.administrated_by),
                modifier = Modifier
                    .weight(ChildVaccinationTableColumnWeight.ADMINISTRATED_BY)
                    .then(startStrokeModifier)
                    .padding(
                        vertical = MaterialTheme.spacing.small12,
                        horizontal = MaterialTheme.spacing.medium16
                    ),
                style = MaterialTheme.typography.labelMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        if (showDetails) {
            Text(
                text = stringResource(R.string.next_visit),
                modifier = Modifier
                    .weight(ChildVaccinationTableColumnWeight.NEXT_VISIT)
                    .then(startStrokeModifier)
                    .padding(
                        vertical = MaterialTheme.spacing.small12,
                        horizontal = MaterialTheme.spacing.medium16
                    ),
                style = MaterialTheme.typography.labelMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

    }
}

@DarkAndLightModePreview
@Composable
fun VaccinationTableHeaderPreview() {
    Hospital_AutomationTheme {
        Surface {
            ChildVaccinationTableHeader(
                showDetails = false,
            )
        }
    }
}


@Preview(widthDp = 800)
@Composable
fun VaccinationTableHeaderWithDetailsPreview() {
    Hospital_AutomationTheme {
        Surface {
            ChildVaccinationTableHeader(
                showDetails = true,
            )
        }
    }
}

