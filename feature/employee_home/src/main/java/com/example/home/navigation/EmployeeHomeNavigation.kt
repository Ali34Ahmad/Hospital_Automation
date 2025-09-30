package com.example.home.navigation

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.home.presentation.EmployeeHomeNavigationUiActions
import com.example.home.presentation.HomeScreen
import com.example.home.presentation.EmployeeHomeViewModel
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
        val viewModel = koinViewModel<EmployeeHomeViewModel>()
        val uiState = viewModel.uiState.collectAsStateWithLifecycle()

        val navActions = object : EmployeeHomeNavigationUiActions {
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