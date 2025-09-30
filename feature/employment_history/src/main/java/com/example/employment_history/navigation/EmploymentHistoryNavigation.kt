package com.example.employment_history.navigation

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.employment_history.presentation.EmploymentHistoryNavigationUiActions
import com.example.employment_history.presentation.EmploymentHistoryScreen
import com.example.employment_history.presentation.EmploymentHistoryViewModel
import com.example.model.enums.Role
import com.example.navigation.extesion.navigateToScreen
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel


@Serializable
data class EmploymentHistoryRoute(
    val userId: Int?,
    val roleOfRequestedUserHistory: Role? = null,
    val pharmacyId: Int? = null,
)

fun NavController.navigateToEmploymentHistoryScreen(
    userId: Int?,
    roleOfRequestedUserHistory: Role? = null,
    pharmacyId: Int? = null,
) {
    navigateToScreen(EmploymentHistoryRoute(userId, roleOfRequestedUserHistory, pharmacyId))
}

fun NavGraphBuilder.employmentHistoryScreen(
    onNavigateToAcceptedByAdminProfileScreen: (adminId: Int) -> Unit,
    onNavigateToToResignedByAdminProfileScreen: (resignedById: Int) -> Unit,
    onNavigateUp: () -> Unit,
    onNavigateToSuspendedByAdminProfileScreen: (suspendedById: Int, currentEmployeeId: Int, role: Role?,
            pharmacyId:Int?) -> Unit,
) {
    composable<EmploymentHistoryRoute> {
        val viewModel = koinViewModel<EmploymentHistoryViewModel>()
        val uiState = viewModel.uiState.collectAsStateWithLifecycle()

        val navActions = object : EmploymentHistoryNavigationUiActions {
            override fun navigateToAcceptedByAdminProfileScreen() {
                onNavigateToAcceptedByAdminProfileScreen(
                    uiState.value.employmentHistory?.acceptedBy?.userId
                        ?: uiState.value.pharmacyContractResponse?.acceptedBy?.id ?: -1
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
                        ?: uiState.value.pharmacyContractResponse?.deactivatedBy?.id ?: -1,
                    uiState.value.employmentHistory?.currentUser?.userId
                        ?: uiState.value.pharmacyContractResponse?.pharmacyInHistory?.pharmacist?.userId ?: -1,
                    uiState.value.roleOfRequestedUserHistory,
                    uiState.value.pharmacyContractResponse?.pharmacyInHistory?.id
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
