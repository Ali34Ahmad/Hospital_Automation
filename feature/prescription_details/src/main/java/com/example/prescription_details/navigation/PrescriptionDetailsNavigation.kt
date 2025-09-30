package com.example.prescription_details.navigation

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.prescription_details.presentation.PrescriptionDetailsNavigationUiActions
import com.example.prescription_details.presentation.PrescriptionDetailsScreen
import com.example.medical_prescription_details.main.PrescriptionDetailsViewModel
import com.example.navigation.extesion.navigateToScreen
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel


@Serializable
data class PrescriptionDetailsRoute(
    val prescriptionId: Int
)

fun NavController.navigateToPrescriptionDetailsScreen(prescriptionId: Int) {
    navigateToScreen(PrescriptionDetailsRoute(prescriptionId))
}

fun NavGraphBuilder.prescriptionDetailsScreen(
    onNavigateUp: () -> Unit,
    onNavigateToPatientProfile: (id: Int) -> Unit,
    onNavigateToChildProfile: (id: Int) -> Unit,
    onNavigateToFulfillingPharmacy: (id: Int) -> Unit,
    onNavigateToMedicineDetails: (id: Int) -> Unit,
) {
    composable<PrescriptionDetailsRoute> {
        val viewModel = koinViewModel<PrescriptionDetailsViewModel>()
        val uiState = viewModel.uiState.collectAsStateWithLifecycle()

        val navActions = object : PrescriptionDetailsNavigationUiActions {
            override fun navigateUp() {
                onNavigateUp()
            }

            override fun navigateToPatientProfile(id: Int) {
                onNavigateToPatientProfile(id)
            }

            override fun navigateToChildProfile(id: Int) {
                onNavigateToChildProfile(id)
            }

            override fun navigateToFulfillingPharmacy(pharmacyId: Int) {
                onNavigateToFulfillingPharmacy(pharmacyId)
            }

            override fun navigateToMedicineDetails(medicineId: Int) {
                onNavigateToMedicineDetails(medicineId)
            }
        }

        PrescriptionDetailsScreen(
            uiState = uiState.value,
            uiActions = viewModel.getUiActions(navActions)
        )
    }
}
