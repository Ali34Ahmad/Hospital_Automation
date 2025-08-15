package com.example.ui_components.components.topbars.custom

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.constants.icons.AppIcons
import com.example.model.ActionIcon
import com.example.model.enums.TopBarState
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui_components.R
import com.example.ui_components.components.topbars.HospitalAutomationTopBar
import com.example.ui_components.components.topbars.HospitalAutomationTopBarWithSearchBar

@Composable
fun DoctorSearchTopBar(
    state: TopBarState,
    onToggleState: ()-> Unit,
    title: String?,
    searchQuery: String,
    onQueryChanged: (String)-> Unit,
    onClearQuery: ()-> Unit,
    onMenuIconClick: ()-> Unit,
    modifier: Modifier = Modifier,
    @DrawableRes menuIcon: Int = AppIcons.Outlined.menu,
    @DrawableRes searchIcon: Int = AppIcons.Outlined.search
) {
    if(title == null){
        HospitalAutomationTopBarWithSearchBar(
            modifier = modifier,
            query = searchQuery,
            onQueryChange = onQueryChanged,
            onTrailingIconClick = onClearQuery,
            placeholderText = R.string.doctor_name,
            onNavigationIconCLick = onMenuIconClick,
            navigationIcon = menuIcon,
        )
    }else{
        AnimatedContent(
            targetState = state,
        ) {state->
            when(state){
                TopBarState.DEFAULT -> HospitalAutomationTopBar(
                    title = title,
                    onNavigationIconClick = onMenuIconClick,
                    modifier = modifier,
                    navigationIcon = menuIcon,
                    imageUrl = null,
                    actionIcons = listOf(
                        ActionIcon(
                            icon = searchIcon,
                            onCLick = onToggleState
                        )
                    ),
                )
                TopBarState.SEARCH -> HospitalAutomationTopBarWithSearchBar(
                    modifier = modifier,
                    query = searchQuery,
                    onQueryChange = onQueryChanged,
                    onTrailingIconClick = onClearQuery,
                    placeholderText = R.string.doctor_name,
                    onNavigationIconCLick = onMenuIconClick,
                    navigationIcon = menuIcon,
                )
            }
        }
    }
}

@Preview
@Composable
fun DoctorSearchTopBarPreview() {
    Hospital_AutomationTheme {
        var state by remember{ mutableStateOf(TopBarState.DEFAULT) }
        DoctorSearchTopBar(
            state = state,
            onToggleState = {
                state = if(state == TopBarState.DEFAULT) TopBarState.SEARCH
                else TopBarState.DEFAULT
            },
            title = null,
            searchQuery = "",
            onQueryChanged ={

            },
            onClearQuery = {

            },
            onMenuIconClick = {

            },
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Preview
@Composable
fun DoctorSearchByClinicTopBarPreview() {
    Hospital_AutomationTheme {
        var state by remember{ mutableStateOf(TopBarState.DEFAULT) }
        var query by remember { mutableStateOf("") }
        DoctorSearchTopBar(
            state = state,
            onToggleState = {
                state = if(state == TopBarState.DEFAULT) TopBarState.SEARCH
                else TopBarState.DEFAULT
            },
            title = "Department of Digestive",
            searchQuery = query,
            onQueryChanged ={
                query = it
            },
            onClearQuery = {
                query = ""
            },
            onMenuIconClick = {

            },
            modifier = Modifier.fillMaxWidth(),
        )
    }
}