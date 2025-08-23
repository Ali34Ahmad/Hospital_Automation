package com.example.children_search.presentation.preview

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.children_search.presentation.ChildrenSearchNavigationActions
import com.example.children_search.presentation.ChildrenSearchScreen
import com.example.children_search.presentation.ChildrenSearchUiState
import com.example.model.child.ChildData
import com.example.model.enums.ScreenState
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import kotlinx.coroutines.flow.flowOf
import java.time.LocalDate

internal val mockActions = object : ChildrenSearchNavigationActions{
    override fun navigateUp() {}
    override fun navigateToChildProfile(childId: Int) {}
}
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
                dateOfBirth = LocalDate.of(2010,10,10)
            ),
            ChildData(
                id = 2,
                firstName = "Ali",
                lastName = "Ali",
                fatherFirstName = "Bassam",
                fatherLastName = "Ali",
                motherFirstName = "Laila",
                motherLastName = "Ale",
                dateOfBirth = LocalDate.of(2010,10,10)
            ),
            ChildData(
                id = 3,
                firstName = "soha",
                lastName = "Ali",
                fatherFirstName = "Bassam",
                fatherLastName = "Ali",
                motherFirstName = "Laila",
                motherLastName = "Ale",
                dateOfBirth = LocalDate.of(2010,10,10)
            )
        )
    )
)
internal val emptyResult = flowOf(
    PagingData.empty<ChildData>()
)
@DarkAndLightModePreview
@Composable
fun ChildrenSearchScreenSuccessPreview(){
    Hospital_AutomationTheme {
        ChildrenSearchScreen(
            onAction = {},
            navigationActions = mockActions,
            uiState = ChildrenSearchUiState(state = ScreenState.SUCCESS),
            children = fakeChildren.collectAsLazyPagingItems()
        )
    }
}
@DarkAndLightModePreview
@Composable
fun ChildrenSearchScreenErrorPreview(){
    Hospital_AutomationTheme {
        ChildrenSearchScreen(
            onAction = {},
            navigationActions = mockActions,
            uiState = ChildrenSearchUiState(state = ScreenState.ERROR),
            children = fakeChildren.collectAsLazyPagingItems()
        )
    }
}
@DarkAndLightModePreview
@Composable
fun ChildrenSearchScreenIdlePreview(){
    Hospital_AutomationTheme {
        ChildrenSearchScreen(
            onAction = {},
            navigationActions = mockActions,
            uiState = ChildrenSearchUiState(),
            children =null
        )
    }
}

@DarkAndLightModePreview
@Composable
fun ChildrenSearchScreenEmptyPreview(){
    Hospital_AutomationTheme {
        ChildrenSearchScreen(
            onAction = {},
            navigationActions = mockActions,
            uiState = ChildrenSearchUiState(state = ScreenState.SUCCESS),
            children = emptyResult.collectAsLazyPagingItems()
        )
    }
}

@DarkAndLightModePreview
@Composable
fun ChildrenSearchScreenLoadingPreview(){
    Hospital_AutomationTheme {
        ChildrenSearchScreen(
            onAction = {},
            navigationActions = mockActions,
            uiState = ChildrenSearchUiState(state = ScreenState.LOADING),
            children = fakeChildren.collectAsLazyPagingItems()
        )
    }
}
