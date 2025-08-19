package com.example.ui_components.components.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import com.example.ui_components.R
import com.example.ui_components.components.items.ProfileActionsItem
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme

@Composable
fun ProfileActionsList(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
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
                    title = stringResource(R.string.employment_history),
                    showUnderline = true,
                )
                ProfileActionsItem(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth(),
                    iconRes = R.drawable.ic_deactivate_account,
                    title = stringResource(R.string.deactivate_my_account),
                    showUnderline = false,
                    titleColor = MaterialTheme.colorScheme.error,
                    iconBackgroundColor = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.4f),
                    iconColor = MaterialTheme.colorScheme.onErrorContainer,
                )
            }
        }
    }
}