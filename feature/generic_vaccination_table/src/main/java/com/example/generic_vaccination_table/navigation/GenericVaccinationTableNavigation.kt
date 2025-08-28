package com.example.generic_vaccination_table.navigation

import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.generic_vaccination_table.main.GenericVaccinationTableNavigationUiActions
import com.example.generic_vaccination_table.main.GenericVaccinationTableScreen
import com.example.generic_vaccination_table.main.GenericVaccinationTableViewModel
import com.example.navigation.extesion.navigateToScreen
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel


@Serializable
data class GenericVaccinationTableRoute(
    val genericVaccinationTableAccessType: GenericVaccinationTableAccessType,
)


fun NavController.navigateToGenericVaccinationTableScreen(genericVaccinationTableAccessType: GenericVaccinationTableAccessType) {
    navigateToScreen(GenericVaccinationTableRoute(genericVaccinationTableAccessType))
}

fun NavGraphBuilder.genericVaccineDetailsScreen(
    onNavigateToVaccineDetailsScreen:()->Unit,
    onNavigateUp:()->Unit,
) {
    composable<GenericVaccinationTableRoute> {
        val viewModel = koinViewModel<GenericVaccinationTableViewModel>()
        val uiState = viewModel.uiState.collectAsState()

        val navActions = object : GenericVaccinationTableNavigationUiActions {
            override fun navigateToVaccineDetailsScreen() {
                onNavigateToVaccineDetailsScreen()
            }

            override fun navigateUp() {
                onNavigateUp()
            }

        }

        GenericVaccinationTableScreen(
            uiState = uiState.value,
            uiActions = viewModel.getUiActions(navActions)
        )
    }
}
