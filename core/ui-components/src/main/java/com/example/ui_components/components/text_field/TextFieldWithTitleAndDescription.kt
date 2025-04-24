package com.example.ui_components.components.text_field

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.spacing
import com.example.ui_components.R

@Composable
fun TextFieldWithTitleAndDescription(
    @StringRes title: Int,
    @StringRes description: Int,
    text: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(
                MaterialTheme.shapes.small
            )
            .background(MaterialTheme.colorScheme.background)
            .padding(
                vertical = MaterialTheme.spacing.large24,
                horizontal = MaterialTheme.spacing.medium16
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(title),
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(Modifier.height(MaterialTheme.spacing.small8))

        Text(
            text = stringResource(description),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(MaterialTheme.spacing.large32))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(1f),
            value = text,
            onValueChange = onTextChange,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainerLowest.copy(alpha = 0.8f),
                focusedBorderColor = MaterialTheme.colorScheme.outlineVariant,
                unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.8f)
            ),
            maxLines = 2,
            shape = MaterialTheme.shapes.medium
        )
    }
}

@Preview()
@Composable
fun TextFieldWithTitleAndDescriptionPreview() {
    Hospital_AutomationTheme {
        var text by remember { mutableStateOf("This patient is suffering from long-last back pain.") }

        TextFieldWithTitleAndDescription(
            title = R.string.add_medical_dignosis,
            description = R.string.add_medical_dignosis_description,
            text = text,
            onTextChange = {
                text = it
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}