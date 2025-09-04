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
    onNavigateToEmployeeProfileDetailsScreen:(employeeId:Int?)->Unit,
    onNavigateToDoctorProfileDetailsScreen:(doctorId:Int?)->Unit,
    onNavigateToPharmacyDetailsScreen:(pharmacyId:Int?)->Unit,
) {
    composable<EmploymentRequestsRoute> {
        val viewModel = koinViewModel<RequestsViewModel>()
        val uiState = viewModel.uiState.collectAsState()
        val vaccines=viewModel.requestsFlow.collectAsLazyPagingItems()

        val navActions = object : RequestsNavigationUiActions {
            override fun navigateToEmployeeProfileDetailsScreen(employeeId:Int?) {
                onNavigateToEmployeeProfileDetailsScreen(employeeId)
            }

            override fun navigateToDoctorProfileDetailsScreen(doctorId: Int?) {
                onNavigateToDoctorProfileDetailsScreen(doctorId)
            }

            override fun navigateToPharmacyDetailsScreen(pharmacyId: Int?) {
                onNavigateToPharmacyDetailsScreen(pharmacyId)
            }

        }

        RequestsScreen(
            uiState = uiState.value,
            requests = vaccines,
            uiActions = viewModel.getUiActions(navActions),
        )
    }
}
