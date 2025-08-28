package com.example.employment_requests.navigation

import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.employment_requests.main.RequestsNavigationUiActions
import com.example.employment_requests.main.RequestsScreen
import com.example.employment_requests.main.RequestsViewModel
import com.example.navigation.extesion.navigateToScreen
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel


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
        val viewModel = koinViewModel<RequestsViewModel>()
        val uiState = viewModel.uiState.collectAsState()
        val vaccines=viewModel.requestsFlow.collectAsLazyPagingItems()

        val navActions = object : RequestsNavigationUiActions {
            override fun navigateUp() {
                TODO("Not yet implemented")
            }

            override fun navigateToRequestDetailsScreen(vaccineId: Int) {
                TODO("Not yet implemented")
            }

        }

        RequestsScreen(
            uiState = uiState.value,
            requests = vaccines,
            uiActions = viewModel.getUiActions(navActions),
        )
    }
}
