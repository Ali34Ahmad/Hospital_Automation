package com.example.doctor_profile.navigation

import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.doctor_profile.main.DoctorProfileNavigationUiActions
import com.example.doctor_profile.main.DoctorProfileScreen
import com.example.doctor_profile.main.DoctorProfileViewModel
import com.example.navigation.extesion.navigateToScreen
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel


@Serializable
data class DoctorProfileRoute(
    val profileAccessType: ProfileAccessType,
    val doctorId: Int?,
)

fun NavController.navigateToDoctorProfileScreen(profileAccessType: ProfileAccessType,doctorId: Int?) {
    navigateToScreen(DoctorProfileRoute(profileAccessType,doctorId))
}

fun NavGraphBuilder.doctorProfileScreen(
    onNavigateToEmploymentHistoryScreen: () -> Unit,
    onNavigateToLoginScreen: () -> Unit,
    onNavigateUp: () -> Unit,
    onNavigateToAddedChildrenScreen: () -> Unit,
) {
    composable<DoctorProfileRoute> {
        val viewModel = koinViewModel<DoctorProfileViewModel>()
        val uiState = viewModel.uiState.collectAsState()

        val navActions = object : DoctorProfileNavigationUiActions {
            override fun navigateToAddedChildrenScreen() {
                onNavigateToAddedChildrenScreen()
            }

            override fun navigateToEmploymentHistoryScreen() {
                onNavigateToEmploymentHistoryScreen()
            }

            override fun navigateUp() {
                onNavigateUp()
            }

            override fun navigateToLoginScreen() {
                onNavigateToLoginScreen()
            }
        }

        DoctorProfileScreen(
            uiState = uiState.value,
            uiActions = viewModel.getUiActions(navActions)
        )
    }
}
