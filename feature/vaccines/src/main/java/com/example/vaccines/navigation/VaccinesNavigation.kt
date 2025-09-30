package com.example.vaccines.navigation

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.model.enums.Role
import com.example.vaccines.presentation.VaccinesNavigationUiActions
import com.example.vaccines.presentation.VaccinesScreen
import com.example.vaccines.presentation.VaccinesViewModel
import com.example.navigation.extesion.navigateToScreen
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel


@Serializable
data class VaccinesRoute(
    val accessedByRole: Role
)

fun NavController.navigateToVaccinesScreen(accessedByRole: Role) {
    navigateToScreen(VaccinesRoute(accessedByRole))
}

fun NavGraphBuilder.vaccinesScreen(
    onNavigateUp:()->Unit,
    onNavigateToVaccineDetailsScreen:(vaccineId:Int)->Unit,
    onNavigateToAddNewVaccineScreen:()->Unit,
) {
    composable<VaccinesRoute> {
        val viewModel = koinViewModel<VaccinesViewModel>()
        val uiState = viewModel.uiState.collectAsStateWithLifecycle()
        val vaccines=viewModel.vaccinesFlow.collectAsLazyPagingItems()

        val navActions = object : VaccinesNavigationUiActions {
            override fun navigateUp() {
                onNavigateUp()
            }

            override fun navigateToVaccineDetailsScreen(vaccineId: Int) {
                onNavigateToVaccineDetailsScreen(vaccineId)
            }

            override fun navigateToAddNewVaccineScreen() {
                onNavigateToAddNewVaccineScreen()
            }
        }

        VaccinesScreen(
            uiState = uiState.value,
            vaccines = vaccines,
            uiActions = viewModel.getUiActions(navActions),
        )
    }
}
