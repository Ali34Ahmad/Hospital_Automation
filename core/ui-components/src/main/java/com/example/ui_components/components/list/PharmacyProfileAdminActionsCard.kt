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
fun PharmacyProfileAdminActionsCard(
    onFulfilledPrescriptionsHistoryClick: () -> Unit,
    isFulfilledPrescriptionsItemEnabled: Boolean,
    onMedicinesClick: () -> Unit,
    isMedicinesItemEnabled: Boolean,
    onContractHistoryClick: () -> Unit,
    isContractHistoryItemEnabled:Boolean,
    onDeactivateAccountClick: () -> Unit,
    onReactivateAccountClick: () -> Unit,
    isAccountDeactivated: Boolean,
    showDeactivationItem:Boolean,
    onStopPharmacyClick: () -> Unit,
    modifier: Modifier = Modifier,
) {

    val (activationActionText, activationActionIcon,activationActionOnClick) = if (!isAccountDeactivated) {
        Triple(
            stringResource(R.string.deactivate_pharmacy),
            AppIcons.Outlined.deactivateAccount,
            onDeactivateAccountClick
        )
    } else {
        Triple(
            stringResource(R.string.reactivate_pharmacy),
            AppIcons.Outlined.reactivateAccount,
            onReactivateAccountClick
        )
    }

    ProfileActionsList(
        modifier = modifier
    ) {
        ProfileActionsItem(
            onClick = onFulfilledPrescriptionsHistoryClick,
            modifier = Modifier.fillMaxWidth(),
            iconRes = AppIcons.Outlined.fulfilledPrescription,
            title = stringResource(R.string.fulfilled_prescriptions),
            showUnderline = true,
            enabled = isFulfilledPrescriptionsItemEnabled,
        )
        ProfileActionsItem(
            onClick = onMedicinesClick,
            modifier = Modifier.fillMaxWidth(),
            iconRes = AppIcons.Outlined.medicine,
            title = stringResource(R.string.medicines),
            showUnderline = true,
            enabled = isMedicinesItemEnabled,
        )
        ProfileActionsItem(
            onClick = onContractHistoryClick,
            modifier = Modifier.fillMaxWidth(),
            iconRes = AppIcons.Outlined.employmentHistory,
            title = stringResource(R.string.contract_history),
            showUnderline = true,
            enabled = isContractHistoryItemEnabled,
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
            onClick = onStopPharmacyClick,
            modifier = Modifier.fillMaxWidth(),
            iconRes = AppIcons.Outlined.wavingHand,
            title = stringResource(R.string.stop_pharmacy),
            showUnderline = false,
            titleColor = MaterialTheme.colorScheme.error,
            iconBackgroundColor = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.4f),
            iconColor = MaterialTheme.colorScheme.onErrorContainer,
        )

    }
}

@DarkAndLightModePreview
@Composable
fun PharmacyProfileAdminActionsPreview() {
    Hospital_AutomationTheme {
        PharmacyProfileAdminActionsCard(
            onFulfilledPrescriptionsHistoryClick = {},
            onMedicinesClick = {},
            onContractHistoryClick = {},
            onDeactivateAccountClick = {},
            onStopPharmacyClick = {},
            isFulfilledPrescriptionsItemEnabled = false,
            isMedicinesItemEnabled = true,
            isContractHistoryItemEnabled = false,
            showDeactivationItem = true,
            modifier = Modifier.fillMaxWidth(),
            onReactivateAccountClick = {  },
            isAccountDeactivated = true,
        )
    }
}

















