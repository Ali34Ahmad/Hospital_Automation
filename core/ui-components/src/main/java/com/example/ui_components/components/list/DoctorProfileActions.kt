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
import com.example.ui_components.icons.HospitalAutomationIcons

@Composable
fun DoctorProfileActions(
    onAppointmentsHistoryClick: () -> Unit,
    onPrescriptionsClick: () -> Unit,
    onMedicalRecordsClick: () -> Unit,
    onEmploymentHistoryClick: () -> Unit,
    onDeactivateAccountClick: () -> Unit,
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
        )
        ProfileActionsItem(
            onClick = onPrescriptionsClick,
            modifier = Modifier.fillMaxWidth(),
            iconRes = HospitalAutomationIcons.prescription,
            title = stringResource(R.string.appointments),
            showUnderline = true,
        )
        ProfileActionsItem(
            onClick = onMedicalRecordsClick,
            modifier = Modifier.fillMaxWidth(),
            iconRes = HospitalAutomationIcons.certificate,
            title = stringResource(R.string.medical_records),
            showUnderline = true,
        )
        ProfileActionsItem(
            onClick = onEmploymentHistoryClick,
            modifier = Modifier.fillMaxWidth(),
            iconRes = HospitalAutomationIcons.employment_history,
            title = stringResource(R.string.employment_history),
            showUnderline = true,
        )
        ProfileActionsItem(
            onClick = onDeactivateAccountClick,
            modifier = Modifier.fillMaxWidth(),
            iconRes = R.drawable.ic_deactivate_account,
            title = stringResource(R.string.deactivate_account),
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
        DoctorProfileActions(
            onAppointmentsHistoryClick = {},
            onPrescriptionsClick = {},
            onMedicalRecordsClick = {},
            onEmploymentHistoryClick = {},
            onDeactivateAccountClick = {},
        )
    }
}

















