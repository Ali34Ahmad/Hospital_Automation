package com.example.medical_prescriptions.navigation

import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.medical_prescriptions.main.MedicalPrescriptionsNavigationUiActions
import com.example.medical_prescriptions.main.MedicalPrescriptionsScreen
import com.example.medical_prescriptions.main.MedicalPrescriptionsViewModel
import com.example.navigation.extesion.navigateToScreen
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel


@Serializable
data class MedicalPrescriptionsRoute(
    val patientId: Int?,
    val childId: Int?
)

fun NavController.navigateToMedicalPrescriptionsScreen(patientId: Int?, childId: Int?) {
    navigateToScreen(MedicalPrescriptionsRoute(patientId, childId))
}

fun NavGraphBuilder.medicalPrescriptionsScreen(
    onNavigateUp: () -> Unit,
    onNavigateToPrescriptionDetailsScreen: (prescriptionId: Int) -> Unit,
) {
    composable<MedicalPrescriptionsRoute> {
        val viewModel = koinViewModel<MedicalPrescriptionsViewModel>()
        val uiState = viewModel.uiState.collectAsState()
        val medicalPrescriptions = viewModel.prescriptionsFlow.collectAsLazyPagingItems()

        val navActions = object : MedicalPrescriptionsNavigationUiActions {
            override fun navigateUp() {
                onNavigateUp()
            }

            override fun navigateToMedicalPrescriptionDetailsScreen(
                prescriptionId: Int?,
            ) {
                onNavigateToPrescriptionDetailsScreen(prescriptionId ?: -1)
            }
        }

        MedicalPrescriptionsScreen(
            uiState = uiState.value,
            uiActions = viewModel.getUiActions(navActions),
            prescriptions = medicalPrescriptions,
        )
    }
}
