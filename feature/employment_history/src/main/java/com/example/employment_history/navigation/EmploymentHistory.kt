package com.example.employment_history.navigation

import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.employment_history.main.EmploymentHistoryNavigationUiActions
import com.example.employment_history.main.EmploymentHistoryScreen
import com.example.employment_history.main.EmploymentHistoryViewModel
import com.example.navigation.extesion.navigateToScreen
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel


@Serializable
data class EmploymentHistoryRoute(
    val id: Int?,
)

fun NavController.navigateToEmploymentHistoryScreen(id: Int?=null) {
    navigateToScreen(EmploymentHistoryRoute(id))
}

fun NavGraphBuilder.employmentHistoryScreen(
    onNavigateToAcceptedByAdminProfileScreen: (adminId: Int) -> Unit,
    onNavigateToToResignedByAdminProfileScreen: (resignedById: Int) -> Unit,
    onNavigateUp:()->Unit,
    onNavigateToToSuspendedByAdminProfileScreen: (suspendedById: Int, currentEmployeeId: Int) -> Unit,
) {
    composable<EmploymentHistoryRoute> {
        val viewModel = koinViewModel<EmploymentHistoryViewModel>()
        val uiState = viewModel.uiState.collectAsState()

        val navActions = object : EmploymentHistoryNavigationUiActions {
            override fun navigateToAcceptedByAdminProfileScreen() {
                onNavigateToAcceptedByAdminProfileScreen(
                    uiState.value.employmentHistory?.acceptedBy?.userId
                        ?: -1
                )
            }

            override fun navigateToToResignedByAdminProfileScreen() {
                onNavigateToToResignedByAdminProfileScreen(
                    uiState.value.employmentHistory?.resignedBy?.userId
                        ?: -1,
                )
            }

            override fun navigateToToSuspendedByAdminProfileScreen() {
                onNavigateToToSuspendedByAdminProfileScreen(
                    uiState.value.employmentHistory?.suspendedBy?.userId
                        ?: -1,
                    uiState.value.employmentHistory?.currentUser?.userId
                        ?: -1,
                )
            }

            override fun navigateUp() {
                onNavigateUp()
            }

        }

        EmploymentHistoryScreen(
            uiState = uiState.value,
            uiActions = viewModel.getUiActions(navActions)
        )
    }
}
