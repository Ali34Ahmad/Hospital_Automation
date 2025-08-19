package com.example.ui_components.components.card

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
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
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.constants.icons.AppIcons
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.items.DetailsItem

@Composable
fun PatientInfoCard(
    imgUrl: String,
    name: String,
    @StringRes firstTitle: Int,
    @StringRes secondTitle: Int,
    firstSubtitle: String,
    secondSubtitle: String,
    onClick: () -> Unit,
    @DrawableRes firstIcon: Int,
    @DrawableRes secondIcon: Int,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.clickable{onClick()},
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        shape = MaterialTheme.shapes.small
    ) {
        Column(
            modifier = Modifier.padding(
                MaterialTheme.spacing.medium16
            ),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.large24),
            horizontalAlignment = Alignment.Start,
        ) {
            // top section
            Row(
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium16),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if (!LocalInspectionMode.current) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(imgUrl)
                            .crossfade(true)
                            .build(),
                        contentDescription = null,
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.primaryContainer
                            )
                            .size(MaterialTheme.sizing.medium40)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop,
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
                            painter = painterResource(AppIcons.Outlined.child),
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
                    iconRes = secondIcon,
                    modifier = Modifier.weight(1f),
                    title = stringResource(firstTitle),
                    description = firstSubtitle,
                )
                DetailsItem(
                    iconRes = firstIcon,
                    modifier = Modifier.weight(1f),
                    title = stringResource(secondTitle),
                    description = secondSubtitle,
                )
            }
        }
    }
}


@DarkAndLightModePreview
@Composable
fun MedicalRecordCardPreview() {
    Hospital_AutomationTheme {
        PatientInfoCard(
            imgUrl = "",
            name = "Ali Ahmad",
            firstTitle = R.string.appointments,
            secondTitle = R.string.prescriptions,
            firstSubtitle = "3",
            secondSubtitle = "1",
            modifier = Modifier.fillMaxWidth(),
            onClick = {},
            firstIcon = AppIcons.Outlined.prescription,
            secondIcon = AppIcons.Outlined.prescription
        )
    }
}
@DarkAndLightModePreview
@Composable
fun PrescriptionCardCardPreview() {
    Hospital_AutomationTheme {
        PatientInfoCard(
            imgUrl = "",
            name = "Ali Ahmad",
            firstTitle = R.string.medicines,
            secondTitle = R.string.date,
            firstSubtitle = "3 Medicines",
            secondSubtitle = "23/3/2025",
            modifier = Modifier.fillMaxWidth(),
            onClick = {},
            firstIcon = AppIcons.Outlined.bloodType,
            secondIcon = AppIcons.Outlined.upcomingEvent
        )
    }
}