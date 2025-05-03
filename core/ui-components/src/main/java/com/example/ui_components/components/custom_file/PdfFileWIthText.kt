package com.example.ui_components.components.custom_file

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.R

@Composable
fun PdfFileWithText(
    text: String = stringResource(R.string.pdf),
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        PdfFileRectangle(
            modifier = Modifier
                .fillMaxSize()
        )
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.background,
            maxLines = 1,
            overflow = TextOverflow.Clip,
        )
    }
}

@DarkAndLightModePreview
@Composable
fun PdfFileWithTextPreview() {
    Hospital_AutomationTheme {
        Surface {
            PdfFileWithText(
                modifier = Modifier
                    .padding(MaterialTheme.spacing.medium16)
                    .height(MaterialTheme.sizing.medium40)
                    .width(MaterialTheme.sizing.medium32)
            )
        }
    }
}