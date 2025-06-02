package com.example.signup.navigation

import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.navigation.extesion.navigateToScreen
import com.example.signup.main.SignUpNavigationUiActions
import com.example.signup.main.SignUpScreen
import com.example.signup.main.SignUpViewModel
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
data object SignUpRoute

fun NavController.navigateToSignUpScreen() {
    navigateToScreen(SignUpRoute)
}

fun NavGraphBuilder.signUpScreen(
    onNavigateToEmailVerificationScreen: (email: String, password: String) -> Unit,
    onNavigateToLoginScreen: () -> Unit,
) {
    composable<SignUpRoute> {
        val viewModel = koinViewModel<SignUpViewModel>()
        val uiState = viewModel.uiState.collectAsState()

        val navActions = object : SignUpNavigationUiActions {
            override fun navigateToEmailVerificationScreen() {
                onNavigateToEmailVerificationScreen(
                    uiState.value.email,
                    uiState.value.password,
                )
            }

            override fun navigateToLoginScreen() {
                onNavigateToLoginScreen()
            }
        }

        SignUpScreen(
            uiState = uiState.value,
            uiActions = viewModel.getUiActions(navActions)
        )
    }
}