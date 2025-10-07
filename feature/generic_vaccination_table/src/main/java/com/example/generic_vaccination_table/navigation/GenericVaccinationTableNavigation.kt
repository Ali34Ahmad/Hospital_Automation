package com.example.generic_vaccination_table.navigation

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.generic_vaccination_table.presentation.GenericVaccinationTableNavigationUiActions
import com.example.generic_vaccination_table.presentation.GenericVaccinationTableScreen
import com.example.generic_vaccination_table.presentation.GenericVaccinationTableViewModel
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

fun NavGraphBuilder.genericVaccinationTableScreen(
    onNavigateToVaccineDetailsScreen:(vaccineId: Int)->Unit,
    onNavigateUp:()->Unit,
) {
    composable<GenericVaccinationTableRoute> {
        val viewModel = koinViewModel<GenericVaccinationTableViewModel>()
        val uiState = viewModel.uiState.collectAsStateWithLifecycle()

        val navActions = object : GenericVaccinationTableNavigationUiActions {
            override fun navigateToVaccineDetailsScreen(vaccineId: Int) {
                onNavigateToVaccineDetailsScreen(vaccineId)
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
