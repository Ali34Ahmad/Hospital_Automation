package com.example.ui_components.components.screen_section

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ui.theme.spacing

@Composable
fun SectionWithTitle(
    title: String,
    modifier: Modifier = Modifier,
    textPadding: PaddingValues = PaddingValues(MaterialTheme.spacing.default),
    content: @Composable () -> Unit,
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(textPadding)
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small8))
        content()
    }
}