package com.example.login.navigation

import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.login.main.LoginNavigationUiActions
import com.example.login.main.LoginScreen
import com.example.login.main.LoginViewModel
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
        val uiState = viewModel.uiState.collectAsState()

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