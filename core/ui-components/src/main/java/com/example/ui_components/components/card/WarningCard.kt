package com.example.ui_components.components.card

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.additionalColorScheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.texts.ClickableText
import com.example.constants.icons.AppIcons
import com.example.model.helper.ext.clickableTextRange

@Composable
fun WarningCard(
    text: String,
    @DrawableRes leadingIcon: Int? = AppIcons.Outlined.errorMessage,
    @DrawableRes trailingIcon: Int? = null,
    onTrailingIconClick: (() -> Unit)? = null,
    onTextClick: (() -> Unit)? = null,
    clickableText: String? = null,
    modifier: Modifier = Modifier,
) {
    val totalText = "$text $clickableText"
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.additionalColorScheme.warningContainer,
            contentColor = MaterialTheme.additionalColorScheme.warning
        ),
        border = BorderStroke(
            width = MaterialTheme.sizing.small1,
            color = MaterialTheme.additionalColorScheme.warning,
        ),
        shape = MaterialTheme.shapes.small
    ) {
        Row(
            modifier = Modifier.padding(
                MaterialTheme.spacing.medium16
            ),
            horizontalArrangement = Arrangement.Start,
        ) {
            leadingIcon?.let {
                Icon(
                    painter = painterResource(leadingIcon),
                    contentDescription = null,
                    modifier = Modifier.size(
                        MaterialTheme.sizing.small18
                    )
                )
            }
            Spacer(Modifier.width(MaterialTheme.spacing.extraSmall4))

            if (onTextClick == null) {
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f)
                )
            } else {
                clickableText?.let {
                    ClickableText(
                        text = totalText,
                        clickableTextRange = totalText.clickableTextRange(clickableText),
                        onClick = onTextClick,
                        modifier = Modifier.weight(1f),
                        normalTextColor = MaterialTheme.additionalColorScheme.warning,
                        clickableTextColor = MaterialTheme.additionalColorScheme.warning,
                    )
                }
            }
            trailingIcon?.let {
                Box(
                    contentAlignment = Alignment.Center,
                ) {
                    IconButton(
                        onClick = onTrailingIconClick ?: {},
                        modifier = Modifier.size(
                            MaterialTheme.sizing.small24
                        )
                    ) {
                        Icon(
                            modifier = Modifier.size(
                                MaterialTheme.sizing.small18
                            ),
                            painter = painterResource(trailingIcon),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            }
        }
    }
}

@DarkAndLightModePreview
@Composable
fun WaringCardPreview() {
    Hospital_AutomationTheme {
        Surface {
            WarningCard(
                text = stringResource(R.string.description_not_added),
                leadingIcon = AppIcons.Outlined.errorMessage,
                trailingIcon = AppIcons.Outlined.add,
                onTrailingIconClick = {},
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun WaringCardWithClickableTextPreview() {
    Hospital_AutomationTheme {
        Surface {
            WarningCard(
                text = stringResource(R.string.description_not_added),
                leadingIcon = AppIcons.Outlined.errorMessage,
                trailingIcon = AppIcons.Outlined.add,
                onTrailingIconClick = {},
                modifier = Modifier.fillMaxWidth(),
                onTextClick = {},
                clickableText = stringResource(R.string.learn_more),
            )
        }
    }
}

