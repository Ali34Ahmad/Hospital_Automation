package com.example.ui_components.components.topbars

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui_components.R
import com.example.constants.icons.AppIcons
import com.example.model.ActionIcon
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.components.network_image.NetworkImage
import com.example.ui_components.components.network_image.NetworkImageError
import com.example.ui_components.components.network_image.NetworkImageLoader

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HospitalAutomationTopBar(
    title: String,
    onNavigationIconClick: () -> Unit ,
    modifier: Modifier = Modifier,
    @DrawableRes navigationIcon: Int? = null,
    imageUrl: String? = null,
    @DrawableRes imagePlaceholder: Int = AppIcons.Outlined.child,
    actionIcons: List<ActionIcon> = emptyList()
    ) {
    TopAppBar(
        modifier = modifier,
        title = {
            if(imageUrl.isNullOrBlank()){
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }else{
                Row(
                    modifier = Modifier.fillMaxWidth(1f),
                    horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium16),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    NetworkImage(
                            model = imageUrl,
                        modifier = Modifier
                            .size(MaterialTheme.sizing.medium56)
                            .padding(MaterialTheme.spacing.small8)
                            .clip(CircleShape)
                        ,
                        contentScale = ContentScale.Crop,
                            loading = {
                                NetworkImageLoader(
                                    modifier=Modifier
                                        .clip(CircleShape)
                                        .padding(MaterialTheme.sizing.small16)
                                        .size(MaterialTheme.sizing.medium40)
                                )
                            },
                            errorCompose = {
                                NetworkImageError()
                            },
                    )

                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleMedium,
                    )
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
            actionIcons.forEach { action->
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
            imageUrl ="example"
        )
    }
}
@DarkAndLightModePreview
@Composable
fun HospitalAutomationTopBarActionsPreview() {
    Hospital_AutomationTheme {
        HospitalAutomationTopBar(
            title ="Mail",
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
