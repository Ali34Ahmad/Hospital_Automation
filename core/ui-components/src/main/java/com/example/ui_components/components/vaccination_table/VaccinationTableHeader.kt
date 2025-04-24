package com.example.ui_components.components.vaccination_table

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
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.R

@Composable
fun VaccinationTableHeader(modifier: Modifier = Modifier) {
    val surfaceColor = MaterialTheme.colorScheme.surface
    val borderWidth = MaterialTheme.sizing.extraSmall1

    Row(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.primaryContainer),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier=Modifier
                .weight(0.15f),
            contentAlignment = Alignment.Center,

        ){
            Text(
                text = stringResource(R.string.visit),
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier
                    .graphicsLayer {
                        rotationZ = -90f
                    }
            )
        }
        Text(
            text = stringResource(R.string.vaccine),
            modifier = Modifier
                .weight(1f)
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
                .padding(
                    vertical = MaterialTheme.spacing.small12,
                    horizontal = MaterialTheme.spacing.medium16
                )
                .weight(1f),
            style = MaterialTheme.typography.labelMedium,
        )
    }
}

@DarkAndLightModePreview
@Composable
fun VaccinationTableHeaderPreview() {
    Hospital_AutomationTheme {
        Surface {
            VaccinationTableHeader()
        }
    }
}