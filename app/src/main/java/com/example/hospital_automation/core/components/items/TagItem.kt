package com.example.hospital_automation.core.components.items

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.example.hospital_automation.R
import com.example.hospital_automation.ui.helper.DarkAndLightModePreview
import com.example.hospital_automation.ui.theme.Hospital_AutomationTheme
import com.example.hospital_automation.ui.theme.additionalColorScheme
import com.example.hospital_automation.ui.theme.additionalTypography
import com.example.hospital_automation.ui.theme.spacing

@Composable
fun TagItem(
    @StringRes title: Int,
    description: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier
        .clip(MaterialTheme.shapes.extraSmall)
        .background(
            color = MaterialTheme.colorScheme.primaryContainer,
        )
        .clickable { onClick() }
        .padding(MaterialTheme.spacing.small8)
    ) {
        Text(
            text = stringResource(title),
            style = MaterialTheme.additionalTypography.labelSmallest,
            color = MaterialTheme.additionalColorScheme.onPrimaryContainerBlueLight,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall4))
        Text(
            text = description,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

    }
}

@DarkAndLightModePreview
@Composable
fun TagItemPreview() {
    Hospital_AutomationTheme {
        Surface {
            TagItem(
                title = R.string.guardians,
                description = "3 Guardians",
                onClick = {},
            )
        }
    }
}