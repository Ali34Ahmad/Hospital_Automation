package com.example.children_search.presentation.preview

import androidx.compose.runtime.Composable
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.children_search.presentation.ChildrenSearchScreen
import com.example.children_search.presentation.ChildrenSearchUiState
import com.example.model.child.ChildData
import com.example.model.enums.FetchingDataState
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import kotlinx.coroutines.flow.flowOf

internal val fakeChildren = flowOf(
    PagingData.from(
        listOf(
            ChildData(
                id = 1,
                firstName = "Ali",
                lastName = "Ali",
                fatherFirstName = "Bassam",
                fatherLastName = "Ali",
                motherFirstName = "Laila",
                motherLastName = "Ale",
                dateOfBirth = "2002-02-01"
            ),
            ChildData(
                id = 2,
                firstName = "Ali",
                lastName = "Ali",
                fatherFirstName = "Bassam",
                fatherLastName = "Ali",
                motherFirstName = "Laila",
                motherLastName = "Ale",
                dateOfBirth = "2002-02-01"
            ),
            ChildData(
                id = 3,
                firstName = "soha",
                lastName = "Ali",
                fatherFirstName = "Bassam",
                fatherLastName = "Ali",
                motherFirstName = "Laila",
                motherLastName = "Ale",
                dateOfBirth = "2002-02-01"
            )
        )
    )
)
@DarkAndLightModePreview
@Composable
fun ChildrenSearchScreenSuccessPreview(){
    Hospital_AutomationTheme {
        ChildrenSearchScreen(
            onNavigateBack = {},
            onQueryDeleted = {},
            onStateUpdated = {},
            onNavigateToChildProfile = {},
            onQueryChanged = {},
            uiState = ChildrenSearchUiState(state = FetchingDataState.Success),
            children = fakeChildren.collectAsLazyPagingItems()
        )
    }
}
@DarkAndLightModePreview
@Composable
fun ChildrenSearchScreenErrorPreview(){
    Hospital_AutomationTheme {
        ChildrenSearchScreen(
            onNavigateBack = {},
            onQueryDeleted = {},
            onStateUpdated = {},
            onNavigateToChildProfile = {},
            onQueryChanged = {},
            uiState = ChildrenSearchUiState(state = FetchingDataState.ERROR),
            children = fakeChildren.collectAsLazyPagingItems()
        )
    }
}

@DarkAndLightModePreview
@Composable
fun ChildrenSearchScreenLoadingPreview(){
    Hospital_AutomationTheme {
        ChildrenSearchScreen(
            onNavigateBack = {},
            onQueryDeleted = {},
            onStateUpdated = {},
            onNavigateToChildProfile = {},
            onQueryChanged = {},
            uiState = ChildrenSearchUiState(state = FetchingDataState.LOADING),
            children = fakeChildren.collectAsLazyPagingItems()
        )
    }
}
