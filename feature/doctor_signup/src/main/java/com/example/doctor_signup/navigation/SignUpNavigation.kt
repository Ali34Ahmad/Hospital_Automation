package com.example.doctor_signup.navigation

import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.doctor_signup.main.DoctorSignUpNavigationUiActions
import com.example.doctor_signup.main.DoctorSignUpScreen
import com.example.doctor_signup.main.DoctorSignUpViewModel
import com.example.navigation.extesion.navigateToScreen
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
data object DoctorSignUpRoute

fun NavController.navigateToSignUpScreen() {
    navigateToScreen(DoctorSignUpRoute)
}

fun NavGraphBuilder.doctorSignUpScreen(
    onNavigateToEmailVerificationScreen: (email: String, password: String) -> Unit,
    onNavigateToLoginScreen: () -> Unit,
) {
    composable<DoctorSignUpRoute> {
        val viewModel = koinViewModel<DoctorSignUpViewModel>()
        val uiState = viewModel.uiState.collectAsState()

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