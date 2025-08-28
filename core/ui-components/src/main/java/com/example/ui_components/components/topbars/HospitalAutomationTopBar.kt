package com.example.ui_components.components.topbars

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.constants.icons.AppIcons
import com.example.ext.shimmerEffect
import com.example.model.ActionIcon
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.components.items.FailedImage
import com.example.ui_components.components.network_image.NetworkImage
import com.example.ui_components.components.network_image.NetworkImageError
import com.example.ui_components.components.network_image.NetworkImageLoader
import com.example.ui_components.components.network_image.SmallNetworkImageError

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HospitalAutomationTopBar(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String? = null,
    onNavigationIconClick: () -> Unit,
    @DrawableRes navigationIcon: Int? = null,
    imageUrl: String? = null,
    @DrawableRes imagePlaceholder: Int = AppIcons.Outlined.child,
    showImagePlaceHolder: Boolean = false,
    actionIcons: List<ActionIcon> = emptyList(),
    hasTrailingContent: Boolean = false,
    trailingContent: @Composable () -> Unit = {},
    onTitleClick: () -> Unit = {},
) {
    TopAppBar(
        modifier = modifier,
        title = {
            //Simple Text
            if (imageUrl.isNullOrBlank() && !showImagePlaceHolder) {
                Text(
                    modifier = Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onTitleClick
                    ),
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            } else {
                //Image and Text
                Row(
                    modifier = Modifier.fillMaxWidth(1f)
                        .clickable{
                            onTitleClick()
                        },
                    horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium16),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    if (showImagePlaceHolder) {
                        Icon(
                            painterResource(imagePlaceholder),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(MaterialTheme.spacing.small8)
                                .size(MaterialTheme.sizing.medium44)
                                .background(
                                    MaterialTheme.colorScheme.primaryContainer,
                                    shape = CircleShape
                                )
                                .padding(MaterialTheme.spacing.extraSmall4),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    } else {
                        NetworkImage(
                            model = imageUrl,
                            modifier = Modifier
                                .padding(MaterialTheme.spacing.small8)
                                .size(MaterialTheme.sizing.medium44)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop,
                            loading = {
                                NetworkImageLoader(
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .padding(MaterialTheme.sizing.small16)
                                        .size(MaterialTheme.sizing.medium40)
                                )
                            },
                            errorCompose = {
                                SmallNetworkImageError()
                            },
                        )
                    }
                    Column(
                        verticalArrangement = Arrangement.spacedBy(
                            MaterialTheme.spacing.extraSmall4
                        )
                    ) {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.titleMedium,
                        )

                        subTitle?.let {
                            Text(
                                text = subTitle,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        },
        navigationIcon = {
            navigationIcon?.let {
                IconButton(
                    onClick = onNavigationIconClick
                ) {
                    Icon(
                        painter = painterResource(it),
                        contentDescription = null
                    )
                }
            }

        },
        actions = {
            if(!hasTrailingContent) {
                actionIcons.forEach { action ->
                    IconButton(
                        onClick = action.onCLick
                    ) {
                        Icon(
                            painter = painterResource(action.icon),
                            contentDescription = null,
                        )
                    }
                }
            }
            else{
                trailingContent()
            }
        }
    )
}

@DarkAndLightModePreview
@Composable
fun HospitalAutomationTopBarPreview() {
    Hospital_AutomationTheme {
        HospitalAutomationTopBar(
            title = "Mail",
            navigationIcon = AppIcons.Outlined.menu,
            onNavigationIconClick = {},
            imageUrl = null
        )
    }
}

@DarkAndLightModePreview
@Composable
fun HospitalAutomationTopBarWithImagePreview() {
    Hospital_AutomationTheme {
        HospitalAutomationTopBar(
            title = "Mail",
            navigationIcon = AppIcons.Outlined.menu,
            onNavigationIconClick = {},
            imageUrl = "example"
        )
    }
}

@DarkAndLightModePreview
@Composable
fun HospitalAutomationTopBarWithImageAndSubtitlePreview() {
    Hospital_AutomationTheme {
        HospitalAutomationTopBar(
            title = "Mail",
            subTitle = "Mail",
            navigationIcon = AppIcons.Outlined.menu,
            onNavigationIconClick = {},
            imageUrl = "example"
        )
    }
}

@DarkAndLightModePreview
@Composable
fun HospitalAutomationTopBarActionsPreview() {
    Hospital_AutomationTheme {
        HospitalAutomationTopBar(
            title = "Mail",
            navigationIcon = AppIcons.Outlined.menu,
            onNavigationIconClick = {},
            imageUrl = null,
            actionIcons = listOf(
                ActionIcon(
                    icon = AppIcons.Outlined.calender,
                    onCLick = {}
                ),
                ActionIcon(
                    icon = AppIcons.Outlined.search,
                    onCLick = {}
                )
            )
        )
    }
}

@DarkAndLightModePreview
@Composable
fun HospitalAutomationTopBarPlaceholderActionsPreview() {
    Hospital_AutomationTheme {
        HospitalAutomationTopBar(
            title = "Mail",
            navigationIcon = AppIcons.Outlined.menu,
            onNavigationIconClick = {},
            imageUrl = null,
            showImagePlaceHolder = true
        )
    }
}

