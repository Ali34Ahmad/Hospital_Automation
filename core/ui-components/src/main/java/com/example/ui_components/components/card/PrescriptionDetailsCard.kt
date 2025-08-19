package com.example.ui_components.components.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.constants.icons.AppIcons
import com.example.ext.toAppropriateDateFormat
import com.example.ext.toAppropriateNameFormat
import com.example.model.prescription.Prescription
import com.example.model.prescription.PrescriptionDetails
import com.example.model.user.FullName
import com.example.model.user.UserMainInfo
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.items.DetailsItem
import java.time.LocalDate

@Composable
fun PrescriptionDetailsCard(
    prescriptionDetails: PrescriptionDetails,
    onGoToPatientProfile: (userId: Int) -> Unit,
    onGoToChildProfile: (childId: Int) -> Unit,
    onMedicinesDetailsItemClick: () -> Unit,
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
            prescriptionDetails.patientMainInfo?.let {
                DetailsItem(
                    iconRes = null,
                    imageUrl = prescriptionDetails.patientMainInfo?.imageUrl,
                    modifier = Modifier.fillMaxWidth(),
                    iconBackgroundColor = MaterialTheme.colorScheme.primaryContainer,
                    title = stringResource(R.string.patient),
                    description = prescriptionDetails.patientMainInfo?.fullName?.toAppropriateNameFormat()
                        ?: "",
                    onClick = {
                        onGoToPatientProfile(
                            prescriptionDetails.patientMainInfo?.id ?: -1
                        )
                    },
                )
            }
            prescriptionDetails.childMainInfo?.let {
                DetailsItem(
                    iconRes = null,
                    modifier = Modifier.fillMaxWidth(),
                    iconBackgroundColor = MaterialTheme.colorScheme.primaryContainer,
                    title = stringResource(R.string.child),
                    description = prescriptionDetails.childMainInfo?.fullName?.toAppropriateNameFormat()
                        ?: "",
                    onClick = {
                        onGoToChildProfile(
                            prescriptionDetails.childMainInfo?.id ?: -1
                        )
                    },
                )
            }

            DetailsItem(
                iconRes = AppIcons.Outlined.medicine,
                modifier = Modifier.fillMaxWidth(),
                iconBackgroundColor = MaterialTheme.colorScheme.primaryContainer,
                title = stringResource(R.string.medicines),
                description = prescriptionDetails.medicines.size.toString(),
                onClick = onMedicinesDetailsItemClick,
            )
            DetailsItem(
                iconRes = AppIcons.Outlined.code,
                modifier = Modifier.fillMaxWidth(),
                iconBackgroundColor = MaterialTheme.colorScheme.primaryContainer,
                title = stringResource(R.string.code),
                description = prescriptionDetails.prescription.code,
            )
            DetailsItem(
                iconRes = AppIcons.Outlined.specificDate,
                modifier = Modifier.fillMaxWidth(),
                iconBackgroundColor = MaterialTheme.colorScheme.primaryContainer,
                title = stringResource(R.string.date),
                description = prescriptionDetails.prescription.createdAt.toAppropriateDateFormat(),
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun PrescriptionDetailCardPreview() {
    Hospital_AutomationTheme {
        Surface {
            PrescriptionDetailsCard(
                prescriptionDetails = PrescriptionDetails(
                    prescription = Prescription(
                        patientId = 1,
                        childId = 1,
                        code = "2322",
                        doctorId = 1,
                        numberOfMedicines = 3,
                        createdAt = LocalDate.now(),
                        id = 1,
                    ),
                    doctorMainInfo = UserMainInfo(
                        id = 1,
                        fullName = FullName("a", "a", "adf"),
                        imageUrl = "",
                        subInfo = "Dentist"
                    ),
                    patientMainInfo = UserMainInfo(
                        id = 1,
                        fullName = FullName("a", "a", "adf"),
                        imageUrl = "",
                        subInfo = null
                    ),
                    childMainInfo = UserMainInfo(
                        id = 1,
                        fullName = FullName("a", "a", "adf"),
                        imageUrl = "",
                        subInfo = null
                    ),
                    medicines = emptyList()
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.medium16),
                onGoToPatientProfile = { },
                onMedicinesDetailsItemClick = { },
                onGoToChildProfile = {},
            )
        }
    }
}