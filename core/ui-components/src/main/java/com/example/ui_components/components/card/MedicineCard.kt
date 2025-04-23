package com.example.ui_components.components.card

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.additionalColorScheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.buttons.HospitalAutomationButton
import com.example.ui_components.icons.HospitalAutomationIcons

@Composable
fun MedicineCard(
    imageUrl: String,
    medicineName: String,
    drug: Int,
    price: Int,
    numberOfPharmacies: Int,
    onClick: () -> Unit,
    onPharmaciesClick: () -> Unit,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    currency: String = "S.P",
    @DrawableRes secondaryIcon: Int = HospitalAutomationIcons.location,
    @StringRes buttonText: Int = R.string.add_to_prescription
) {
    Card(
        modifier = modifier
            .clickable(onClick = onClick)
        ,
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    ) {
        Column(
            modifier = Modifier
                .padding(
                vertical = MaterialTheme.spacing.small12,
                horizontal = MaterialTheme.spacing.small8
            ),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
        ) {
            if (!LocalInspectionMode.current){
                AsyncImage(
                    modifier = Modifier
                        .width(MaterialTheme.sizing.extraLarge164)
                        .height(MaterialTheme.sizing.extraLarge124)
                        .clip(
                            MaterialTheme.shapes.small
                        )
                        .align(Alignment.CenterHorizontally),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(imageUrl)
                        .crossfade(true)
                        .build(),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
            }else{
                Image(
                    modifier = Modifier
                        .width(MaterialTheme.sizing.extraLarge164)
                        .height(MaterialTheme.sizing.extraLarge124)
                        .clip(
                            MaterialTheme.shapes.small
                        )
                        .align(Alignment.CenterHorizontally),
                    contentScale = ContentScale.Crop,
                    painter = painterResource(R.drawable.vitamined3),
                    contentDescription = null
                )
            }
            Spacer(Modifier.height(MaterialTheme.spacing.small8))

            //Medicine name with drug amount
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = medicineName,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(Modifier.width(MaterialTheme.spacing.small8))
                Text(
                    text = "( $drug )",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.additionalColorScheme.onBackgroundVariantLight
                )
            }

            Spacer(Modifier.height(MaterialTheme.spacing.small8))

            Text(
                text = "$price $currency",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(Modifier.height(MaterialTheme.spacing.medium16))

            //pharmacies where it exists
            Row(
                modifier = Modifier.clickable(onClick = onPharmaciesClick),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    modifier = Modifier
                        .size(MaterialTheme.sizing.small24)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primaryContainer),
                    painter = painterResource(secondaryIcon),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(Modifier.width(MaterialTheme.spacing.small8))
                Text(
                    text = "$numberOfPharmacies "+ stringResource(R.string.pharmacies),
                    color = MaterialTheme.additionalColorScheme.onBackgroundVariantLight,
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(Modifier.width(MaterialTheme.spacing.large36))
                Icon(
                    modifier = Modifier.size(MaterialTheme.sizing.small18),
                    painter = painterResource(HospitalAutomationIcons.chevronRight),
                    contentDescription = null,
                    tint = MaterialTheme.additionalColorScheme.onBackgroundVariantLight
                )
            }
            Spacer(Modifier.height(MaterialTheme.spacing.medium16))
            HospitalAutomationButton(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                onClick = onButtonClick,
                text = stringResource(buttonText),
            )
        }
    }
}


@Preview
@Composable
fun MedicineCardPreview() {
    Hospital_AutomationTheme {
        MedicineCard(
            imageUrl = "",
            medicineName = "Vitamin D3",
            drug = 1000,
            price = 11900,
            numberOfPharmacies = 2,
            onClick = {},
            onPharmaciesClick = {},
            onButtonClick = {},
        )
    }
}























