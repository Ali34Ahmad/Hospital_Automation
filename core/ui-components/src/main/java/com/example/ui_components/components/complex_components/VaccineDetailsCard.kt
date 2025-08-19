package com.example.ui_components.components.complex_components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.spacing
import com.example.ui_components.components.items.DetailsItem
import com.example.constants.icons.AppIcons
import com.example.ui_components.R
import com.example.ui_components.components.texts.IndexedText
import com.example.ui_components.components.texts.TitleWithSubtitle
import com.example.model.helper.ext.toCapitalized
import com.example.model.vaccine.VaccineData
import com.example.ui.fake.createSampleVaccineList
import com.example.ui.helper.DarkAndLightModePreview

@Composable
fun VaccineDetailsCard(
    vaccine: VaccineData,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        shape = MaterialTheme.shapes.small
    ) {
        Column(
            modifier = Modifier
                .padding(
                    vertical = MaterialTheme.spacing.large24,
                ),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
        ) {
            DetailsItem(
                iconRes = AppIcons.Outlined.fromAge,
                modifier = Modifier.fillMaxWidth(),
                iconBackgroundColor = MaterialTheme.colorScheme.primaryContainer,
                title = stringResource(R.string.from_age),
                description = "${vaccine.minAge.value} ${vaccine.minAge.unit.name.toCapitalized()}",
            )
            DetailsItem(
                iconRes = AppIcons.Outlined.toAge,
                modifier = Modifier.fillMaxWidth(),
                iconBackgroundColor = MaterialTheme.colorScheme.primaryContainer,
                title = stringResource(R.string.to_age),
                description = "${vaccine.maxAge.value} ${vaccine.maxAge.unit.name.toCapitalized()}",
            )
            DetailsItem(
                iconRes = AppIcons.Outlined.vaccines,
                modifier = Modifier.fillMaxWidth(),
                iconBackgroundColor = MaterialTheme.colorScheme.primaryContainer,
                title = stringResource(R.string.quantity),
                description = "${vaccine.quantity} Syringes",
            )
            Spacer(Modifier.height(MaterialTheme.spacing.large24))
            TitleWithSubtitle(
                title = R.string.description,
                subtitle = vaccine.description,
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(
                        horizontal = MaterialTheme.spacing.medium16
                    )
            )
            Spacer(Modifier.height(MaterialTheme.spacing.large24))
            Text(
                text = stringResource(R.string.interactions),
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(
                        horizontal = MaterialTheme.spacing.medium16
                    )
            )
            Spacer(Modifier.height(MaterialTheme.spacing.small8))
            val interactions = vaccine.interactions
            interactions?.forEachIndexed { index, interaction ->
                IndexedText(
                    index = index + 1,
                    title = interaction.name,
                    body = interaction.description,
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(
                            horizontal = MaterialTheme.spacing.medium16
                        )
                )
                if (index < interactions.size - 1)
                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.small8))
            }
        }
    }
}

@DarkAndLightModePreview
@Composable
fun VaccineDetailsPreview() {
    Hospital_AutomationTheme {
        Surface {
            VaccineDetailsCard(
                vaccine = createSampleVaccineList()[0],
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.medium16)
            )
        }
    }

}