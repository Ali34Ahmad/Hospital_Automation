package com.example.pharmacy_details.navigation

import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.navigation.extesion.navigateToScreen
import com.example.pharmacy_details.main.PharmacyDetailsNavigationUiActions
import com.example.pharmacy_details.main.PharmacyDetailsScreen
import com.example.pharmacy_details.main.PharmacyDetailsViewModel
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel


@Serializable
data class PharmacyDetailsRoute(
    val pharmacyAccessType: PharmacyAccessType,
    val pharmacyId: Int?,
)

fun NavController.navigateToPharmacyDetailsScreen(
    pharmacyAccessType: PharmacyAccessType,
    pharmacyId: Int?
) {
    navigateToScreen(PharmacyDetailsRoute(pharmacyAccessType, pharmacyId))
}

fun NavGraphBuilder.pharmacyDetailsScreen(
    onNavigateUp: () -> Unit,
    onNavigateToEmailApp: (email: String, subject: String?) -> Unit,
    onNavigateToCallApp: (phoneNumber: String) -> Unit,
    onNavigateToFulfilledPrescriptionsScreen: (pharmacyId: Int) -> Unit,
    onNavigateToMedicinesScreen: (pharmacyId: Int, pharmacyName: String, imageUrl: String) -> Unit,
    onNavigateToEmploymentHistoryScreen: (pharmacyId: Int) -> Unit,
) {
    composable<PharmacyDetailsRoute> {
        val viewModel = koinViewModel<PharmacyDetailsViewModel>()
        val uiState = viewModel.uiState.collectAsState()

        val navActions = object : PharmacyDetailsNavigationUiActions {
            override fun navigateUp() {
                onNavigateUp()
            }

            override fun navigateToEmailApp(email: String, subject: String?) {
                onNavigateToEmailApp(email, subject)
            }

            override fun navigateToCallApp(phoneNumber: String) {
                onNavigateToCallApp(phoneNumber)
            }

            override fun navigateToFulfilledPrescriptionsScreen(pharmacyId: Int) {
                onNavigateToFulfilledPrescriptionsScreen(pharmacyId)
            }

            override fun navigateToMedicinesScreen(pharmacyId: Int) {
                onNavigateToMedicinesScreen(
                    pharmacyId,
                    uiState.value.pharmacyInfo?.phName ?: "",
                    uiState.value.pharmacyInfo?.userWithAddress?.imageUrl ?: ""
                )
            }

            override fun navigateToEmploymentHistoryScreen(pharmacyId: Int) {
                onNavigateToEmploymentHistoryScreen(pharmacyId)
            }

        }

        PharmacyDetailsScreen(
            uiState = uiState.value,
            uiActions = viewModel.getUiActions(navActions)
        )
    }
}
