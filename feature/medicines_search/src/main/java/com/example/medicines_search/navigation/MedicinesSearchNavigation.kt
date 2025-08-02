package com.example.medicines_search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.medicines_search.presentation.MedicineSearchViewModel
import com.example.medicines_search.presentation.MedicinesSearchNavigationActions
import com.example.medicines_search.presentation.MedicinesSearchScreen
import com.example.navigation.extesion.navigateReplacingCurrent
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
data class MedicinesSearchRoute(
    val childId: Int?,
    val patientId: Int?,
    val appointmentId: Int,
)

/**
 * Before navigation to this screen we have to ensure replacing the
 * current screen before navigation to the medicine search.
 */
fun NavController.navigateToMedicineSearchScreenReplacingCurrent(
    childId: Int?,
    patientId: Int?,
    appointmentId: Int
){
    navigateReplacingCurrent(
        route = MedicinesSearchRoute(
            childId = childId,
            patientId = patientId,
            appointmentId = appointmentId
        )
    )
}

fun NavGraphBuilder.medicinesScreen(
    onNavigateUp : () -> Unit,
    onNavigateToPharmacies: (medicineId: Int,medicineName: String) -> Unit,
    onNavigateToMedicineDetails: (medicineId: Int) -> Unit,
    onNavigateToAppointmentDetails: (appointmentId: Int) -> Unit,
){
  composable<MedicinesSearchRoute> {
      val viewModel = koinViewModel<MedicineSearchViewModel>()
      val navigationActions = object : MedicinesSearchNavigationActions{
          override fun navigateUp() = onNavigateUp()

          override fun navigateToPharmacies(medicineId: Int,medicineName: String) =  onNavigateToPharmacies(medicineId,medicineName)

          override fun navigateToMedicineDetails(medicineId: Int) = onNavigateToMedicineDetails(medicineId)
          override fun navigateToAppointmentDetails(appointmentId: Int) = onNavigateToAppointmentDetails(appointmentId)
      }
      MedicinesSearchScreen(
          viewModel = viewModel,
          navigationActions = navigationActions
      )
  }
}