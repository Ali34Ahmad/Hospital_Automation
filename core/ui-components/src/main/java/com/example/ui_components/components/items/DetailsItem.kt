package com.example.ui_components.components.items

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.example.ext.clickableTextRange
import com.example.ui_components.R
import com.example.ui_components.components.icon.IconWithBackground
import com.example.ui_components.components.texts.ClickableText
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.additionalColorScheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing

@Composable
fun DetailsItem(
    @DrawableRes iconRes: Int,
    modifier: Modifier = Modifier,
    iconBackgroundColor: Color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.4f),
    iconColor: Color = MaterialTheme.colorScheme.primary,
    title: String,
    description: String,
    onClick: (() -> Unit)? = null,
    onDescriptionClick: (() -> Unit)? = null,
    descriptionColor: Color = MaterialTheme.colorScheme.onBackground,
    descriptionClickableTextRange: IntRange = 0..0,
    isMultipleLines: Boolean = false,
    contentPadding: PaddingValues = PaddingValues(
        horizontal = MaterialTheme.spacing.medium16,
        vertical = MaterialTheme.spacing.small12
    )
) {
    val rowModifier = if (onClick != null) {
        modifier
            .clickable { onClick() }
            .padding(contentPadding)
    } else {
        modifier
            .padding(contentPadding)
    }
    val maxDescriptionLines = if (isMultipleLines) {
        Int.MAX_VALUE
    } else {
        1
    }

    Row(modifier = rowModifier) {
        IconWithBackground(
            iconRes = iconRes,
            backgroundColor = iconBackgroundColor,
            iconColor = iconColor,
        )
        Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium16))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.additionalColorScheme.onBackgroundVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall4))
            if (onDescriptionClick == null) {
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = descriptionColor,
                )
            } else {
                ClickableText(
                    text = description,
                    clickableTextRange = descriptionClickableTextRange,
                    onClick = onDescriptionClick,
                    maxLines = maxDescriptionLines,
                    normalTextColor = descriptionColor,
                    clickableTextColor = descriptionColor,
                )
            }
        }
        if (onClick != null) {
            Icon(
                painter = painterResource(R.drawable.ic_chevron_right),
                contentDescription = "",
                tint = MaterialTheme.additionalColorScheme.onBackgroundVariant,
                modifier = Modifier
                    .size(MaterialTheme.sizing.small18)
                    .align(alignment = Alignment.CenterVertically),
            )
        }

    }

}

@DarkAndLightModePreview
@Composable
fun DetailsItemPreview() {
    Hospital_AutomationTheme {
        Surface {
            DetailsItem(
                iconRes = R.drawable.ic_location_outlined,
                iconBackgroundColor = MaterialTheme.colorScheme.secondaryContainer,
                iconColor = MaterialTheme.colorScheme.primary,
                title = stringResource(id = R.string.residential_address),
                description = "Lattakia - Masaya Street",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = MaterialTheme.spacing.medium16,
                        vertical = MaterialTheme.spacing.small12
                    ),
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun ClickableDetailsItemPreview() {
    Hospital_AutomationTheme {
        Surface {
            DetailsItem(
                iconRes = R.drawable.ic_location_outlined,
                iconBackgroundColor = MaterialTheme.colorScheme.secondaryContainer,
                iconColor = MaterialTheme.colorScheme.primary,
                title = stringResource(id = R.string.residential_address),
                description = "Lattakia - Masaya Street",
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = MaterialTheme.spacing.medium16,
                        vertical = MaterialTheme.spacing.small12
                    ),
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun ClickableDescriptionDetailsItemPreview() {
    Hospital_AutomationTheme {
        Surface {
            DetailsItem(
                iconRes = R.drawable.ic_location_outlined,
                iconBackgroundColor = MaterialTheme.colorScheme.secondaryContainer,
                iconColor = MaterialTheme.colorScheme.primary,
                title = stringResource(id = R.string.residential_address),
                description = "Uploaded by Zaid Ahmad",
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = MaterialTheme.spacing.medium16,
                        vertical = MaterialTheme.spacing.small12
                    ),
                descriptionClickableTextRange = "Uploaded by Zaid Ahmad".clickableTextRange("Zaid Ahmad"),
                onDescriptionClick = {},
            )
        }
    }
}

