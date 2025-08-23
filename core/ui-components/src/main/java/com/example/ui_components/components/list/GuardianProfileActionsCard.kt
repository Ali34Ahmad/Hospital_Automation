package com.example.ui_components.components.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
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
fun GuardianProfileActionsCard(
    onChildrenClick: () -> Unit,
    onAppointmentsClick:()-> Unit,
    onPrescriptionsClick:()-> Unit,
    onMedicalRecordClick:()-> Unit,
    onSuspendUserAccountClick:()-> Unit,
    onReactivateUserAccount:()-> Unit,
    hasAdminAccess: Boolean,
    isActive: Boolean,
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.small,
    colors: CardColors = CardDefaults
        .cardColors(containerColor = MaterialTheme.colorScheme.background)
) {
    Card(
        modifier = modifier,
        shape = shape,
        colors = colors
    ){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            //This action is available in general.
            ProfileActionsItem(
                iconRes = AppIcons.Outlined.child,
                title = stringResource(R.string.children),
                showUnderline = hasAdminAccess,
                onClick = onChildrenClick,
            )
            //This actions are only available for admin
            if(hasAdminAccess){
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
                    title = stringResource(R.string.medical_record),
                    showUnderline = true,
                    onClick = onMedicalRecordClick,
                )
                if (isActive){
                    ProfileActionsItem(
                        iconRes = AppIcons.Outlined.deactivateAccount,
                        title = stringResource(R.string.suspend_user_account),
                        iconColor = MaterialTheme.colorScheme.error,
                        titleColor = MaterialTheme.colorScheme.error,
                        iconBackgroundColor = MaterialTheme.colorScheme.errorContainer,
                        showUnderline = false,
                        onClick = onSuspendUserAccountClick,
                    )
                }else{
                    ProfileActionsItem(
                        iconRes = AppIcons.Outlined.reactivateAccount,
                        title = stringResource(R.string.reactivate_user_account),
                        showUnderline = false,
                        onClick = onReactivateUserAccount,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun GuardianProfileWithAdminAccessCardPreview() {
    Hospital_AutomationTheme {
        Surface {
            GuardianProfileActionsCard(
                onChildrenClick = {},
                onAppointmentsClick = {},
                onPrescriptionsClick = {},
                onMedicalRecordClick = {},
                onSuspendUserAccountClick = {},
                onReactivateUserAccount = {},
                hasAdminAccess = true,
                isActive = true,
                modifier = Modifier.padding(MaterialTheme.spacing.medium16),
            )
        }
    }
}
@Preview
@Composable
fun GuardianProfileCardPreview() {
    Hospital_AutomationTheme {
        Surface() {
            GuardianProfileActionsCard(
                onChildrenClick = {},
                onAppointmentsClick = {},
                onPrescriptionsClick = {},
                onMedicalRecordClick = {},
                onSuspendUserAccountClick = {},
                onReactivateUserAccount = {},
                hasAdminAccess = false,
                isActive = true,
                modifier = Modifier.padding(MaterialTheme.spacing.medium16),
            )
        }
    }
}