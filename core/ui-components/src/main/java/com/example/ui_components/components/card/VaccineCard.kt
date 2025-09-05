package com.example.ui_components.components.card

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.example.ext.toAppropriateAgeFormat
import com.example.model.vaccine.VaccineData
import com.example.ui.fake.createSampleVaccineList
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.additionalColorScheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing

@Composable
fun VaccineCard(
    vaccine: VaccineData,
    onClick: (id: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .background(MaterialTheme.colorScheme.background)
            .clickable { onClick(vaccine.id?:-1) },
    ) {
        Column(
            modifier = Modifier.padding(MaterialTheme.spacing.medium16),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small8),
            ) {
                if (vaccine.quantity>0) {
                    Spacer(
                        modifier = Modifier
                            .size(MaterialTheme.sizing.extraSmall4)
                            .clip(CircleShape)
                            .background(MaterialTheme.additionalColorScheme.green)
                    )
                }
                Text(
                    text = vaccine.name.trim(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Spacer(modifier = Modifier.size(MaterialTheme.spacing.extraSmall4))
            Text(
                text = vaccine.description.trim(),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground,
            )
            Spacer(modifier = Modifier.size(MaterialTheme.spacing.medium16))
            Text(
                text = "${vaccine.minAge.toAppropriateAgeFormat()} - ${vaccine.maxAge.toAppropriateAgeFormat()}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.additionalColorScheme.onBackgroundVariant,
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun VaccineCardPreview() {
    Hospital_AutomationTheme {
        Surface {
            VaccineCard(
                vaccine = createSampleVaccineList()[0],
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.medium16),
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun VaccineCardNotAvailablePreview() {
    Hospital_AutomationTheme {
        Surface {
            VaccineCard(
                vaccine = createSampleVaccineList()[1],
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.medium16),
            )
        }
    }
}

