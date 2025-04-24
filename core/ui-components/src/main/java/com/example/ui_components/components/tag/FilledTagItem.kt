package com.example.ui_components.components.tag

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.spacing

@Composable
fun FilledTagItem(
    text:String,
    textColor:Color,
    backgroundColor:Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(
                shape = MaterialTheme.shapes.extraLarge
            )
            .background(backgroundColor)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = textColor,
            modifier = Modifier.padding(
                horizontal = MaterialTheme.spacing.medium16,
                vertical = MaterialTheme.spacing.small8,
            )
        )
    }
}

@DarkAndLightModePreview
@Composable
fun FilledTagItemPreview() {
    Hospital_AutomationTheme {
        Surface {
            FilledTagItem(
                text = "Request",
                textColor = MaterialTheme.colorScheme.onPrimaryContainer,
                backgroundColor =MaterialTheme.colorScheme.primaryContainer,
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun FilledTagItemErrorPreview() {
    Hospital_AutomationTheme {
        Surface {
            FilledTagItem(
                text = "Rejection",
                textColor = MaterialTheme.colorScheme.onErrorContainer,
                backgroundColor =MaterialTheme.colorScheme.errorContainer,
            )
        }
    }
}

