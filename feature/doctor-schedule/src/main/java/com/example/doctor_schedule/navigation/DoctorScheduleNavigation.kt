package com.example.doctor_schedule.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.doctor_schedule.presentation.DoctorScheduleNavigationActions
import com.example.doctor_schedule.presentation.DoctorScheduleScreen
import com.example.doctor_schedule.presentation.DoctorScheduleViewModel
import com.example.navigation.extesion.navigateReplacingCurrent
import com.example.navigation.extesion.navigateToScreen
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
object DoctorScheduleRoute

fun NavController.navigateToScheduleScreen(){
    navigateToScreen(
        route = DoctorScheduleRoute
    )
}
fun NavController.navigateToScheduleScreenReplacingCurrent(){
    navigateReplacingCurrent(
        route = DoctorScheduleRoute,
    )
}

fun NavGraphBuilder.doctorScheduleScreen(
    onNavigateToAppointmentDetails: (doctorId: Int)-> Unit,
    onNavigateToDoctorProfile: ()-> Unit,
    onNavigateToMedicalRecords: () -> Unit,
    onNavigateToPrescriptions: () -> Unit,
    onNavigateToVaccines: () -> Unit,
    onNavigateToNotifications: () -> Unit,
){
    composable<DoctorScheduleRoute> {
        val viewModel = koinViewModel<DoctorScheduleViewModel>()
        val navigationActions = object : DoctorScheduleNavigationActions{
            override fun navigateToAppointmentDetails(doctorId: Int)= onNavigateToAppointmentDetails(doctorId)
            override fun navigateToDoctorProfile() = onNavigateToDoctorProfile()
            override fun navigateToNotifications() = onNavigateToNotifications()
            override fun navigateToMedicalRecords() = onNavigateToMedicalRecords()
            override fun navigateToPrescriptions() = onNavigateToPrescriptions()
            override fun navigateToVaccines() =onNavigateToVaccines()
        }
        DoctorScheduleScreen(
            viewModel = viewModel,
            navigationActions = navigationActions
        )
    }
}