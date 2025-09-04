package com.example.ui_components.components.card

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import com.example.constants.icons.AppIcons
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.items.ProfileActionsItem

@Composable
fun EmployeeProfileOwnerActionsCard(
    onAddedChildrenItemClick: () -> Unit,
    isAddedChildrenEnabled: Boolean,
    onEmploymentHistoryItemClick: () -> Unit,
    isEmploymentHistoryEnabled: Boolean,
    onDeactivateAccountItemClick: () -> Unit,
    onReactivateAccountItemClick: () -> Unit,
    showDeactivateMyAccountItem: Boolean,
    isAccountDeactivated: Boolean,
    onLogoutItemClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val (activationActionText, activationActionIcon,activationActionOnClick) = if (!isAccountDeactivated) {
        Triple(
            stringResource(R.string.deactivate_account),
            AppIcons.Outlined.deactivateAccount,
            onDeactivateAccountItemClick
        )
    } else {
        Triple(
            stringResource(R.string.reactivate_account),
            AppIcons.Outlined.reactivateAccount,
            onReactivateAccountItemClick
        )
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = MaterialTheme.shapes.small),
    ) {
        ProfileActionsItem(
            onClick = onAddedChildrenItemClick,
            modifier = Modifier.fillMaxWidth(),
            iconRes = AppIcons.Outlined.child,
            title = stringResource(R.string.added_children),
            showUnderline = true,
            enabled = isAddedChildrenEnabled
        )
        ProfileActionsItem(
            onClick = onEmploymentHistoryItemClick,
            modifier = Modifier.fillMaxWidth(),
            iconRes = AppIcons.Outlined.employmentHistory,
            title = stringResource(R.string.employment_history),
            showUnderline = true,
            enabled = isEmploymentHistoryEnabled,
        )
        if (showDeactivateMyAccountItem){
            ProfileActionsItem(
                onClick = activationActionOnClick,
                modifier = Modifier.fillMaxWidth(),
                iconRes = activationActionIcon,
                title = activationActionText,
                showUnderline = true,
                iconBackgroundColor = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.4f),
                iconColor = MaterialTheme.colorScheme.error,
                titleColor = MaterialTheme.colorScheme.error,
            )
        }

        ProfileActionsItem(
            onClick = onLogoutItemClick,
            modifier = Modifier.fillMaxWidth(),
            iconRes = AppIcons.Outlined.logout,
            title = stringResource(R.string.logout),
            showUnderline = false,
            iconBackgroundColor = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.4f),
            iconColor = MaterialTheme.colorScheme.error,
            titleColor = MaterialTheme.colorScheme.error
        )

    }
}

@DarkAndLightModePreview
@Composable
fun EmployeeProfileOwnerActionsCardPreview() {
    Hospital_AutomationTheme {
        Surface {
            Column {
                EmployeeProfileOwnerActionsCard(
                    modifier = Modifier.padding(MaterialTheme.spacing.medium16),
                    onAddedChildrenItemClick = {},
                    onDeactivateAccountItemClick = {},
                    onEmploymentHistoryItemClick = {},
                    onLogoutItemClick = {},
                    isAddedChildrenEnabled = true,
                    showDeactivateMyAccountItem = false,
                    isAccountDeactivated = false,
                    onReactivateAccountItemClick = {},
                    isEmploymentHistoryEnabled=true,
                )
            }
        }
    }
}

@DarkAndLightModePreview
@Composable
fun EmployeeProfileOwnerActionsCardDeactivatedAccountPreview() {
    Hospital_AutomationTheme {
        Surface {
            Column {
                EmployeeProfileOwnerActionsCard(
                    modifier = Modifier.padding(MaterialTheme.spacing.medium16),
                    onAddedChildrenItemClick = {},
                    onDeactivateAccountItemClick = {},
                    onEmploymentHistoryItemClick = {},
                    onLogoutItemClick = {},
                    isAddedChildrenEnabled = true,
                    showDeactivateMyAccountItem = false,
                    isAccountDeactivated = true,
                    onReactivateAccountItemClick = {},
                    isEmploymentHistoryEnabled=false,
                )
            }
        }
    }
}
