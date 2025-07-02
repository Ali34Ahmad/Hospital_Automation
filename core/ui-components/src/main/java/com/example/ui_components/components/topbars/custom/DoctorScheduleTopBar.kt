package com.example.ui_components.components.topbars.custom

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.fillMaxWidth
import com.example.ui_components.R
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.constants.icons.AppIcons
import com.example.model.ActionIcon
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui_components.components.topbars.HospitalAutomationTopBar
import com.example.ui_components.components.topbars.HospitalAutomationTopBarWithSearchBar

@Composable
fun DoctorScheduleTopBar(
    isPermissionsGranted: Boolean,
    isSearchBarVisible: Boolean,
    searchQuery: String,
    onQueryChanged: (String)-> Unit,
    onQueryDeleted: () -> Unit,
    onSearch: ()-> Unit,
    onBackButtonClick: () -> Unit,
    onDatePickerIconClick: ()-> Unit,
    onDrawerOpened: () -> Unit,
    modifier: Modifier = Modifier
) {
    val actions = listOf(
        ActionIcon(
            icon = AppIcons.Outlined.calender,
            onCLick = onDatePickerIconClick
        ),
        ActionIcon(
            icon = AppIcons.Outlined.search,
            onCLick = onSearch
        )
    )
    AnimatedContent (isSearchBarVisible) { state->
        when(state){
            false->{
                HospitalAutomationTopBar(
                    title = stringResource(R.string.schedule),
                    onNavigationIconClick = onDrawerOpened,
                    modifier = modifier,
                    navigationIcon = AppIcons.Outlined.menu,
                    actionIcons = if (isPermissionsGranted) actions else emptyList()
                )
            }
            true->{
                HospitalAutomationTopBarWithSearchBar(
                    query = searchQuery,
                    onQueryChange = onQueryChanged,
                    onTrailingIconClick = onQueryDeleted,
                    placeholderText = R.string.appointment_type,
                    onNavigationIconCLick = onBackButtonClick,
                    modifier = modifier,
                )
            }
        }
    }
}

@Preview
@Composable
fun DoctorScheduleTopBarPreview() {
    Hospital_AutomationTheme {
        var isSearchVisible by remember { mutableStateOf(false) }
        var searchQuery by remember { mutableStateOf("") }
        DoctorScheduleTopBar(
            isSearchBarVisible = isSearchVisible,
            searchQuery = searchQuery,
            onQueryChanged = {
                searchQuery = it
            },
            onQueryDeleted = {
                searchQuery = ""
            },
            onSearch = {
                isSearchVisible = true
            },
            onDatePickerIconClick = {},
            onDrawerOpened = {},
            modifier = Modifier.fillMaxWidth(),
            onBackButtonClick = {isSearchVisible = false},
            isPermissionsGranted = true
        )
    }
}