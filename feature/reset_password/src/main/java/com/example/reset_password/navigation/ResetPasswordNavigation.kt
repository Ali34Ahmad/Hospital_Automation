package com.example.reset_password.navigation

import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.navigation.extesion.navigateToScreen
import com.example.reset_password.main.ResetPasswordNavigationUiActions
import com.example.reset_password.main.ResetPasswordScreen
import com.example.reset_password.main.ResetPasswordViewModel
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
data class ResetPasswordRoute(
    val email: String
)

fun NavController.navigateToResetPasswordScreen(email: String) {
    navigateToScreen(ResetPasswordRoute(email))
}

fun NavGraphBuilder.resetPasswordScreen(
    onNavigateToHomeScreen: () -> Unit,
) {
    composable<ResetPasswordRoute> {
        val viewModel = koinViewModel<ResetPasswordViewModel>()
        val uiState = viewModel.uiState.collectAsState()

        val navActions = object : ResetPasswordNavigationUiActions {
            override fun navigateToHomeScreen() {
                onNavigateToHomeScreen()
            }
        }

        ResetPasswordScreen(
            uiState = uiState.value,
            uiActions = viewModel.getUiActions(navActions)
        )
    }
}