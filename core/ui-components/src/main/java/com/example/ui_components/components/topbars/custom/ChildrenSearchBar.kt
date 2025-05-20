package com.example.ui_components.components.topbars.custom

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.ui_components.R
import com.example.ui_components.components.topbars.HospitalAutomationTopBarWithSearchBar
import com.example.ui_components.icons.HospitalAutomationIcons

@Composable
fun ChildrenSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onTrailingIconClick: () -> Unit,
    onSearch: (String) -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    HospitalAutomationTopBarWithSearchBar(
        query = query,
        onQueryChange = onQueryChange,
        onTrailingIconClick = onTrailingIconClick,
        onSearch = onSearch,
        placeholderText = R.string.search_for_children,
        trailingIcon = HospitalAutomationIcons.close,
        modifier = modifier,
        onNavigationIconCLick = onNavigateUp,
    )
}