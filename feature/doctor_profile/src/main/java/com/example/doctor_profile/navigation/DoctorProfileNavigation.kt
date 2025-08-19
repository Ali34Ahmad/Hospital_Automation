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
    val doctorProfileAccessType: DoctorProfileAccessType,
    val doctorId: Int?,
)

fun NavController.navigateToDoctorProfileScreen(doctorProfileAccessType: DoctorProfileAccessType, doctorId: Int?) {
    navigateToScreen(DoctorProfileRoute(doctorProfileAccessType,doctorId))
}

fun NavGraphBuilder.doctorProfileScreen(
    onNavigateToEmploymentHistoryScreen: () -> Unit,
    onNavigateToLoginScreen: () -> Unit,
    onNavigateUp: () -> Unit,
    onNavigateToAppointmentsScreen: () -> Unit,
    onNavigateToPrescriptionsScreen: () -> Unit,
    onNavigateToMedicalRecordsScreen: () -> Unit,
    onNavigateToDepartmentScreen: () -> Unit,
) {
    composable<DoctorProfileRoute> {
        val viewModel = koinViewModel<DoctorProfileViewModel>()
        val uiState = viewModel.uiState.collectAsState()

        val navActions = object : DoctorProfileNavigationUiActions {
            override fun navigateToAppointmentsScreen() {
                onNavigateToAppointmentsScreen()
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

            override fun navigateToPrescriptionsScreen() {
                onNavigateToPrescriptionsScreen()
            }

            override fun navigateToMedicalRecordsScreen() {
                onNavigateToMedicalRecordsScreen()
            }

            override fun navigateToDepartmentDetailsScreen() {
                onNavigateToDepartmentScreen()
            }
        }

        DoctorProfileScreen(
            uiState = uiState.value,
            uiActions = viewModel.getUiActions(navActions)
        )
    }
}
