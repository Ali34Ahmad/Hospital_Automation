package com.example.ui_components.components.card

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import com.example.constants.icons.AppIcons
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.items.DetailsItem
import com.example.ui_components.components.items.OutlinedTagItem


@Composable
fun AppointmentCard(
    imageUrl : String?,
    name: String,
    tag: String,
    date: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    @StringRes dateTitle: Int = R.string.date_and_time,
    innerPadding: PaddingValues = PaddingValues(MaterialTheme.spacing.medium16),
    @DrawableRes icon: Int = AppIcons.Outlined.calender,
    @DrawableRes mainIcon: Int = AppIcons.Outlined.child,
) {
    Column(
        modifier = modifier
            .clickable(onClick=onClick)
            .background(
                MaterialTheme.colorScheme.background,
                shape = MaterialTheme.shapes.medium
            )
            .padding(
                innerPadding
            ),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.large24),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium16),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if(LocalInspectionMode.current || imageUrl == null){
                Icon(
                    painter = painterResource(mainIcon),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .background(
                            MaterialTheme.colorScheme.primaryContainer,
                            CircleShape
                        )
                        .padding(
                            MaterialTheme.spacing.extraSmall4
                        )
                        .size(MaterialTheme.spacing.large32)
                )
            }
            else{
                AsyncImage(
                    model = imageUrl,
                    contentDescription = null,
                    modifier = Modifier.size(
                        MaterialTheme.sizing.medium40
                    )
                        .clip(
                            CircleShape
                        ),
                    contentScale = ContentScale.Crop
                )
            }
            Text(
                text = name,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(1f)
            )
            OutlinedTagItem(
                text = tag,
            )
        }
        DetailsItem(
            iconRes = icon,
            title = stringResource(dateTitle),
            description = date,
            contentPadding = PaddingValues(MaterialTheme.spacing.default)
        )
    }
}

@Preview
@Composable
fun Preview() {
    Hospital_AutomationTheme {
        AppointmentCard(
            modifier = Modifier.fillMaxWidth(),
            imageUrl = null,
            name = "Ali Mansoura",
            tag = "Adenoid surgery",
            date = "25 Dec - 10:00 AM",
            onClick = {
            }
        )
    }
}