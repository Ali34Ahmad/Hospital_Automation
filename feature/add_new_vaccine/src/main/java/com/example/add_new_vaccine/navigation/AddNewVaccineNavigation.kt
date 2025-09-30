package com.example.add_new_vaccine.navigation

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.add_new_vaccine.presentation.AddNewVaccineNavigationUiActions
import com.example.add_new_vaccine.presentation.AddNewVaccineScreen
import com.example.add_new_vaccine.presentation.AddNewVaccineViewModel
import com.example.navigation.extesion.navigateToScreen
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
data object AddNewVaccineRoute

fun NavController.navigateToAddNewVaccineScreen() {
    navigateToScreen(AddNewVaccineRoute)
}

fun NavGraphBuilder.addNewVaccineScreen(
    onNavigateToVaccineDetailsScreenScreen: (vaccineId: Int) -> Unit,
    onNavigateUp: () -> Unit,
) {
    composable<AddNewVaccineRoute> {
        val viewModel = koinViewModel<AddNewVaccineViewModel>()
        val uiState = viewModel.uiState.collectAsStateWithLifecycle()

        val navActions = object : AddNewVaccineNavigationUiActions {
            override fun navigateToVaccineDetailsScreenScreen() {
                onNavigateToVaccineDetailsScreenScreen(uiState.value.vaccineId?:-1)
            }

            override fun navigateUp() {
                onNavigateUp()
            }

        }

        AddNewVaccineScreen(
            uiState = uiState.value,
            uiActions = viewModel.getUiActions(navActions)
        )
    }
}