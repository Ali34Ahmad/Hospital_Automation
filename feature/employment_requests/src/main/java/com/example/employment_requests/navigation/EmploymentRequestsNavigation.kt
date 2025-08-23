package com.example.employment_requests.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.employment_requests.main.RequestsScreen
import com.example.navigation.extesion.navigateToScreen
import kotlinx.serialization.Serializable


@Serializable
data object EmploymentRequestsRoute

fun NavController.navigateToEmploymentRequestsScreen() {
    navigateToScreen(EmploymentRequestsRoute)
}

fun NavGraphBuilder.employmentRequestsScreen(
    onNavigateUp:()->Unit,
    onNavigateToVaccineDetailsScreen:(vaccineId:Int)->Unit,
) {
    composable<EmploymentRequestsRoute> {
//        val viewModel = koinViewModel<AllVaccinesViewModel>()
//        val uiState = viewModel.uiState.collectAsState()
//        val vaccines=viewModel.vaccinesFlow.collectAsLazyPagingItems()
//
//        val navActions = object : AllVaccinesNavigationUiActions {
//            override fun navigateUp() {
//                onNavigateUp()
//            }
//
//            override fun navigateToVaccineDetailsScreen(vaccineId: Int) {
//                onNavigateToVaccineDetailsScreen(vaccineId)
//            }
//        }

        RequestsScreen(
//            uiState = uiState.value,
//            vaccines = vaccines,
//            uiActions = viewModel.getUiActions(navActions),
        )
    }
}
