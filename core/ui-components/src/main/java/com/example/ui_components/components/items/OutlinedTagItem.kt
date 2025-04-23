package com.example.ui_components.components.items

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.sizing

@Composable
fun OutlinedTagItem(
    text: String,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        modifier = modifier,
        shape = MaterialTheme.shapes.extraLarge,
        colors = CardDefaults.outlinedCardColors(
            containerColor = Color.Transparent,
        ),
        border = BorderStroke(
            width = MaterialTheme.sizing.small1,
            color = MaterialTheme.colorScheme.primary
        )
    ){
        Text(
            text,
            modifier = Modifier.padding(
                vertical = MaterialTheme.sizing.small8,
                horizontal = MaterialTheme.sizing.small16,
            ),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }

}

@Preview()
@Composable
fun Preview() {
    Hospital_AutomationTheme {
        OutlinedTagItem(
            text = "Adenoid surgery"
        )
    }
}