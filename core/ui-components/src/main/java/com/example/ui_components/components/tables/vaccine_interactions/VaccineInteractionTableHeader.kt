package com.example.ui_components.components.tables.vaccine_interactions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.R

@Composable
fun VaccineInteractionTableHeader(modifier: Modifier = Modifier) {

    val surfaceColor = MaterialTheme.colorScheme.outlineVariant
    val borderWidth = MaterialTheme.sizing.extraSmall1

    val innerRowModifier = modifier
        .drawBehind {
            val strokeWidth = borderWidth.toPx()
            val yBottomHorizontalLine = size.height - strokeWidth / 2
            drawLine(
                color = surfaceColor,
                start = Offset(0f, yBottomHorizontalLine),
                end = Offset(size.width, yBottomHorizontalLine),
                strokeWidth = strokeWidth
            )
            drawLine(
                color = surfaceColor,
                start = Offset(0f, 0f),
                end = Offset(size.width, 0f),
                strokeWidth = strokeWidth
            )
        }

    val textPadding = Modifier.padding(
        vertical = MaterialTheme.spacing.small8,
        horizontal = MaterialTheme.spacing.small12,
    )

    Row(
        modifier = innerRowModifier
            .padding(
                start = MaterialTheme.spacing.medium16,
                end = MaterialTheme.spacing.extraSmall4,
            )
            .height(IntrinsicSize.Min),
    ) {
        Text(
            text = stringResource(R.string.name),
            modifier = Modifier.weight(0.4f).then(textPadding),
            style = MaterialTheme.typography.bodyMedium,
        )
        Spacer(
            modifier=Modifier.width(1.dp)
                .fillMaxHeight()
                .background(MaterialTheme.colorScheme.outlineVariant)
        )
        Text(
            text = stringResource(R.string.description),
            modifier = Modifier.weight(0.6f).then(textPadding),
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}