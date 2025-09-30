package com.example.add_residential_address.navigation

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.add_residential_address.presentation.AddResidentialAddressNavigationUiActions
import com.example.add_residential_address.presentation.AddResidentialAddressScreen
import com.example.add_residential_address.presentation.AddResidentialAddressViewModel
import com.example.navigation.extesion.navigateToScreen
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel


@Serializable
data object AddResidentialAddressRoute

fun NavController.navigateToAddResidentialAddressScreen() {
    navigateToScreen(AddResidentialAddressRoute)
}

fun NavGraphBuilder.addResidentialAddressScreen(
    onNavigateToUploadProfileImageScreen: () -> Unit,
) {
    composable<AddResidentialAddressRoute> {
        val uploadEmployeeDocumentsViewModel =
            koinViewModel<AddResidentialAddressViewModel>()
        val uploadEmployeeDocumentsUiState =
            uploadEmployeeDocumentsViewModel.uiState.collectAsStateWithLifecycle()

        val navActions = object : AddResidentialAddressNavigationUiActions {
            override fun navigateToUploadProfileImageScreen() {
                onNavigateToUploadProfileImageScreen()
            }
        }

        AddResidentialAddressScreen(
            uiState = uploadEmployeeDocumentsUiState.value,
            uiActions = uploadEmployeeDocumentsViewModel.getUiActions(navActions)
        )
    }
}