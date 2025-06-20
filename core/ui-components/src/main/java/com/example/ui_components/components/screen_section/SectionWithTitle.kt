package com.example.ui_components.components.screen_section

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.ui.theme.spacing

@Composable
fun SectionWithTitle(
    title: String,
    modifier: Modifier = Modifier,
    titleAreaPadding: PaddingValues = PaddingValues(MaterialTheme.spacing.default),
    trailingIcon: @Composable () -> Unit = {},
    content: @Composable () -> Unit,
) {
    Column(
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier.padding(titleAreaPadding),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.weight(1f),
            )
            trailingIcon()
        }
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small8))
        content()
    }
}