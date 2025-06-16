package com.example.upload_employee_documents.navigation

import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.navigation.extesion.navigateToScreen
import com.example.upload_employee_documents.main.UploadEmployeeDocumentsNavigationUiActions
import com.example.upload_employee_documents.main.UploadEmployeeDocumentsScreen
import com.example.upload_employee_documents.main.UploadEmployeeDocumentsViewModel
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel


@Serializable
data object UploadEmployeeDocumentsRoute

fun NavController.navigateToUploadEmployeeDocumentsScreen() {
    navigateToScreen(UploadEmployeeDocumentsRoute)
}

fun NavGraphBuilder.uploadEmploymentDocumentsScreen(
    onNavigateToAddResidentialAddressScreen: () -> Unit,
) {
    composable<UploadEmployeeDocumentsRoute> {
        val uploadEmployeeDocumentsViewModel =
            koinViewModel<UploadEmployeeDocumentsViewModel>()
        val uploadEmployeeDocumentsUiState =
            uploadEmployeeDocumentsViewModel.uiState.collectAsState()

        val navActions = object : UploadEmployeeDocumentsNavigationUiActions {
            override fun navigateToAddResidentialAddressScreen() {
                onNavigateToAddResidentialAddressScreen()
            }
        }

        UploadEmployeeDocumentsScreen(
            uiState = uploadEmployeeDocumentsUiState.value,
            uiActions = uploadEmployeeDocumentsViewModel.getUiActions(navActions)
        )
    }
}