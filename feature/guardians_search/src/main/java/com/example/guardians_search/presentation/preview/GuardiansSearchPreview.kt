package com.example.guardians_search.presentation.preview

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.guardians_search.presentation.GuardiansSearchNavigationActions
import com.example.guardians_search.presentation.GuardiansSearchScreen
import com.example.guardians_search.presentation.GuardiansSearchUiState
import com.example.model.enums.ScreenState
import com.example.model.guardian.GuardianData
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import kotlinx.coroutines.flow.flowOf


private val fakeGuardians = flowOf(
    PagingData.from(listOf(
        GuardianData(
            id = 1,
            img = null,
            fullName = "Ali Man"
        ),
        GuardianData(
            id = 2,
            img = null,
            fullName = "Ali ahmad"
        ),
        GuardianData(
            id = 3,
            img = null,
            fullName = "Ali Salem"
        )
    ))
)

private val emptyResult = flowOf(
    PagingData.from(emptyList<GuardianData>())
)

private val mockNavigationActions = object : GuardiansSearchNavigationActions{
    override fun navigateUp() {}
    override fun navigateToGuardianDetails(guardianId: Int, childId: Int?) {}
}

@DarkAndLightModePreview
@Composable
fun GuardiansSearchScreenPreview() {
    Hospital_AutomationTheme {
        val uiState by remember { mutableStateOf(GuardiansSearchUiState()) }
        GuardiansSearchScreen(
            modifier = Modifier.fillMaxSize(),
            uiState = uiState,
            navigationActions = mockNavigationActions,
            onAction = {},
            guardians = emptyResult.collectAsLazyPagingItems()
        )
    }

}

@DarkAndLightModePreview
@Composable
fun GuardiansSearchScreenEmptyPreview() {
    Hospital_AutomationTheme {
        val uiState by remember { mutableStateOf(GuardiansSearchUiState(
            searchQuery = "Ali",
            screenState = ScreenState.SUCCESS
        )) }
        GuardiansSearchScreen(
            modifier = Modifier.fillMaxSize(),
            uiState = uiState,
            navigationActions = mockNavigationActions,
            onAction = {},
            guardians = emptyResult.collectAsLazyPagingItems()
        )
    }
}
@DarkAndLightModePreview
@Composable
fun GuardiansSearchScreenLoadingPreview() {
    Hospital_AutomationTheme {
        val uiState by remember { mutableStateOf(GuardiansSearchUiState(
            searchQuery = "Ali",
            screenState = ScreenState.LOADING
        )) }
        GuardiansSearchScreen(
            modifier = Modifier.fillMaxSize(),
            uiState = uiState,
            navigationActions = mockNavigationActions,
            onAction = {},
            guardians = emptyResult.collectAsLazyPagingItems()
        )
    }
}
@DarkAndLightModePreview
@Composable
fun GuardiansSearchScreenSuccessPreview() {
    Hospital_AutomationTheme {
        var uiState by remember { mutableStateOf(GuardiansSearchUiState(
            searchQuery = "Ali",
            screenState = ScreenState.SUCCESS
        )) }
        GuardiansSearchScreen(
            modifier = Modifier.fillMaxSize(),
            uiState = uiState,
            navigationActions = mockNavigationActions,
            onAction = {},
            guardians = fakeGuardians.collectAsLazyPagingItems()
        )
    }

}
@DarkAndLightModePreview
@Composable
fun GuardiansSearchScreenErrorPreview() {
    Hospital_AutomationTheme {
        val uiState by remember { mutableStateOf(GuardiansSearchUiState(
            searchQuery = "Ali",
            screenState = ScreenState.ERROR
        )) }
        GuardiansSearchScreen(
            modifier = Modifier.fillMaxSize(),
            uiState = uiState,
            navigationActions = mockNavigationActions,
            onAction = {},
            guardians = fakeGuardians.collectAsLazyPagingItems()
        )
    }

}
@DarkAndLightModePreview
@Composable
fun GuardiansSearchScreenIdlePreview() {
    Hospital_AutomationTheme {
        val uiState by remember { mutableStateOf(GuardiansSearchUiState(
            searchQuery = "",
            screenState = ScreenState.IDLE
        )) }
        GuardiansSearchScreen(
            modifier = Modifier.fillMaxSize(),
            uiState = uiState,
            navigationActions = mockNavigationActions,
            onAction = {},
            guardians = null
        )
    }
}


