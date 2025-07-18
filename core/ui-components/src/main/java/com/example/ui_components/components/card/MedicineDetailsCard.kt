package com.example.ui_components.components.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.constants.icons.AppIcons
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.spacing
import com.example.ui_components.components.network_image.NetworkImage
import com.example.ui_components.components.network_image.NetworkImageError
import com.example.ui_components.components.network_image.NetworkImageLoader
import com.example.ui_components.R
import com.example.ui_components.components.items.DetailsItem
import com.example.ui_components.components.items.TitleAndSubtitle

@Composable
fun MedicineDetailsCard(
    imageUrl: String?,
    name: String,
    price: Int,
    titer: Int,
    companyName: String,
    isAllowedWithoutPrescription: Boolean,
    composition: String,
    indications: String,
    onAlternativesItemClick: ()-> Unit,
    numberOfAlternatives: Int,
    modifier: Modifier = Modifier,
) {
    val allowanceDescription = if(isAllowedWithoutPrescription) stringResource(R.string.allowed)
    else stringResource(R.string.prescription_required)
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    ){
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            NetworkImage(
                model = imageUrl,
                Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth,
                errorCompose = {
                    NetworkImageError()
                },
                loading = {
                    NetworkImageLoader()
                }
            )
            Spacer(Modifier.height(MaterialTheme.spacing.medium16))
            //name and price row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.spacing.medium16),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = "$price ${stringResource(R.string.syrian_pound)}",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Spacer(Modifier.height(MaterialTheme.spacing.large44))
            DetailsItem(
                iconRes = AppIcons.Outlined.titer,
                iconBackgroundColor = MaterialTheme.colorScheme.primaryContainer,
                title = stringResource(R.string.titer),
                description = titer.toString(),
                modifier = Modifier
                    .fillMaxWidth(),
                contentPadding = PaddingValues(
                    horizontal = MaterialTheme.spacing.medium16
                )
            )
            Spacer(Modifier.height(MaterialTheme.spacing.large24))
            DetailsItem(
                contentPadding = PaddingValues(
                    horizontal = MaterialTheme.spacing.medium16
                ),
                iconRes = AppIcons.Outlined.factory,
                iconBackgroundColor = MaterialTheme.colorScheme.primaryContainer,
                title = stringResource(R.string.company),
                description = companyName,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(Modifier.height(MaterialTheme.spacing.large24))
            DetailsItem(
                iconRes = AppIcons.Outlined.prescription,
                iconBackgroundColor = MaterialTheme.colorScheme.primaryContainer,
                title = stringResource(R.string.allowance),
                description = allowanceDescription,
                modifier = Modifier
                    .fillMaxWidth(),
                contentPadding = PaddingValues(
                    horizontal = MaterialTheme.spacing.medium16
                )
            )
            Spacer(Modifier.height(MaterialTheme.spacing.large24))
            if (numberOfAlternatives!=0){
                DetailsItem(
                    iconRes = AppIcons.Outlined.alternative,
                    iconBackgroundColor = MaterialTheme.colorScheme.primaryContainer,
                    title = stringResource(R.string.alternatives),
                    description = numberOfAlternatives.toString(),
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentPadding = PaddingValues(
                        horizontal = MaterialTheme.spacing.medium16
                    ),
                    onClick = onAlternativesItemClick
                )
                Spacer(Modifier.height(MaterialTheme.spacing.large24))
            }
            TitleAndSubtitle(
                title = stringResource(R.string.pharmaceutical_composition),
                subtitle = composition,
                modifier = Modifier.padding(
                    horizontal = MaterialTheme.spacing.medium16
                ),
            )
            Spacer(Modifier.height(MaterialTheme.spacing.large24))
            TitleAndSubtitle(
                title = stringResource(R.string.pharmaceutical_Indications),
                subtitle = indications,
                modifier = Modifier.padding(
                    horizontal = MaterialTheme.spacing.medium16
                ),
            )
            Spacer(Modifier.height(MaterialTheme.spacing.large24))
        }
    }
}

@Preview
@Composable
fun MedicineDetailsCardPreview() {
    Hospital_AutomationTheme {
        MedicineDetailsCard(
            imageUrl = null,
            name = "Vitamine D3",
            price = 12000,
            titer = 1000,
            companyName = "Al Fares",
            isAllowedWithoutPrescription = false,
            composition = """
                Croscarmellose sodium + Sodium starch glycolate + Magnesium stearate + Lactose monohydrate
            """.trimIndent(),
            indications = """
                Always consult with a qualified healthcare professional for any health concerns or before making any decisions regarding your health or treatment.
            """.trimIndent(),
            onAlternativesItemClick = {},
            numberOfAlternatives = 3,
            modifier = Modifier.fillMaxWidth()
        )
    }
}