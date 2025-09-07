package com.example.vaccines.navigation

import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.model.enums.Role
import com.example.vaccines.main.VaccinesNavigationUiActions
import com.example.vaccines.main.VaccinesScreen
import com.example.vaccines.main.VaccinesViewModel
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
        val uiState = viewModel.uiState.collectAsState()
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
