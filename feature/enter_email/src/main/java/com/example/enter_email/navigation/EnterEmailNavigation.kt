package com.example.enter_email.navigation

import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.enter_email.main.EnterEmailNavigationUiActions
import com.example.enter_email.main.EnterEmailScreen
import com.example.enter_email.main.EnterEmailViewModel
import com.example.navigation.extesion.navigateToScreen
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
data object EnterEmailRoute

fun NavController.navigateToEnterEmailScreen() {
    navigateToScreen(EnterEmailRoute)
}

fun NavGraphBuilder.enterEmailScreen(
    onNavigateToEmailOtpVerificationScreen: (email: String, navigateToResetPassword: Boolean) -> Unit,
) {
    composable<EnterEmailRoute> {
        val viewModel = koinViewModel<EnterEmailViewModel>()
        val uiState = viewModel.uiState.collectAsState()

        val navActions = object : EnterEmailNavigationUiActions {
            override fun navigateToEmailOtpVerificationScreen() {
                onNavigateToEmailOtpVerificationScreen(
                    uiState.value.email,
                    true,
                )
            }

        }

        EnterEmailScreen(
            uiState = uiState.value,
            uiActions = viewModel.getUiActions(navActions)
        )
    }
}