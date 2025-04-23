package com.example.ui_components.components.complex_components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.constants.enums.AgeUnit
import com.example.model.Age
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.spacing
import com.example.ui_components.components.items.DetailsItem
import com.example.ui_components.icons.HospitalAutomationIcons
import com.example.ui_components.R
import com.example.ui_components.components.texts.IndexedText
import com.example.ui_components.components.texts.TitleWithSubtitle
import com.example.ui_components.model.InteractionData

@Composable
fun VaccineDetails(
    fromAge: Age,
    toAge: Age,
    quantity: Int,
    quantityUnit: String,
    description: String,
    interactions: List<InteractionData>,
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
            modifier = Modifier.padding(
                vertical = MaterialTheme.spacing.large24,
                horizontal = MaterialTheme.spacing.medium16
            )
                .verticalScroll(
                    rememberScrollState()
                ),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
        ) {
            DetailsItem(
                iconRes = HospitalAutomationIcons.fromAge,
                modifier = Modifier.fillMaxWidth(),
                iconBackgroundColor = MaterialTheme.colorScheme.primaryContainer,
                title = stringResource(R.string.from_age),
                description = "${fromAge.value} ${fromAge.unit}",
            )
            Spacer(Modifier.height(MaterialTheme.spacing.large24))
            DetailsItem(
                iconRes = HospitalAutomationIcons.toAge,
                modifier = Modifier.fillMaxWidth(),
                iconBackgroundColor = MaterialTheme.colorScheme.primaryContainer,
                title = stringResource(R.string.to_age),
                description = "${toAge.value} ${toAge.unit}",
            )
            Spacer(Modifier.height(MaterialTheme.spacing.large24))
            DetailsItem(
                iconRes = HospitalAutomationIcons.vaccines,
                modifier = Modifier.fillMaxWidth(),
                iconBackgroundColor = MaterialTheme.colorScheme.primaryContainer,
                title = stringResource(R.string.quantity),
                description = "$quantity $quantityUnit",
            )
            Spacer(Modifier.height(MaterialTheme.spacing.large24))
            TitleWithSubtitle(
                title = R.string.description,
                subtitle = description,
            )
            Spacer(Modifier.height(MaterialTheme.spacing.large24))
            Text(
                text = stringResource(R.string.interactions),
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(Modifier.height(MaterialTheme.spacing.small8))
            interactions.forEachIndexed { index, interaction ->
                IndexedText(
                    index = index+1,
                    title = interaction.title,
                    body = interaction.description,
                    modifier = Modifier.fillMaxWidth(1f)
                )
                if(index < interactions.size-1)
                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.small8))
            }
        }
    }
}

@Preview
@Composable
fun VaccineDetailsPreview() {
    Hospital_AutomationTheme {
        VaccineDetails(
            fromAge = Age(value = 1, unit = AgeUnit.DAY),
            toAge = Age(value = 3, unit = AgeUnit.MONTH),
            quantity = 70,
            quantityUnit = "syringes",
            description = "The vaccine contains a weakened form of the rubella virus. When given, it triggers your body to produce antibodies against the virus, providing immunity.",
            interactions = listOf(
                InteractionData(
                    title = "Immunodeficiency conditions",
                    description = "Individuals with conditions like HIV/AIDS or other immune system disorders may also have a weakened response to the vaccine."
                ),
                InteractionData(
                    title = "Immunodeficiency conditions",
                    description = "The MMR vaccine contains trace amounts of neomycin and gelatin. Individuals with severe allergies to these substances may experience an allergic reaction."
                ),
                InteractionData(
                    title = "Egg allergy",
                    description = "While the MMR vaccine is grown in chick embryo cell culture, severe reactions in individuals with egg allergies are rare. However, it's essential to inform the healthcare provider about any egg allergies."
                )
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }

}