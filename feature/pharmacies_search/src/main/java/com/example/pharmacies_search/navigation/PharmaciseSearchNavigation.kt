package com.example.pharmacies_search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.navigation.extesion.switchToTab
import com.example.pharmacies_search.presentation.PharmaciesSearchNavigationActions
import com.example.pharmacies_search.presentation.PharmaciesSearchScreen
import com.example.pharmacies_search.presentation.PharmaciesSearchViewModel
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
object PharmaciesSearchSearchRoute

fun NavController.switchToPharmaciesSearchSearch(
    startDestination: Any
){
    switchToTab(
        route = PharmaciesSearchSearchRoute,
        startDestination = startDestination
    )
}

fun NavGraphBuilder.pharmaciesSearch(
    onNavigateToPharmacyDetails: (pharmacyId: Int)-> Unit
){
    composable<PharmaciesSearchSearchRoute> {
        val viewModel = koinViewModel<PharmaciesSearchViewModel>()
        val navigationActions = object : PharmaciesSearchNavigationActions{
            override fun navigateToPharmacyDetails(pharmacyId: Int) = onNavigateToPharmacyDetails(pharmacyId)

            override fun navigateToAdminProfile() {
                TODO("Not yet implemented")
            }

            override fun navigateToVaccines() {
                TODO("Not yet implemented")
            }

            override fun navigateToNotifications() {
                TODO("Not yet implemented")
            }

            override fun navigateToPrescriptions() {
                TODO("Not yet implemented")
            }

            override fun navigateToMedicalRecords() {
                TODO("Not yet implemented")
            }

            override fun navigateToVaccineTable() {
                TODO("Not yet implemented")
            }
        }
        PharmaciesSearchScreen(
            viewModel = viewModel,
            navigationActions = navigationActions
        )
    }
}
