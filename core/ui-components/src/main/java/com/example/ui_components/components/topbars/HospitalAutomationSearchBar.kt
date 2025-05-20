package com.example.ui_components.components.topbars

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui_components.R
import com.example.ui_components.icons.HospitalAutomationIcons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HospitalAutomationSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onTrailingIconClick: () -> Unit,
    onSearch: (String) -> Unit,
    @StringRes placeholderText: Int,
    @DrawableRes trailingIcon: Int?,
    modifier: Modifier = Modifier,
    maxLines: Int = 1,
) {
    TextField(
        modifier = modifier,
        value = query,
        maxLines = maxLines,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearch(query)
            }
        ),
        onValueChange = onQueryChange,
        colors = TextFieldDefaults.colors(
            focusedLeadingIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
            unfocusedLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
            focusedTrailingIconColor= MaterialTheme.colorScheme.onPrimaryContainer,
            unfocusedTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
            focusedContainerColor = Color(0xffF9F9FA),
            unfocusedContainerColor = Color(0xffF9F9FA).copy(alpha = 0.6f),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        placeholder = {
            Text(
                text = stringResource(placeholderText),
                style = MaterialTheme.typography.bodyLarge
            )
        },
        trailingIcon = {
            trailingIcon?.let {
                    IconButton(
                        onClick = onTrailingIconClick
                    ){
                        Icon(
                            painter = painterResource(it),
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
        },
        shape = RoundedCornerShape(100),
        textStyle = MaterialTheme.typography.bodyLarge,
    )
}

@Preview()
@Composable
fun HospitalAutomationSearchBarPreview() {
    Hospital_AutomationTheme {
        var query by remember { mutableStateOf("") }
        HospitalAutomationSearchBar(
            modifier = Modifier.width(340.dp),
            query = query,
            onQueryChange = {
                query = it
            },
            placeholderText = R.string.search_for_guardians,
            trailingIcon = HospitalAutomationIcons.close,
            onTrailingIconClick = {
                query = ""
            },
            onSearch = {}
        )
    }
}