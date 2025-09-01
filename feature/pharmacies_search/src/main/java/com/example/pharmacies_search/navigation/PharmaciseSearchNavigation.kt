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
    onNavigateToPharmacyDetails: (pharmacyId: Int)-> Unit,
    onNavigateToAdminProfile: ()-> Unit,
    onNavigateToVaccines: ()-> Unit,
    onNavigateToNotifications: ()-> Unit,
    onNavigateToPrescriptions: ()-> Unit,
    onNavigateToMedicalRecords: ()-> Unit,
    onNavigateToVaccineTable: ()-> Unit,
){
    composable<PharmaciesSearchSearchRoute> {
        val viewModel = koinViewModel<PharmaciesSearchViewModel>()
        val navigationActions = object : PharmaciesSearchNavigationActions{
            override fun navigateToPharmacyDetails(pharmacyId: Int) = onNavigateToPharmacyDetails(pharmacyId)

            override fun navigateToAdminProfile() = onNavigateToAdminProfile()
            override fun navigateToVaccines() = onNavigateToVaccines()

            override fun navigateToNotifications() = onNavigateToNotifications()

            override fun navigateToPrescriptions() = onNavigateToPrescriptions()

            override fun navigateToMedicalRecords() = onNavigateToMedicalRecords()

            override fun navigateToVaccineTable() = onNavigateToVaccineTable()
        }
        PharmaciesSearchScreen(
            viewModel = viewModel,
            navigationActions = navigationActions
        )
    }
}
