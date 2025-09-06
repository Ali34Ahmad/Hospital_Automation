package com.example.ui_components.components.items

import androidx.annotation.StringRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import com.example.ui_components.R

@Composable
fun NoItemsText(
    modifier: Modifier = Modifier,
    @StringRes text: Int = R.string.there_are_no_items,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    color: Color =  MaterialTheme.colorScheme.outline,
) {
    Text(
        text = stringResource(text),
        modifier = modifier,
        style = textStyle,
        color = color
    )
}