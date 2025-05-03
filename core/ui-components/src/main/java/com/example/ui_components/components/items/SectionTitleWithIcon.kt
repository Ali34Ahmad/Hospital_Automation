package com.example.ui_components.components.items

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.constants.icons.AppIcons
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui_components.R
import com.example.ui_components.components.icon.IconWithBackground

@Composable
fun SectionTitleWithIcon(
    title: String,
    @DrawableRes iconRes: Int,
    onIconClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.weight(1f),
        )
        IconButton(
            onClick = onIconClick
        ) {
            IconWithBackground(
                iconRes = iconRes,
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun SectionTitleWithIconPreview() {
    Hospital_AutomationTheme {
        Surface {
            SectionTitleWithIcon(
                title = stringResource(R.string.guardians),
                onIconClick = {},
                iconRes = AppIcons.Outlined.add,
            )
        }
    }
}