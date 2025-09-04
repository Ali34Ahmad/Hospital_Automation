package com.example.ui_components.components.list_items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.additionalColorScheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing

@Composable
fun SimpleCheckBoxItem(
    title: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean=true,
    textStyle: TextStyle = MaterialTheme.typography.bodyLarge,
    paddingValues: PaddingValues = PaddingValues(
        vertical = MaterialTheme.spacing.medium16,
        horizontal = MaterialTheme.spacing.small12,
    ),
    enabledTextColor: Color = MaterialTheme.colorScheme.onBackground,
    disabledTextColor: Color = MaterialTheme.additionalColorScheme.onBackgroundVariant.copy(alpha = 0.7f)
) {
    val textColor = if(enabled) enabledTextColor else disabledTextColor
    Row(
        modifier = modifier
            .clickable(
                enabled = enabled,
                onClick = {
                    if(enabled){
                        onCheckedChange(!isChecked)
                    }
                },
            )
            .padding(paddingValues),
         horizontalArrangement = Arrangement.SpaceBetween,
         verticalAlignment = Alignment.CenterVertically,
     ) {
         Text(
             text  = title,
             style = textStyle,
             color = textColor
         )
        Checkbox(
            checked = isChecked,
            onCheckedChange = onCheckedChange,
            modifier = Modifier.size(MaterialTheme.sizing.small24),
            enabled = enabled,
        )
     }
}

@DarkAndLightModePreview
@Composable
fun SimpleCheckBoxItemPreview(){
    Hospital_AutomationTheme {
        var isChecked by remember{ mutableStateOf(false) }
        Surface {
            SimpleCheckBoxItem(
                title = "Sunday",
                isChecked = isChecked,
                onCheckedChange = {
                    isChecked = it
                },
                enabled = false,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}