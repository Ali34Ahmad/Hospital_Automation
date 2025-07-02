package com.example.ui_components.components.dialog

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.example.constants.icons.AppIcons
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.R

@Composable
fun DialogWithDescription(
    onDismissRequest: ()-> Unit,
    onActionClick: ()-> Unit,
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
    buttonText: String = stringResource(R.string.done),
    @DrawableRes icon: Int = AppIcons.Outlined.bulb,
    mainColor: Color = MaterialTheme.colorScheme.primary
) {
    Dialog(
        onDismissRequest = onDismissRequest,
    ) {
        Card(
            modifier = modifier,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceContainerLow
            ),
            shape = RoundedCornerShape(MaterialTheme.sizing.small24)
        ) {
            Column(
                verticalArrangement = Arrangement.Top
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(MaterialTheme.sizing.small12)
                        .background(
                            mainColor
                        )
                )
                Spacer(Modifier.height(MaterialTheme.spacing.medium16))
                Row(
                    modifier = Modifier.padding(horizontal = MaterialTheme.spacing.large24),
                    horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small12),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.size(MaterialTheme.spacing.large32),
                        painter = painterResource(icon),
                        contentDescription = null,
                        tint = mainColor
                    )
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                Spacer(Modifier.height(MaterialTheme.spacing.small8))
                Text(
                    modifier = Modifier.padding(horizontal = MaterialTheme.spacing.large24),
                    text = subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                TextButton(
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(MaterialTheme.spacing.medium16),
                    onClick = onActionClick
                ) {
                    Text(
                        text = buttonText,
                        style = MaterialTheme.typography.labelLarge,
                        color = mainColor
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun DialogWithDescriptionPreview() {
    Hospital_AutomationTheme {
        DialogWithDescription(
            onDismissRequest = {},
            onActionClick = {},
            title = "CT Scanner",
            subtitle = "It is a very powerful scanner for medical \n" +
                    "purposes, you can use it safely without any danger.",
        )
    }
}