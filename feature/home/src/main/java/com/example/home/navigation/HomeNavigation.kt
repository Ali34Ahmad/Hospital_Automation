package com.example.home.navigation

import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.home.main.HomeNavigationUiActions
import com.example.home.main.HomeScreen
import com.example.home.main.HomeViewModel
import com.example.navigation.extesion.navigateToScreen
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel


@Serializable
object HomeRoute

fun NavController.navigateToHomeScreen() {
    navigateToScreen(HomeRoute)
}

fun NavGraphBuilder.homeScreen(
    onNavigateToAddChildScreen: () -> Unit,
    onNavigateToAddGuardianScreen: () -> Unit,
    onNavigateToEmployeeProfileScreen: () -> Unit,
    onNavigateToRequestsScreen: () -> Unit,
    onNavigateToAddedChildrenScreen: () -> Unit,
) {
    composable<HomeRoute> {
        val viewModel = koinViewModel<HomeViewModel>()
        val uiState = viewModel.uiState.collectAsState()

        val navActions = object : HomeNavigationUiActions {
            override fun navigateToAddChildScreen() {
                onNavigateToAddChildScreen()
            }

            override fun navigateToAddGuardianScreen() {
                onNavigateToAddGuardianScreen()
            }

            override fun navigateToEmployeeProfileScreen() {
                onNavigateToEmployeeProfileScreen()
            }

            override fun navigateToRequestsScreen() {
                onNavigateToRequestsScreen()
            }

            override fun navigateToAddedChildrenScreen() {
                onNavigateToAddedChildrenScreen()
            }
        }

        HomeScreen(
            uiState = uiState.value,
            uiActions = viewModel.getUiActions(navActions)
        )
    }
}