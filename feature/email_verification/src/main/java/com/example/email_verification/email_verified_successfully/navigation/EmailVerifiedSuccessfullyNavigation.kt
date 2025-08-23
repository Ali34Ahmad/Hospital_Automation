package com.example.email_verification.email_verified_successfully.navigation

import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.email_verification.email_verified_successfully.main.EmailVerifiedSuccessfullyNavigationUiActions
import com.example.email_verification.email_verified_successfully.main.EmailVerifiedSuccessfullyScreen
import com.example.email_verification.email_verified_successfully.main.EmailVerifiedSuccessfullyViewModel
import com.example.navigation.extesion.navigateToScreen
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel


@Serializable
data class EmailVerifiedSuccessfullyRoute(
    val email: String,
    val password: String? = null,
    val navigateToResetPassword: Boolean
)

fun NavController.navigateToEmailVerifiedSuccessfullyScreen(
    email: String,
    password: String? = null,
    navigateToResetPassword: Boolean
) {
    navigateToScreen(EmailVerifiedSuccessfullyRoute(email,password,navigateToResetPassword))
}

fun NavGraphBuilder.emailVerifiedSuccessfullyScreen(
    onNavigateToResetPasswordScreen: (email: String) -> Unit,
    onNavigateToNextScreen: () -> Unit,
) {
    composable<EmailVerifiedSuccessfullyRoute> {
        val emailVerifiedSuccessfullyViewModel =
            koinViewModel<EmailVerifiedSuccessfullyViewModel>()
        val emailVerifiedSuccessfullyUiState =
            emailVerifiedSuccessfullyViewModel.uiState.collectAsState()

        val navActions = object : EmailVerifiedSuccessfullyNavigationUiActions {
            override fun navigateToUploadEmployeeDocumentsScreen() {
                onNavigateToNextScreen()
            }

            override fun navigateToResetPasswordScreen() {
                onNavigateToResetPasswordScreen(emailVerifiedSuccessfullyUiState.value.email)
            }
        }
        EmailVerifiedSuccessfullyScreen(
            uiState = emailVerifiedSuccessfullyUiState.value,
            uiActions = emailVerifiedSuccessfullyViewModel.getUiActions(navActions),
        )
    }
}