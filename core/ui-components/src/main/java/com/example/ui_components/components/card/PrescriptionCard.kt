package com.example.ui_components.components.card

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.example.constants.icons.AppIcons
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.items.DetailsItem
import com.example.ui_components.components.network_image.NetworkImage
import com.example.ui_components.components.network_image.NetworkImageLoader
import com.example.ui_components.components.network_image.SmallNetworkImageError

@Composable
fun PrescriptionCard(
    imgUrl: String,
    name: String,
    numberOfMedicines: Int,
    date: String,
    onClick: () -> Unit,
    isChild: Boolean,
    modifier: Modifier = Modifier,
    @DrawableRes medicinesIcon: Int = AppIcons.Outlined.bloodType,
    @DrawableRes dateIcon: Int = AppIcons.Outlined.update
) {
    Card(
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small12),
            horizontalAlignment = Alignment.Start,
        ) {
            // top section
            Row(
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium16),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(
                    start = MaterialTheme.spacing.medium16,
                    end = MaterialTheme.spacing.medium16,
                    top = MaterialTheme.spacing.small12,

                    ),
            ) {
                if (!isChild) {
                    NetworkImage(
                        model = imgUrl,
                        modifier = Modifier
                            .size(MaterialTheme.sizing.medium40)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop,
                        loading = {
                            NetworkImageLoader(
                                modifier = Modifier
                                    .size(MaterialTheme.sizing.medium40)
                                    .clip(CircleShape)
                            )
                        },
                        errorCompose = {
                            SmallNetworkImageError(
                                modifier = Modifier
                                    .size(MaterialTheme.sizing.medium40)
                                    .clip(CircleShape),
                            )
                        }
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .size(MaterialTheme.sizing.medium40)
                            .background(
                                color = MaterialTheme.colorScheme.primaryContainer,
                                CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            modifier = Modifier.fillMaxSize(0.8f),
                            painter = painterResource(AppIcons.Outlined.father),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
                Text(
                    text = name,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            // bottom section
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                DetailsItem(
                    iconRes = medicinesIcon,
                    modifier = Modifier.weight(1f),
                    title = stringResource(R.string.medicines),
                    description = numberOfMedicines.toString(),
                    iconBackgroundColor = MaterialTheme.colorScheme.primaryContainer
                )
                DetailsItem(
                    iconRes = dateIcon,
                    modifier = Modifier.weight(1f),
                    title = stringResource(R.string.date),
                    description = date,
                    iconBackgroundColor = MaterialTheme.colorScheme.primaryContainer
                )
            }
        }
    }
}


@Preview
@Composable
fun PrescriptionCardPreview() {
    Hospital_AutomationTheme {
        PrescriptionCard(
            imgUrl = "",
            name = "Mariam Saoud",
            numberOfMedicines = 2,
            date = "30/1/2025",
            onClick = {},
            isChild = false,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Preview
@Composable
fun PrescriptionChildCardPreview() {
    Hospital_AutomationTheme {
        PrescriptionCard(
            imgUrl = "",
            name = "Mariam Saoud",
            numberOfMedicines = 2,
            date = "30/1/2025",
            onClick = {},
            isChild = true,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
