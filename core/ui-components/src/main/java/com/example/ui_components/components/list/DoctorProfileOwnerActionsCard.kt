package com.example.ui_components.components.list

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
fun DoctorProfileOwnerActionsCard(
    onAppointmentsHistoryClick: () -> Unit,
    isAppointmentsItemEnabled: Boolean,
    onPrescriptionsClick: () -> Unit,
    isPrescriptionsItemEnabled: Boolean,
    onMedicalRecordsClick: () -> Unit,
    isMedicalRecordsItemEnabled:Boolean,
    onEmploymentHistoryClick: () -> Unit,
    isEmploymentHistoryItemEnabled:Boolean,
    onDeactivateAccountClick: () -> Unit,
    onReactivateAccountClick: () -> Unit,
    showDeactivationItem:Boolean,
    onLogoutClick: () -> Unit,
    isAccountDeactivated: Boolean,
    modifier: Modifier = Modifier,
) {
    val (activationActionText, activationActionIcon,activationActionOnClick) = if (!isAccountDeactivated) {
        Triple(
            stringResource(R.string.deactivate_my_account),
            AppIcons.Outlined.deactivateAccount,
            onDeactivateAccountClick
        )
    } else {
        Triple(
            stringResource(R.string.reactivate_my_account),
            AppIcons.Outlined.reactivateAccount,
            onReactivateAccountClick
        )
    }

    ProfileActionsList(
        modifier = modifier
    ) {
        ProfileActionsItem(
            onClick = onAppointmentsHistoryClick,
            modifier = Modifier.fillMaxWidth(),
            iconRes = AppIcons.Outlined.upcomingEvent,
            title = stringResource(R.string.appointments),
            showUnderline = true,
            enabled = isAppointmentsItemEnabled,
        )
        ProfileActionsItem(
            onClick = onPrescriptionsClick,
            modifier = Modifier.fillMaxWidth(),
            iconRes = AppIcons.Outlined.prescription,
            title = stringResource(R.string.prescriptions),
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
                onClick = activationActionOnClick,
                modifier = Modifier.fillMaxWidth(),
                iconRes = activationActionIcon,
                title = activationActionText,
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
fun DoctorProfileOwnerActionsPreview() {
    Hospital_AutomationTheme {
        DoctorProfileOwnerActionsCard(
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
            isAccountDeactivated = true,
            onReactivateAccountClick = {},
        )
    }
}

















