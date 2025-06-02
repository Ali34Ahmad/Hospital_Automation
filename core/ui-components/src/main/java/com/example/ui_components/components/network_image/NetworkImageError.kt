package com.example.ui_components.components.network_image

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import com.example.constants.icons.AppIcons
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.R

@Composable
fun NetworkImageError(
    textStyle: TextStyle=MaterialTheme.typography.titleLarge
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(MaterialTheme.sizing.profileImageHeight)
            .background(MaterialTheme.colorScheme.outlineVariant),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(AppIcons.Outlined.error),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(0.2f),
            tint = MaterialTheme.colorScheme.error
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium16))
        Text(
            text = stringResource(R.string.failed_to_load_image),
            style = textStyle,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}