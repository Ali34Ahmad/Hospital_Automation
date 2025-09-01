package com.example.pharmacy_medicines.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.navigation.extesion.navigateToScreen
import com.example.pharmacy_medicines.presentation.PharmacyMedicineScreen
import com.example.pharmacy_medicines.presentation.PharmacyMedicinesNavigationActions
import com.example.pharmacy_medicines.presentation.PharmacyMedicinesViewModel
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
data class PharmacyMedicinesRoute(
    val pharmacyId: Int,
    val name: String,
    val imageUrl : String
)

fun NavController.navigateToPharmacyMedicines(
    pharmacyId: Int,
    name: String,
    imageUrl: String
){
    navigateToScreen(
        PharmacyMedicinesRoute(
            pharmacyId = pharmacyId,
            name = name,
            imageUrl = imageUrl
        )
    )
}

fun NavGraphBuilder.pharmacyMedicines(
    onNavigateUp: ()-> Unit,
    onNavigateToPharmacy :(Int)-> Unit,
    onNavigateToMedicineDetails :(Int)-> Unit,
){
    composable<PharmacyMedicinesRoute>{
        val viewModel = koinViewModel<PharmacyMedicinesViewModel>()
        val navigationActions = object : PharmacyMedicinesNavigationActions{
            override fun navigateUp() = onNavigateUp()

            override fun navigateToPharmacy(pharmacyId: Int) = onNavigateToPharmacy(pharmacyId)

            override fun navigateToMedicineDetails(medicineId: Int) = onNavigateToMedicineDetails(medicineId)

        }

        PharmacyMedicineScreen(
            viewModel = viewModel,
            navigationActions = navigationActions
        )
    }
}