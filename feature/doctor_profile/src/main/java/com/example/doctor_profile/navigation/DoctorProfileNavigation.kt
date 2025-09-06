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
    onNavigateToEmploymentHistoryScreen: (doctorId:Int?) -> Unit,
    onNavigateToLoginScreen: () -> Unit,
    onNavigateUp: () -> Unit,
    onNavigateToAppointmentsScreen: (doctorId:Int?) -> Unit,
    onNavigateToPrescriptionsScreen: (doctorId:Int?) -> Unit,
    onNavigateToMedicalRecordsScreen: (doctorId:Int?) -> Unit,
    onNavigateToDepartmentScreen: (clinicId:Int) -> Unit,
) {
    composable<DoctorProfileRoute> {
        val viewModel = koinViewModel<DoctorProfileViewModel>()
        val uiState = viewModel.uiState.collectAsState()

        val navActions = object : DoctorProfileNavigationUiActions {
            override fun navigateToAppointmentsScreen(doctorId: Int?) {
                onNavigateToAppointmentsScreen(doctorId)
            }

            override fun navigateToEmploymentHistoryScreen(doctorId:Int?) {
                onNavigateToEmploymentHistoryScreen(doctorId)
            }

            override fun navigateUp() {
                onNavigateUp()
            }

            override fun navigateToLoginScreen() {
                onNavigateToLoginScreen()
            }

            override fun navigateToPrescriptionsScreen(doctorId:Int?) {
                onNavigateToPrescriptionsScreen(doctorId)
            }

            override fun navigateToMedicalRecordsScreen(doctorId: Int?) {
                onNavigateToMedicalRecordsScreen(doctorId)
            }

            override fun navigateToDepartmentDetailsScreen(clinicId: Int) {
                onNavigateToDepartmentScreen(clinicId)
            }
        }

        DoctorProfileScreen(
            uiState = uiState.value,
            uiActions = viewModel.getUiActions(navActions)
        )
    }
}
