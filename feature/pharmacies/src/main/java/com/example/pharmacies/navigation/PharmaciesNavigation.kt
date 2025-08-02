package com.example.pharmacies.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.navigation.extesion.navigateToScreen
import com.example.pharmacies.presentaion.PharmaciesNavigationActions
import com.example.pharmacies.presentaion.PharmaciesScreen
import com.example.pharmacies.presentaion.PharmaciesViewModel
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
data class PharmaciesRoute(
    val medicineId: Int,
    val medicineName: String,
)

fun NavController.navigateToPharmacies(
    medicineId: Int,
    medicineName: String
){
    navigateToScreen(
        route = PharmaciesRoute(
            medicineId = medicineId,
            medicineName = medicineName
        )
    )
}

fun NavGraphBuilder.pharmaciesScreen(
    onNavigateUp : () -> Unit,
    onNavigateToPharmacyDetails :(pharmacyId: Int) -> Unit
){
  composable<PharmaciesRoute> {
      val viewModel = koinViewModel<PharmaciesViewModel>()
      val navigationActions = object : PharmaciesNavigationActions{
          override fun navigateBack() = onNavigateUp()

          override fun navigateToPharmacyDetails(pharmacyId: Int) = onNavigateToPharmacyDetails(pharmacyId)
      }

      PharmaciesScreen(
          viewModel = viewModel,
          navigationActions = navigationActions
      )
  }
}
