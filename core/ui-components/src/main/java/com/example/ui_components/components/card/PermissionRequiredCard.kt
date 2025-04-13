package com.example.ui_components.components.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.constants.icons.AppIcons
import com.example.hospital_automation.core.components.card.IllustrationCard
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.sizing
import com.example.ui_components.R
import com.example.ui_components.components.icon.SystemStateIcon

@Composable
fun PermissionRequiredCard(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
) {
    IllustrationCard(
        title = title,
        description = description,
        modifier = modifier,
        illustration = {
            Image(
                painter = painterResource(R.drawable.ill_permission),
                contentDescription = null,
                modifier = Modifier.size(MaterialTheme.sizing.extraLarge124),
            )
        }
    )
}

@DarkAndLightModePreview
@Composable
fun PermissionRequiredCardPreview() {
    Hospital_AutomationTheme {
        Surface {
            PermissionRequiredCard(
                title = stringResource(R.string.permission_required),
                description = stringResource(R.string.permission_required_description),
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}