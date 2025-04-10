package com.example.hospital_automation.core.components.icon

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.hospital_automation.R
import com.example.hospital_automation.ui.helper.DarkAndLightModePreview
import com.example.hospital_automation.ui.theme.Hospital_AutomationTheme
import com.example.hospital_automation.ui.theme.sizing
import com.example.hospital_automation.ui.theme.spacing

@Composable
fun SystemStateIcon(
    modifier: Modifier = Modifier,
    @DrawableRes iconRes: Int,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    iconColor: Color = MaterialTheme.colorScheme.onPrimary,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
                .background(color = backgroundColor.copy(alpha = 0.2f))
        )
        Spacer(
            modifier = Modifier
                .fillMaxSize()
                .padding(MaterialTheme.spacing.small12)
                .clip(CircleShape)
                .background(color = backgroundColor.copy(alpha = 0.5f))
        )
        Spacer(
            modifier = Modifier
                .fillMaxSize()
                .padding(MaterialTheme.spacing.large24)
                .clip(CircleShape)
                .background(color = backgroundColor.copy(alpha = 0.8f))
        )
        Icon(painter = painterResource(iconRes), contentDescription = null, tint = iconColor)
    }
}

@DarkAndLightModePreview
@Composable
fun SystemStateIconPreview() {
    Hospital_AutomationTheme {
        Surface {
            SystemStateIcon(
                iconRes = R.drawable.ic_check,
                modifier = Modifier.size(MaterialTheme.sizing.extraLarge124)
            )
        }
    }
}