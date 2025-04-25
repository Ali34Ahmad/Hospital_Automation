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
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.items.DetailsItem
import com.example.ui_components.icons.HospitalAutomationIcons

@Composable
fun MedicalRecordCard(
    imgUrl: String,
    name: String,
    numberOfAppointments: Int,
    numberOfPrescriptions: Int,
    onClick: () -> Unit,
    onPrescriptionsClick: () -> Unit,
    onAppointmentsClick: () -> Unit,
    modifier: Modifier = Modifier,
    @DrawableRes prescriptionsIcon: Int = HospitalAutomationIcons.prescription,
    @DrawableRes appointmentsIcon: Int = HospitalAutomationIcons.prescription
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
                            painter = painterResource(HospitalAutomationIcons.child),
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
                    iconRes = appointmentsIcon,
                    modifier = Modifier.weight(1f),
                    title = stringResource(R.string.appointments),
                    description = numberOfAppointments.toString(),
                    onClick = onAppointmentsClick
                )
                DetailsItem(
                    iconRes = prescriptionsIcon,
                    modifier = Modifier.weight(1f),
                    title = stringResource(R.string.prescriptions),
                    description = numberOfPrescriptions.toString(),
                    onClick = onPrescriptionsClick
                )
            }
        }
    }
}


@DarkAndLightModePreview
@Composable
fun MedicalRecordCard2Preview() {
    Hospital_AutomationTheme {
        MedicalRecordCard(
            imgUrl = "",
            name = "Ali Ahmad",
            numberOfAppointments = 3,
            numberOfPrescriptions = 1,
            modifier = Modifier.fillMaxWidth(),
            onClick = {},
            onAppointmentsClick = {},
            onPrescriptionsClick = {}
        )
    }
}