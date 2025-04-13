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
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.items.ProfileActionsItem

@Composable
fun GuardianProfileActionsCard(
    onChildrenItemClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = MaterialTheme.shapes.small),
    ) {
        ProfileActionsItem(
            onClick = onChildrenItemClicked,
            modifier = Modifier.fillMaxWidth(),
            iconRes = R.drawable.ic_child,
            title = stringResource(R.string.children),
            showUnderline = false,
        )
    }
}

@DarkAndLightModePreview
@Composable
fun GuardianProfileActionsCardPreview() {
    Hospital_AutomationTheme {
        Surface {
            GuardianProfileActionsCard(
                modifier = Modifier.padding(MaterialTheme.spacing.medium16),
                onChildrenItemClicked = {}
            )
        }
    }
}