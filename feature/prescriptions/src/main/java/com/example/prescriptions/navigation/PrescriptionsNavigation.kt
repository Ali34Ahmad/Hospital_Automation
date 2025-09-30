package com.example.prescriptions.navigation

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.prescriptions.presentation.PrescriptionsNavigationUiActions
import com.example.prescriptions.presentation.PrescriptionsScreen
import com.example.prescriptions.presentation.PrescriptionsViewModel
import com.example.navigation.extesion.navigateToScreen
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel


@Serializable
data class PrescriptionsRoute(
    val patientId: Int?=null,
    val childId: Int?=null,
    val doctorId: Int?=null,
)

fun NavController.navigateToPrescriptionsScreen(patientId: Int?, childId: Int?,doctorId: Int?) {
    navigateToScreen(PrescriptionsRoute(patientId, childId,doctorId))
}

fun NavGraphBuilder.prescriptionsScreen(
    onNavigateUp: () -> Unit,
    onNavigateToPrescriptionDetailsScreen: (prescriptionId: Int) -> Unit,
) {
    composable<PrescriptionsRoute> {
        val viewModel = koinViewModel<PrescriptionsViewModel>()
        val uiState = viewModel.uiState.collectAsStateWithLifecycle()
        val medicalPrescriptions = viewModel.prescriptionsFlow.collectAsLazyPagingItems()

        val navActions = object : PrescriptionsNavigationUiActions {
            override fun navigateUp() {
                onNavigateUp()
            }

            override fun navigateToPrescriptionDetailsScreen(
                prescriptionId: Int?,
            ) {
                onNavigateToPrescriptionDetailsScreen(prescriptionId ?: -1)
            }
        }

        PrescriptionsScreen(
            uiState = uiState.value,
            uiActions = viewModel.getUiActions(navActions),
            prescriptions = medicalPrescriptions,
        )
    }
}
