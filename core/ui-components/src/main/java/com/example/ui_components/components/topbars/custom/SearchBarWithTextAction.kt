package com.example.ui_components.components.topbars.custom

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.constants.icons.AppIcons
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui_components.components.topbars.HospitalAutomationTopBarWithSearchBar
import com.example.ui_components.R
@Composable
fun SearchBarWithTextAction(
    query: String,
    onQueryChanged: (String) -> Unit,
    onClearIconClick: () -> Unit,
    onNavigateBack: ()-> Unit,
    @StringRes placeholder: Int,
    modifier: Modifier = Modifier,
    actionText: String,
    onActionClick: ()-> Unit,
    hasNavigationIcon: Boolean
    ) {
    HospitalAutomationTopBarWithSearchBar(
        modifier =modifier,
        query = query,
        onQueryChange = onQueryChanged,
        onTrailingIconClick = onClearIconClick,
        placeholderText = placeholder,
        onNavigationIconCLick = onNavigateBack,
        trailingIcon = AppIcons.Outlined.close,
        navigationIcon = AppIcons.Outlined.arrowBack,
        hasNavigationIcon = hasNavigationIcon
    ){
        TextButton(
            onClick =  onActionClick
        ) {
            Text(
                actionText,
                style = MaterialTheme.typography.labelLarge,
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SearchBarWithActionPreview() {
    Hospital_AutomationTheme {
        SearchBarWithTextAction(
            query = "Ali",
            onQueryChanged ={},
            onClearIconClick = {},
            onNavigateBack = {},
            placeholder = R.string.search_for_medicines,
            modifier = Modifier.fillMaxWidth(),
            actionText = "Finish",
            onActionClick = {},
            hasNavigationIcon = true
        )
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SearchBarWithActionWithoutNavigationPreview() {
    Hospital_AutomationTheme {
        SearchBarWithTextAction(
            query = "Ali",
            onQueryChanged ={},
            onClearIconClick = {},
            onNavigateBack = {},
            placeholder = R.string.search_for_medicines,
            modifier = Modifier.fillMaxWidth(),
            actionText = "Finish",
            onActionClick = {},
            hasNavigationIcon = false
        )
    }
}