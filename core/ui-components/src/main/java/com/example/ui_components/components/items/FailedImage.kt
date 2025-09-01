package com.example.ui_components.components.items

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.constants.icons.AppIcons
import com.example.ui.theme.Hospital_AutomationTheme

@Composable
fun FailedImage(
    modifier: Modifier = Modifier,
    shape: Shape = CircleShape,
    @DrawableRes icon: Int = AppIcons.Outlined.brokenImage,
    containerColor : Color = MaterialTheme.colorScheme.outlineVariant,
    contentColor : Color = MaterialTheme.colorScheme.outline,
) {
    Box(
        modifier = modifier
            .clip(shape)
            .background(containerColor),
        contentAlignment = Alignment.Center
    ){
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            tint = contentColor,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FailedImagePreview() {
    Hospital_AutomationTheme {
        FailedImage()
    }
}