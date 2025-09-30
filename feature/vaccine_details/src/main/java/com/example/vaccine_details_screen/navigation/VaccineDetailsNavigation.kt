package com.example.vaccine_details_screen.navigation

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.navigation.extesion.navigateReplacingCurrent
import com.example.navigation.extesion.navigateToScreen
import com.example.vaccine_details_screen.presentation.VaccineDetailsNavigationUiActions
import com.example.vaccine_details_screen.presentation.VaccineDetailsScreen
import com.example.vaccine_details_screen.presentation.VaccineDetailsViewModel
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
data class VaccineDetailsRoute(
    val vaccinePreviousScreen: VaccinePreviousScreen,
    val vaccineId: Int?,
)


fun NavController.navigateToVaccineDetailsScreen(vaccinePreviousScreen: VaccinePreviousScreen,vaccineId: Int) {
    navigateToScreen(VaccineDetailsRoute(vaccinePreviousScreen,vaccineId))
}

fun NavController.navigateToVaccineDetailsScreenReplacingCurrentScreen(vaccinePreviousScreen: VaccinePreviousScreen,vaccineId: Int) {
    navigateReplacingCurrent(VaccineDetailsRoute(vaccinePreviousScreen,vaccineId))
}


fun NavGraphBuilder.vaccineDetailsScreen(
    onNavigateToVaccinationTableScreen:()->Unit,
    onNavigateUp:()->Unit,
) {
    composable<VaccineDetailsRoute> {
        val viewModel = koinViewModel<VaccineDetailsViewModel>()
        val uiState = viewModel.uiState.collectAsStateWithLifecycle()

        val navActions = object : VaccineDetailsNavigationUiActions {
            override fun navigateToVaccinationTableScreen() {
                onNavigateToVaccinationTableScreen()
            }

            override fun navigateUp() {
                onNavigateUp()
            }

        }

        VaccineDetailsScreen(
            uiState = uiState.value,
            uiActions = viewModel.getUiActions(navActions)
        )
    }
}
