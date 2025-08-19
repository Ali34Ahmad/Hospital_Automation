package com.example.ui_components.components.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.constants.icons.AppIcons
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.sizing
import com.example.ui_components.R
import com.example.ui_components.components.items.ProfileActionsItem

@Composable
fun DepartmentActionsCard(
    onAllDoctorsClick:()-> Unit,
    onAppointmentsClick:()-> Unit,
    onPrescriptionsClick:()-> Unit,
    onMedicalRecordsClick:()-> Unit,
    onContractHistoryClick:()-> Unit,
    onDeactivateClinicClick:()-> Unit,
    onReactivateClinicClick:()-> Unit,
    isActive: Boolean,
    hasAdminAccess: Boolean,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(MaterialTheme.sizing.small8),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ProfileActionsItem(
                iconRes = AppIcons.Outlined.stethoscope,
                title = stringResource(R.string.all_doctors),
                showUnderline = true,
                onClick = onAllDoctorsClick,
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
                iconRes = AppIcons.Outlined.medicalRecords,
                title = stringResource(R.string.medical_records),
                showUnderline = true,
                onClick = onMedicalRecordsClick,
            )
            ProfileActionsItem(
                iconRes = AppIcons.Outlined.workHistory,
                title = stringResource(R.string.contract_history),
                showUnderline = true,
                onClick = onContractHistoryClick,
            )
            if(hasAdminAccess){
                if (isActive) {
                    ProfileActionsItem(
                        iconRes = AppIcons.Outlined.deactivateAccount,
                        title = stringResource(R.string.deactivate_clinic),
                        iconColor = MaterialTheme.colorScheme.error,
                        titleColor = MaterialTheme.colorScheme.error,
                        iconBackgroundColor = MaterialTheme.colorScheme.errorContainer,
                        showUnderline = false,
                        onClick = onDeactivateClinicClick,
                    )
                } else {
                    ProfileActionsItem(
                        iconRes = AppIcons.Outlined.reactivateAccount,
                        title = stringResource(R.string.reactivate_clinic),
                        showUnderline = false,
                        onClick = onReactivateClinicClick,
                    )
                }
            }

        }
    }
}

@Preview
@Composable
fun DepartmentActionsPreview() {
    Hospital_AutomationTheme {
        DepartmentActionsCard(
            onAllDoctorsClick = {},
            onAppointmentsClick = {},
            onPrescriptionsClick = {},
            onMedicalRecordsClick = {},
            onContractHistoryClick = {},
            onDeactivateClinicClick = {},
            onReactivateClinicClick = {},
            isActive = true,
            hasAdminAccess = true
        )
    }
}