package com.example.medicine_details.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.medicine_details.presentation.MedicineDetailsNavigationActions
import com.example.medicine_details.presentation.MedicineDetailsScreen
import com.example.medicine_details.presentation.MedicineDetailsViewModel
import com.example.navigation.extesion.navigateToScreen
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
data class MedicineDetailsRoute(
    val medicineId: Int
)

fun NavController.navigateToMedicineDetails(
    medicineId: Int,
){
    navigateToScreen(
        route = MedicineDetailsRoute(
            medicineId = medicineId
        )
    )
}

fun NavGraphBuilder.medicineDetailsScreen(
    onNavigateUp: ()-> Unit
){
    composable<MedicineDetailsRoute> {
        val viewModel = koinViewModel<MedicineDetailsViewModel>()
        val navigationActions = object : MedicineDetailsNavigationActions{
            override fun navigateBack() = onNavigateUp()
        }

        MedicineDetailsScreen(
            viewModel = viewModel,
            navigationActions = navigationActions
        )
    }
}











