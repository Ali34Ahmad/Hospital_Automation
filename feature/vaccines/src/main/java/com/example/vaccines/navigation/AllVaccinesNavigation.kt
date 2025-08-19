package com.example.vaccines.navigation

import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.vaccines.main.AllVaccinesNavigationUiActions
import com.example.vaccines.main.AllVaccinesScreen
import com.example.vaccines.main.AllVaccinesViewModel
import com.example.navigation.extesion.navigateToScreen
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel


@Serializable
data object AllVaccinesRoute

fun NavController.navigateToAllVaccinesScreen() {
    navigateToScreen(AllVaccinesRoute)
}

fun NavGraphBuilder.allVaccinesScreen(
    onNavigateUp:()->Unit,
    onNavigateToVaccineDetailsScreen:(vaccineId:Int)->Unit,
) {
    composable<AllVaccinesRoute> {
        val viewModel = koinViewModel<AllVaccinesViewModel>()
        val uiState = viewModel.uiState.collectAsState()
        val vaccines=viewModel.vaccinesFlow.collectAsLazyPagingItems()

        val navActions = object : AllVaccinesNavigationUiActions {
            override fun navigateUp() {
                onNavigateUp()
            }

            override fun navigateToVaccineDetailsScreen(vaccineId: Int) {
                onNavigateToVaccineDetailsScreen(vaccineId)
            }
        }

        AllVaccinesScreen(
            uiState = uiState.value,
            vaccines = vaccines,
            uiActions = viewModel.getUiActions(navActions),
        )
    }
}
