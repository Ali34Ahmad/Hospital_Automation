package com.example.login.navigation

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.login.presentation.LoginNavigationUiActions
import com.example.login.presentation.LoginScreen
import com.example.login.presentation.LoginViewModel
import com.example.navigation.extesion.navigateToScreen
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
data object LoginRoute

fun NavController.navigateToLoginScreen() {
    navigateToScreen(LoginRoute)
}

fun NavGraphBuilder.loginScreen(
    onNavigateToToSignUpScreen: () -> Unit,
    onNavigateToEnterEmailScreen: () -> Unit,
    onNavigateToHomeScreen: () -> Unit,
) {
    composable<LoginRoute> {
        val viewModel = koinViewModel<LoginViewModel>()
        val uiState = viewModel.uiState.collectAsStateWithLifecycle()

        val navActions = object : LoginNavigationUiActions {
            override fun navigateToSignUpScreen() {
                onNavigateToToSignUpScreen()
            }

            override fun navigateToEnterEmailScreen() {
                onNavigateToEnterEmailScreen()
            }

            override fun navigateToHomeScreen() {
                onNavigateToHomeScreen()
            }
        }

        LoginScreen(
            uiState = uiState.value,
            uiActions = viewModel.getUiActions(navActions)
        )
    }
}