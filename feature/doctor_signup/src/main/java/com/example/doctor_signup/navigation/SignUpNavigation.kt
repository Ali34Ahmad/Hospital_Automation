package com.example.doctor_signup.navigation

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.doctor_signup.presentation.DoctorSignUpNavigationUiActions
import com.example.doctor_signup.presentation.DoctorSignUpScreen
import com.example.doctor_signup.presentation.DoctorSignUpViewModel
import com.example.navigation.extesion.navigateToScreen
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
data object DoctorSignUpRoute

fun NavController.navigateToDoctorSignUpScreen() {
    navigateToScreen(DoctorSignUpRoute)
}

fun NavGraphBuilder.doctorSignUpScreen(
    onNavigateToEmailVerificationScreen: (email: String, password: String) -> Unit,
    onNavigateToLoginScreen: () -> Unit,
) {
    composable<DoctorSignUpRoute> {
        val viewModel = koinViewModel<DoctorSignUpViewModel>()
        val uiState = viewModel.uiState.collectAsStateWithLifecycle()

        val navActions = object : DoctorSignUpNavigationUiActions {
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

        DoctorSignUpScreen(
            uiState = uiState.value,
            uiActions = viewModel.getUiActions(navActions)
        )
    }
}