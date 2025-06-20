package com.example.ui_components.components.list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui_components.R
import com.example.ui_components.components.items.ProfileActionsItem
import com.example.constants.icons.AppIcons

@Composable
fun DoctorProfileActionsCard(
    onAppointmentsHistoryClick: () -> Unit,
    isAppointmentsItemEnabled: Boolean,
    onPrescriptionsClick: () -> Unit,
    isPrescriptionsItemEnabled: Boolean,
    onMedicalRecordsClick: () -> Unit,
    isMedicalRecordsItemEnabled:Boolean,
    onEmploymentHistoryClick: () -> Unit,
    isEmploymentHistoryItemEnabled:Boolean,
    onDeactivateAccountClick: () -> Unit,
    showDeactivationItem:Boolean,
    onLogoutClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ProfileActionsList(
        modifier = modifier
    ) {
        ProfileActionsItem(
            onClick = onAppointmentsHistoryClick,
            modifier = Modifier.fillMaxWidth(),
            iconRes = R.drawable.ic_employement_history,
            title = stringResource(R.string.appointments),
            showUnderline = true,
            enabled = isAppointmentsItemEnabled,
        )
        ProfileActionsItem(
            onClick = onPrescriptionsClick,
            modifier = Modifier.fillMaxWidth(),
            iconRes = AppIcons.Outlined.prescription,
            title = stringResource(R.string.appointments),
            showUnderline = true,
            enabled = isPrescriptionsItemEnabled,
        )
        ProfileActionsItem(
            onClick = onMedicalRecordsClick,
            modifier = Modifier.fillMaxWidth(),
            iconRes = AppIcons.Outlined.certificate,
            title = stringResource(R.string.medical_records),
            showUnderline = true,
            enabled = isMedicalRecordsItemEnabled,
        )
        ProfileActionsItem(
            onClick = onEmploymentHistoryClick,
            modifier = Modifier.fillMaxWidth(),
            iconRes = AppIcons.Outlined.employmentHistory,
            title = stringResource(R.string.employment_history),
            showUnderline = true,
            enabled = isEmploymentHistoryItemEnabled,
        ) 
        if (showDeactivationItem){
            ProfileActionsItem(
                onClick = onDeactivateAccountClick,
                modifier = Modifier.fillMaxWidth(),
                iconRes = R.drawable.ic_deactivate_account,
                title = stringResource(R.string.deactivate_account),
                showUnderline = true,
                titleColor = MaterialTheme.colorScheme.error,
                iconBackgroundColor = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.4f),
                iconColor = MaterialTheme.colorScheme.onErrorContainer,
            )
        }
        ProfileActionsItem(
            onClick = onLogoutClick,
            modifier = Modifier.fillMaxWidth(),
            iconRes = R.drawable.ic_logout,
            title = stringResource(R.string.logout),
            showUnderline = false,
            titleColor = MaterialTheme.colorScheme.error,
            iconBackgroundColor = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.4f),
            iconColor = MaterialTheme.colorScheme.onErrorContainer,
        )

    }
}

@DarkAndLightModePreview
@Composable
fun DoctorProfileActionsPreview() {
    Hospital_AutomationTheme {
        DoctorProfileActionsCard(
            onAppointmentsHistoryClick = {},
            onPrescriptionsClick = {},
            onMedicalRecordsClick = {},
            onEmploymentHistoryClick = {},
            onDeactivateAccountClick = {},
            onLogoutClick = {},
            isAppointmentsItemEnabled = false,
            isPrescriptionsItemEnabled = true,
            isMedicalRecordsItemEnabled = true,
            isEmploymentHistoryItemEnabled = false,
            showDeactivationItem = true,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

















