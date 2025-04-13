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
fun EmployeeProfileActionsCard(
    onAddedChildrenItemClick:()->Unit,
    onEmploymentHistoryItemClick:()->Unit,
    onDeactivateAccountItemClick:()->Unit,
    modifier: Modifier = Modifier,
) {
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
        )
        ProfileActionsItem(
            onClick = onEmploymentHistoryItemClick,
            modifier = Modifier.fillMaxWidth(),
            iconRes = AppIcons.Outlined.employmentHistory,
            title = stringResource(R.string.employment_history),
            showUnderline = true,
        )
        ProfileActionsItem(
            onClick = onDeactivateAccountItemClick,
            modifier = Modifier.fillMaxWidth(),
            iconRes = AppIcons.Outlined.deactivateAccount,
            title = stringResource(R.string.deactivate_account),
            showUnderline = false,
            iconBackgroundColor = MaterialTheme.colorScheme.errorContainer,
            iconColor = MaterialTheme.colorScheme.error,
            titleColor = MaterialTheme.colorScheme.error
        )

    }
}

@DarkAndLightModePreview
@Composable
fun EmployeeProfileActionsCardPreview() {
    Hospital_AutomationTheme {
        Surface {
            EmployeeProfileActionsCard(
                modifier = Modifier.padding(MaterialTheme.spacing.medium16),
                onAddedChildrenItemClick = {},
                onDeactivateAccountItemClick = {},
                onEmploymentHistoryItemClick = {},
            )
        }
    }
}