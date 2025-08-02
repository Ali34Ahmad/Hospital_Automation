package com.example.ui_components.components.topbars.custom

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.constants.icons.AppIcons
import com.example.model.ActionIcon
import com.example.model.enums.TopBarState
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui_components.R
import com.example.ui_components.components.topbars.HospitalAutomationTopBar
import com.example.ui_components.components.topbars.HospitalAutomationTopBarWithSearchBar

@Composable
fun SearchTopBar(
    onSearchIconClick: ()-> Unit,
    onMenuOpen: ()-> Unit,
    onBackToDefault: () -> Unit,
    onClearIconClick:()-> Unit,
    query: String,
    state: TopBarState,
    onQueryChanged: (String)-> Unit,
    title: String,
    @StringRes placeholder: Int,
    modifier: Modifier = Modifier,
    @DrawableRes defaultLeadingIcon: Int = AppIcons.Outlined.menu,
    @DrawableRes searchLeadingIcon: Int = AppIcons.Outlined.arrowBack,
    @DrawableRes searchTrailingIcon: Int = AppIcons.Outlined.close,
    @DrawableRes searchIcon: Int = AppIcons.Outlined.search
) {
    AnimatedContent(
        state,
        modifier = modifier
    ) { targetState ->
        when (targetState) {
            TopBarState.DEFAULT -> {
                HospitalAutomationTopBar(
                    title = title,
                    modifier = Modifier.fillMaxWidth(),
                    onNavigationIconClick = onMenuOpen,
                    actionIcons = listOf(
                        ActionIcon(
                            icon = searchIcon,
                            onCLick = onSearchIconClick
                        )
                    ),
                    navigationIcon = defaultLeadingIcon
                )
            }
            TopBarState.SEARCH -> {
                HospitalAutomationTopBarWithSearchBar(
                    query = query,
                    onQueryChange = onQueryChanged,
                    onTrailingIconClick = onClearIconClick,
                    placeholderText = placeholder,
                    onNavigationIconCLick = onBackToDefault,
                    trailingIcon = searchTrailingIcon,
                    navigationIcon = searchLeadingIcon,
                )
            }
        }
    }
}

@DarkAndLightModePreview()
@Composable
fun ClinicsTopBarPreview() {
    Hospital_AutomationTheme {
        var query by remember { mutableStateOf("") }
        var state by remember { mutableStateOf(TopBarState.SEARCH) }
        SearchTopBar(
            onSearchIconClick = { state = TopBarState.SEARCH },
            onMenuOpen = { state = TopBarState.DEFAULT },
            onClearIconClick = { query = "" },
            query = query,
            state = state,
            onQueryChanged = { query = it },
            title = stringResource(id = R.string.search_for_clinics),
            placeholder = R.string.search_for_clinics,
            onBackToDefault ={},
        )
    }
}