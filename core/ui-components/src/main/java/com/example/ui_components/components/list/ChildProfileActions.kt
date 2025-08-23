package com.example.ui_components.components.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.constants.icons.AppIcons
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.items.ProfileActionsItem

@Composable
fun ChildProfileActions(
    onVaccinationTableClick:()-> Unit,
    onAppointmentsClick: ()-> Unit,
    onPrescriptionsClick: ()-> Unit,
    onMedicalRecordsClick: ()-> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.small,
    colors: CardColors = CardDefaults
        .cardColors(containerColor = MaterialTheme.colorScheme.background)
) {
    Card(
        modifier = modifier,
        shape = shape,
        colors = colors
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ProfileActionsItem(
                iconRes = AppIcons.Outlined.syringe,
                title = stringResource(R.string.vaccination_table),
                showUnderline = true,
                onClick = onVaccinationTableClick,
            )
            ProfileActionsItem(
                iconRes = AppIcons.Outlined.upcomingEvent,
                title = stringResource(R.string.appointments),
                showUnderline = true,
                onClick = onAppointmentsClick,
            )
            ProfileActionsItem(
                iconRes = AppIcons.Outlined.prescription,
                title = stringResource(R.string.prescriptions),
                showUnderline = true,
                onClick = onPrescriptionsClick,
            )
            ProfileActionsItem(
                iconRes = AppIcons.Outlined.syringe,
                title = stringResource(R.string.medical_records),
                showUnderline = false,
                onClick = onMedicalRecordsClick,
            )
        }
    }
}

@Preview
@Composable
fun ChildProfileActionsCardPreview() {
    Hospital_AutomationTheme {
        Surface {
            ChildProfileActions(
                onVaccinationTableClick = {},
                onAppointmentsClick = {},
                onPrescriptionsClick = {},
                onMedicalRecordsClick = {},
                modifier = Modifier.padding(
                    vertical = MaterialTheme.spacing.large24,
                    horizontal = MaterialTheme.spacing.medium16
                ),
            )
        }
    }
}