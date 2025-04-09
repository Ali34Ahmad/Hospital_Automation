package com.example.ui_components.components.texts

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.ui_components.theme.Hospital_AutomationTheme

@Composable
fun TitleLarge(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text,
        style = MaterialTheme.typography.titleLarge,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun TitleLargePreview() {
    Hospital_AutomationTheme{
        TitleLarge("Sign Up")
    }
}