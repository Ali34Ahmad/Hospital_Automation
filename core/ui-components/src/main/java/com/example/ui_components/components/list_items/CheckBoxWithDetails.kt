package com.example.ui_components.components.list_items

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui_components.theme.Hospital_AutomationTheme
import com.example.ui_components.R

@Composable
fun CheckBoxWithDetails(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    @StringRes title: Int,
    @StringRes subTitle: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clickable{
                onCheckedChange
            },
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.Top,
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange= onCheckedChange,
        )
        Column(
            modifier = Modifier.padding(top = 10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                stringResource(title),
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                stringResource(subTitle),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CheckBoxWithDetailsPreview() {
    Hospital_AutomationTheme {
        var checked by remember{
            mutableStateOf(false)
        }
        CheckBoxWithDetails(
            title = R.string.I_agree,
            subTitle = R.string.medical_data_purpose,
            checked = checked,
            onCheckedChange = {
                checked = it
            }
        )
    }
}

/*

 */