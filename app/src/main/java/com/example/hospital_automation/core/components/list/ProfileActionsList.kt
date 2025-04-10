package com.example.hospital_automation.core.components.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import com.example.hospital_automation.R
import com.example.hospital_automation.core.components.items.ProfileActionsItem
import com.example.hospital_automation.ui.helper.DarkAndLightModePreview
import com.example.hospital_automation.ui.theme.Hospital_AutomationTheme

@Composable
fun ProfileActionsList(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = modifier.clip(MaterialTheme.shapes.small)
    ) {
        content()
    }

}

@DarkAndLightModePreview
@Composable
fun ProfileActionsListPreview() {
    Hospital_AutomationTheme {
        Surface {
            ProfileActionsList{
                ProfileActionsItem(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth(),
                    iconRes = R.drawable.ic_child,
                    title = stringResource(R.string.added_children),
                    showUnderline = true,
                )
                ProfileActionsItem(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth(),
                    iconRes = R.drawable.ic_employement_history,
                    title = stringResource(R.string.employement_history),
                    showUnderline = true,
                )
                ProfileActionsItem(
                    onClick = {},
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
    }
}