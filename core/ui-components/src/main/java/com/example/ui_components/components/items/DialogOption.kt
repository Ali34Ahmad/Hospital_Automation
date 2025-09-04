package com.example.ui_components.components.items

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.example.constants.icons.AppIcons
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.additionalColorScheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing

@Composable
fun DialogOption(
    title: String,
    subTitle: String,
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int = AppIcons.Outlined.arrowForward,
    titleStyle: TextStyle = MaterialTheme.typography.bodyLarge,
    titleColor: Color = MaterialTheme.colorScheme.onBackground,
    subtitleColor: Color = MaterialTheme.additionalColorScheme.onBackgroundVariant,
    subtitleStyle: TextStyle = MaterialTheme.typography.bodyLarge,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        //title and subtitle
        Column(
            verticalArrangement = Arrangement
                .spacedBy(MaterialTheme.spacing.extraSmall4),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = title,
                style = titleStyle,
                color = titleColor
            )
            Text(
                text = subTitle,
                style = subtitleStyle,
                color = subtitleColor
            )
        }

        Spacer(Modifier.weight(1f))

        //icon
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            modifier = Modifier
                .size(MaterialTheme.sizing.small24)
        )
    }
}

@DarkAndLightModePreview
@Composable
fun DialogOptionPreview() {
    Hospital_AutomationTheme { 
        DialogOption(
            title = "Start Time",
            subTitle = "8:00 AM",
        )
    }
}