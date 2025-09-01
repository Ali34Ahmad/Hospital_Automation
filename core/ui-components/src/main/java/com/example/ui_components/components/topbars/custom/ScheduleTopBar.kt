package com.example.ui_components.components.topbars.custom

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
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
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui_components.components.topbars.HospitalAutomationTopBar
import com.example.ui_components.components.topbars.HospitalAutomationTopBarWithSearchBar

@Composable
fun ScheduleTopBar(
    isPermissionsGranted: Boolean,
    isSearchBarVisible: Boolean,
    searchQuery: String,
    imageUrl: String?,
    name: String?,
    speciality: String?,
    showChildPlaceholder: Boolean,
    onTitleClick: () -> Unit,
    onQueryChanged: (String)-> Unit,
    onQueryDeleted: () -> Unit,
    onSearch: ()-> Unit,
    onBackButtonClick: () -> Unit,
    onDatePickerIconClick: ()-> Unit,
    onDrawerOpened: () -> Unit,
    onNavigateUp: () -> Unit,
    canNavigateUp: Boolean,
    modifier: Modifier = Modifier,
    @DrawableRes menuIcon: Int =  AppIcons.Outlined.menu,
    @DrawableRes navigationIcon: Int =  AppIcons.Outlined.arrowBack,
    @StringRes placeholderText: Int = R.string.appointment_type
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
                    title = name?:stringResource(R.string.schedule),
                    subTitle = speciality,
                    imageUrl = imageUrl,
                    showImagePlaceHolder = showChildPlaceholder,
                    onNavigationIconClick =if(canNavigateUp) onNavigateUp else onDrawerOpened,
                    onTitleClick = onTitleClick,
                    modifier = modifier,
                    navigationIcon = if(canNavigateUp) navigationIcon else menuIcon,
                    actionIcons = if (isPermissionsGranted) actions else emptyList()
                )
            }
            true->{
                HospitalAutomationTopBarWithSearchBar(
                    query = searchQuery,
                    onQueryChange = onQueryChanged,
                    onTrailingIconClick = onQueryDeleted,
                    placeholderText = placeholderText,
                    onNavigationIconCLick = onBackButtonClick,
                    modifier = modifier,
                )
            }
        }
    }
}

@DarkAndLightModePreview
@Composable
fun ScheduleTopBarPreview() {
    Hospital_AutomationTheme {
        var isSearchVisible by remember { mutableStateOf(false) }
        var searchQuery by remember { mutableStateOf("") }
        ScheduleTopBar(
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
            onBackButtonClick = { isSearchVisible = false },
            isPermissionsGranted = true,
            imageUrl = null,
            name = null,
            speciality = null,
            showChildPlaceholder = false,
            onTitleClick = {},
            onNavigateUp = {},
            canNavigateUp = false,
        )
    }
}


@DarkAndLightModePreview
@Composable
fun DoctorScheduleTopBarPreview() {
    Hospital_AutomationTheme {
        var isSearchVisible by remember { mutableStateOf(false) }
        var searchQuery by remember { mutableStateOf("") }
        ScheduleTopBar(
            name = "Ali Mansoura",
            speciality = "Dentist",
            imageUrl = "fake url",
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
            isPermissionsGranted = true,
            showChildPlaceholder = false,
            onTitleClick = {},
            onNavigateUp = {},
            canNavigateUp = true

        )
    }
}



@DarkAndLightModePreview
@Composable
fun UserScheduleTopBarPreview() {
    Hospital_AutomationTheme {
        var isSearchVisible by remember { mutableStateOf(false) }
        var searchQuery by remember { mutableStateOf("") }
        ScheduleTopBar(
            name = "Ali Mansoura",
            imageUrl = "fake url",
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
            onBackButtonClick = { isSearchVisible = false },
            isPermissionsGranted = true,
            speciality = null,
            showChildPlaceholder = false,
            onTitleClick = {},
            onNavigateUp = {},
            canNavigateUp = false,
        )
    }
}



@DarkAndLightModePreview
@Composable
fun ChildScheduleTopBarPreview() {
    Hospital_AutomationTheme {
        var isSearchVisible by remember { mutableStateOf(false) }
        var searchQuery by remember { mutableStateOf("") }
        ScheduleTopBar(
            name = "Ali Mansoura",
            showChildPlaceholder = true,
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
            onBackButtonClick = { isSearchVisible = false },
            isPermissionsGranted = true,
            imageUrl = null,
            speciality = null,
            onTitleClick = {},
            onNavigateUp = {},
            canNavigateUp = false,
        )
    }
}