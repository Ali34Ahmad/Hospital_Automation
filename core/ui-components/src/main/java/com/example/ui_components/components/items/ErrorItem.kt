package com.example.ui_components.components.items

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.additionalColorScheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.constants.icons.AppIcons
import com.example.ui_components.R


@Composable
fun ErrorItem(
    @StringRes title: Int,
    @StringRes subtitle: Int,
    @DrawableRes icon: Int,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.additionalColorScheme.surfaceError,
            contentColor = MaterialTheme.additionalColorScheme.onSurfaceError
        ),
        shape = RoundedCornerShape(MaterialTheme.sizing.small12)
    ) {
        Column(
            modifier = modifier.padding(
            MaterialTheme.spacing.medium16
        ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = null,
                modifier = Modifier.size(MaterialTheme.sizing.small24)
            )
            Spacer(Modifier.height(MaterialTheme.spacing.small8))
            Text(
                text = stringResource(title),
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(MaterialTheme.spacing.extraSmall4))
            Text(
                text = stringResource(subtitle),
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center
            )
        }
    }

}

@DarkAndLightModePreview
@Composable
fun ErrorItemPreview() {
    Hospital_AutomationTheme {
        Surface(
            color = MaterialTheme.colorScheme.surface
        ) {
            ErrorItem(
                modifier = Modifier.padding(MaterialTheme.spacing.medium16),
                title = R.string.no_internet_connection,
                subtitle = R.string.no_internet_connection_subtitle,
                icon = AppIcons.Outlined.wifi_off
            )
        }
    }
}