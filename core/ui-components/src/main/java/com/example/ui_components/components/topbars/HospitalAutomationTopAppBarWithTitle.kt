package com.example.ui_components.components.topbars

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.ui.theme.Hospital_AutomationTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HospitalAutomationTopBarWithTitle(
    title: String,
    modifier: Modifier = Modifier,
    
) {
    Box(
        modifier = modifier,
    ){
        TopAppBar(
            title = {
                Text(text = title)
            },
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun HospitalAutomationTopBarWithTitlePreview() {
    Hospital_AutomationTheme {
        HospitalAutomationTopBarWithTitle(
            modifier = Modifier.fillMaxWidth(),
            title = "Title",
        )
    }
}