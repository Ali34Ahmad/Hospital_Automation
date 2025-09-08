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
import androidx.compose.material3.Surface
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
import com.example.ui_components.components.items.FailedImage
import com.example.ui_components.components.network_image.NetworkImage
import com.example.ui_components.components.network_image.NetworkImageLoader


@Composable
fun MedicalRecordCard(
    patientId: Int?,
    childId: Int?,
    imgUrl: String?,
    name: String,
    numberOfAppointments: Int,
    numberOfPrescriptions: Int,
    onClick: () -> Unit,
    onPrescriptionsClick: () -> Unit,
    onAppointmentsClick: () -> Unit,
    modifier: Modifier = Modifier,
    @DrawableRes prescriptionsIcon: Int = AppIcons.Outlined.prescription,
    @DrawableRes appointmentsIcon: Int = AppIcons.Outlined.prescription
) {
    Card(
        modifier = modifier.clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        shape = MaterialTheme.shapes.small
    ) {
        Column(
            modifier = Modifier.padding(
                vertical = MaterialTheme.spacing.medium16
            ),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.large24),
            horizontalAlignment = Alignment.Start,
        ) {
            // top section
            Row(
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium16),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(
                    horizontal = MaterialTheme.spacing.medium16,
                )
            ) {
                if (imgUrl != null && patientId != null) {
                    NetworkImage(
                        model = imgUrl,
                        modifier = Modifier
                            .size(MaterialTheme.sizing.medium40)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop,
                        loading = {
                            NetworkImageLoader(
                                Modifier
                                .size(MaterialTheme.sizing.medium40)
                                .clip(CircleShape)
                            )
                        },
                        errorCompose = {
                            FailedImage()
                        }
                    )
                } else if (imgUrl == null && childId != null) {
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
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                DetailsItem(
                    iconRes = appointmentsIcon,
                    modifier = Modifier.weight(1f),
                    title = stringResource(R.string.appointments),
                    description = numberOfAppointments.toString(),
                    onClick = onAppointmentsClick,
                )
                DetailsItem(
                    iconRes = prescriptionsIcon,
                    modifier = Modifier.weight(1f),
                    title = stringResource(R.string.prescriptions),
                    description = numberOfPrescriptions.toString(),
                    onClick = onPrescriptionsClick,
                )
            }
        }
    }
}


@DarkAndLightModePreview
@Composable
fun MedicalRecordCardPatientPreview() {
    Hospital_AutomationTheme {
        Surface {
            MedicalRecordCard(
                imgUrl = "",
                name = "Ali Ahmad",
                numberOfAppointments = 3,
                numberOfPrescriptions = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.medium16),
                onClick = {},
                onAppointmentsClick = {},
                onPrescriptionsClick = {},
                patientId = 1,
                childId = null,
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun MedicalRecordCardChildPreview() {
    Hospital_AutomationTheme {
        Surface {
            MedicalRecordCard(
                imgUrl = null,
                name = "Ali Ahmad",
                numberOfAppointments = 3,
                numberOfPrescriptions = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.medium16),
                onClick = {},
                onAppointmentsClick = {},
                onPrescriptionsClick = {},
                patientId = null,
                childId = 2,
            )
        }
    }
}

