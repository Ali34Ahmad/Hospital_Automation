package com.example.employment_history.navigation

import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.employment_history.main.EmploymentHistoryNavigationUiActions
import com.example.employment_history.main.EmploymentHistoryScreen
import com.example.employment_history.main.EmploymentHistoryViewModel
import com.example.model.enums.Role
import com.example.navigation.extesion.navigateToScreen
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel


@Serializable
data class EmploymentHistoryRoute(
    val id: Int?,
    val roleOfRequestedUserHistory: Role? = null,
)

fun NavController.navigateToEmploymentHistoryScreen(
    doctorId: Int?,
    roleOfRequestedUserHistory: Role? = null,
) {
    navigateToScreen(EmploymentHistoryRoute(doctorId,roleOfRequestedUserHistory))
}

fun NavGraphBuilder.employmentHistoryScreen(
    onNavigateToAcceptedByAdminProfileScreen: (adminId: Int) -> Unit,
    onNavigateToToResignedByAdminProfileScreen: (resignedById: Int) -> Unit,
    onNavigateUp: () -> Unit,
    onNavigateToSuspendedByAdminProfileScreen: (suspendedById: Int, currentEmployeeId: Int, role: Role?) -> Unit,
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

            override fun navigateToResignedByAdminProfileScreen() {
                onNavigateToToResignedByAdminProfileScreen(
                    uiState.value.employmentHistory?.resignedBy?.userId
                        ?: -1,
                )
            }

            override fun navigateToSuspendedByAdminProfileScreen(role: Role?) {
                onNavigateToSuspendedByAdminProfileScreen(
                    uiState.value.employmentHistory?.suspendedBy?.userId
                        ?: -1,
                    uiState.value.employmentHistory?.currentUser?.userId
                        ?: -1,
                    uiState.value.roleOfRequestedUserHistory
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
