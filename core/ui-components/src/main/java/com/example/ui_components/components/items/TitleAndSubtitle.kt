package com.example.ui_components.components.items

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.example.ui.theme.spacing

@Composable
fun TitleAndSubtitle(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
    titleStyle: TextStyle = MaterialTheme.typography.titleSmall,
    subtitleStyle: TextStyle = MaterialTheme.typography.bodySmall,
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(MaterialTheme.spacing.small8),
    horizontalAlignment: Alignment.Horizontal = Alignment.Start
) {
    Column(
        modifier = modifier,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment,
    ) {
        Text(
            title,
            style = titleStyle,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            subtitle,
            style = subtitleStyle,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}