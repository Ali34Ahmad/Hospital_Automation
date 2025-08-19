package com.example.ui_components.components.network_image

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.constants.icons.AppIcons

@Composable
fun SmallNetworkImageError(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int? = AppIcons.Outlined.error,
    iconModifier: Modifier = Modifier.fillMaxSize(0.8f),
    iconColor: Color = MaterialTheme.colorScheme.error,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        icon?.let {
            Icon(
                imageVector = ImageVector.vectorResource(icon), contentDescription = null,
                tint = iconColor,
                modifier = iconModifier
            )
        }
    }
}