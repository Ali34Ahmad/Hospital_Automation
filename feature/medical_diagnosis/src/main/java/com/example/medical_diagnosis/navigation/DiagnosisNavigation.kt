package com.example.medical_diagnosis.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.medical_diagnosis.presentation.DiagnosisNavigationActions
import com.example.medical_diagnosis.presentation.DiagnosisScreen
import com.example.medical_diagnosis.presentation.DiagnosisViewModel
import com.example.navigation.extesion.navigateToScreen
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
data class DiagnosisRoute(
    val appointmentId: Int,
    val fullName: String,
    val patientId: Int?,
    val childId: Int?,
    val canSkip: Boolean = false,
)

fun NavController.navigateToDiagnosisScreen(
    appointmentId: Int,
    fullName: String,
    patientId: Int?,
    childId: Int?,
    canSkip: Boolean,
){
    navigateToScreen(
        route = DiagnosisRoute(
            appointmentId = appointmentId,
            fullName = fullName,
            patientId = patientId,
            childId = childId,
            canSkip = canSkip
        )
    )
}
fun NavGraphBuilder.diagnosisScreen(
    onNavigateToMedicinesSearchScreen: (
        childId: Int?,
        patientId: Int?,
        appointmentId: Int,
    )-> Unit,
    onNavigateToAppointmentDetails: (Int)-> Unit,
){
    composable<DiagnosisRoute> {
        val viewModel = koinViewModel<DiagnosisViewModel>()
        val navigationActions = object : DiagnosisNavigationActions{
            override fun navigateToAppointmentDetails(appointmentId: Int) = onNavigateToAppointmentDetails(appointmentId)
            override fun navigateToMedicinesSearch(
                childId: Int?,
                patientId: Int?,
                appointmentId: Int,
            ) = onNavigateToMedicinesSearchScreen(
                childId,
                patientId,
                appointmentId
            )
        }
        DiagnosisScreen(
            viewModel = viewModel,
            navigationActions = navigationActions
        )
    }
}