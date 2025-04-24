package com.example.ui_components.components.tag

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing

@Composable
fun OutlinedTagItem(
    text:String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .border(
                width = MaterialTheme.sizing.extraSmall1,
                color = MaterialTheme.colorScheme.primary,
                shape = MaterialTheme.shapes.extraLarge
            )
            .clip(
                shape = MaterialTheme.shapes.extraLarge
            )
            .clickable {
                onClick()
            }
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(
                horizontal = MaterialTheme.spacing.medium16,
                vertical = MaterialTheme.spacing.small8,
            )
        )
    }
}

@DarkAndLightModePreview
@Composable
fun OutlinedTagItemPreview() {
    Hospital_AutomationTheme {
        Surface {
            OutlinedTagItem(
                text = "X-ray Machine",
                onClick = {},
            )
        }
    }
}