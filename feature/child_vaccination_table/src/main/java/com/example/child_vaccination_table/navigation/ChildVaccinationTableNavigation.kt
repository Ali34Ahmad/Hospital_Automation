package com.example.child_vaccination_table.navigation

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.child_vaccination_table.presentation.ChildVaccinationTableNavigationUiActions
import com.example.child_vaccination_table.presentation.ChildVaccinationTableScreen
import com.example.child_vaccination_table.presentation.ChildVaccinationTableViewModel
import com.example.navigation.extesion.navigateToScreen
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel


@Serializable
data class ChildVaccinationTableRoute(
    val childId:Int
)


fun NavController.navigateToChildVaccinationTableScreen(childId:Int) {
    navigateToScreen(ChildVaccinationTableRoute(childId))
}

fun NavGraphBuilder.childVaccinationTableScreen(
    windowSizeClass: WindowSizeClass,
    onNavigateToVaccineDetailsScreen:(vaccineId: Int)->Unit,
    onNavigateToDoctorProfileScreen:(doctorId: Int)->Unit,
    onNavigateToAppointmentDetailsScreen:(appointmentId: Int)->Unit,
    onNavigateUp:()->Unit,
) {
    composable<ChildVaccinationTableRoute> {
        val viewModel = koinViewModel<ChildVaccinationTableViewModel>()
        val uiState = viewModel.uiState.collectAsStateWithLifecycle()

        val navActions = object : ChildVaccinationTableNavigationUiActions {
            override fun navigateToVaccineDetailsScreen(vaccineId: Int) {
                onNavigateToVaccineDetailsScreen(vaccineId)
            }

            override fun navigateToDoctorProfileScreen(doctorId: Int) {
                onNavigateToDoctorProfileScreen(doctorId)
            }

            override fun navigateToAppointmentDetailsScreen(appointmentId: Int) {
                onNavigateToAppointmentDetailsScreen(appointmentId)
            }

            override fun navigateUp() {
                onNavigateUp()
            }

        }

        ChildVaccinationTableScreen(
            uiState = uiState.value,
            uiActions = viewModel.getUiActions(navActions),
            windowSizeClass = windowSizeClass,
        )
    }
}
