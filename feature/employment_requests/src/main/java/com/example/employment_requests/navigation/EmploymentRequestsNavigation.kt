package com.example.employment_requests.navigation

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.employment_requests.presentation.RequestsNavigationUiActions
import com.example.employment_requests.presentation.RequestsScreen
import com.example.employment_requests.presentation.RequestsViewModel
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
    onNavigateToAdminProfile:()->Unit,
    onNavigateToVaccines:()->Unit,
    onNavigateToVaccineTable:()->Unit,
) {
    composable<EmploymentRequestsRoute> {
        val viewModel = koinViewModel<RequestsViewModel>()
        val uiState = viewModel.uiState.collectAsStateWithLifecycle()
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

            override fun navigateToAdminProfile() {
                onNavigateToAdminProfile()
            }

            override fun navigateToVaccines() {
                onNavigateToVaccines()
            }

            override fun navigateToVaccineTable() {
                onNavigateToVaccineTable()
            }

        }

        RequestsScreen(
            uiState = uiState.value,
            requests = vaccines,
            uiActions = viewModel.getUiActions(navActions),
        )
    }
}
