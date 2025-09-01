package com.example.medical_records.navigation

import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.medical_records.main.MedicalRecordsNavigationUiActions
import com.example.medical_records.main.MedicalRecordsScreen
import com.example.medical_records.main.MedicalRecordsViewModel
import com.example.navigation.extesion.navigateToScreen
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel


@Serializable
data class MedicalRecordsRoute(
    val doctorId: Int? = null,
)

fun NavController.navigateToMedicalRecordsScreen() {
    navigateToScreen(MedicalRecordsRoute)
}

fun NavGraphBuilder.medicalRecordsScreen(
    onNavigateUp: () -> Unit,
    onNavigateToAppointmentsScreen: (patientId: Int?, childId: Int?) -> Unit,
    onNavigateToPrescriptionsScreen: (patientId: Int?, childId: Int?,doctorId:Int?) -> Unit,
) {
    composable<MedicalRecordsRoute> {
        val viewModel = koinViewModel<MedicalRecordsViewModel>()
        val uiState = viewModel.uiState.collectAsState()
        val vaccines = viewModel.medicalRecordsFlow.collectAsLazyPagingItems()

        val navActions = object : MedicalRecordsNavigationUiActions {
            override fun navigateUp() {
                onNavigateUp()
            }

            override fun navigateToAppointmentsScreen(
                patientId: Int?,
                childId: Int?
            ) {
                onNavigateToAppointmentsScreen(patientId, childId)
            }

            override fun navigateToPrescriptionsScreen(
                patientId: Int?,
                childId: Int?,
                doctorId: Int?,
            ) {
                onNavigateToPrescriptionsScreen(patientId, childId,doctorId)
            }

        }

        MedicalRecordsScreen(
            uiState = uiState.value,
            medicalRecords = vaccines,
            uiActions = viewModel.getUiActions(navActions),
        )
    }
}
