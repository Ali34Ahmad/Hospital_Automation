package com.example.ui_components.components.icon

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.example.ui_components.R
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.sizing

@Composable
fun IconWithBackground(
    @DrawableRes iconRes: Int,
    modifier: Modifier = Modifier,
    @StringRes contentDescription: Int?=null,
    backgroundColor: Color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f),
    iconColor:Color=Color.Black,
) {
    Box(
        modifier = modifier
            .size(MaterialTheme.sizing.small24)
            .clip(CircleShape)
            .background(backgroundColor),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(iconRes),
            contentDescription = stringResource(contentDescription ?: R.string.blank),
            tint = iconColor,
            modifier=Modifier.size(MaterialTheme.sizing.small18),
        )
    }
}

@DarkAndLightModePreview
@Composable
fun IconWithBackgroundPreview(){
    Hospital_AutomationTheme{
        Surface{
            IconWithBackground(
                iconRes = R.drawable.ic_location_outlined,
                contentDescription = null,
                backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                iconColor = MaterialTheme.colorScheme.primary,
            )
        }
    }
}