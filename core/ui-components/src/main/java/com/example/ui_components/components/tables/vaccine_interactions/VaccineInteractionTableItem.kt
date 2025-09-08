package com.example.ui_components.components.tables.vaccine_interactions

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import com.example.model.vaccine.VaccineInteraction
import com.example.ui.fake.createVaccineInteractionsSampleData
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing


@Composable
fun VaccineInteractionTableItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    interaction: VaccineInteraction,
) {
    val surfaceColor = MaterialTheme.colorScheme.outlineVariant
    val borderWidth = MaterialTheme.sizing.extraSmall1

    val innerRowModifier = modifier
        .drawBehind {
            val strokeWidth = borderWidth.toPx()
            val yHorizontalLine = size.height - strokeWidth / 2
            drawLine(
                color = surfaceColor,
                start = Offset(0f, yHorizontalLine),
                end = Offset(size.width, yHorizontalLine),
                strokeWidth = strokeWidth
            )
        }

    val textPadding = Modifier.padding(
        vertical = MaterialTheme.spacing.small8,
        horizontal = MaterialTheme.spacing.small12,
    )

    Row(
        modifier = innerRowModifier
            .clickable { onClick() }
            .padding(
                start = MaterialTheme.spacing.medium16,
                end = MaterialTheme.spacing.extraSmall4,
            )
            .height(IntrinsicSize.Min),
    ) {
        Text(
            text = interaction.name,
            modifier = Modifier.weight(0.4f).then(textPadding),
            style = MaterialTheme.typography.bodyMedium,
        )
        Spacer(
            modifier=Modifier.width(1.dp)
                .fillMaxHeight()
                .background(MaterialTheme.colorScheme.outlineVariant)
        )
        Text(
            text = interaction.description,
            modifier = Modifier.weight(0.6f).then(textPadding),
            style = MaterialTheme.typography.bodyMedium,
        )

    }
}

@DarkAndLightModePreview
@Composable
fun VaccineInteractionsTableItemPreview() {
    Hospital_AutomationTheme {
        Surface {
            Column(
                modifier = Modifier
                    .padding(MaterialTheme.spacing.medium16),
            ) {
                VaccineInteractionTableItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background),
                    onClick = { },
                    interaction = createVaccineInteractionsSampleData()[0],
                )
                VaccineInteractionTableItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background),
                    onClick = { },
                    interaction = createVaccineInteractionsSampleData()[0],
                )

            }
        }
    }
}