package com.example.appointment_details.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.appointment_details.presentation.AppointmentDetailsScreen
import com.example.appointment_details.presentation.AppointmentDetailsViewModel
import com.example.appointment_details.presentation.AppointmentDetailsNavigationActions
import com.example.navigation.extesion.navigateReplacingCurrent
import com.example.navigation.extesion.navigateToScreen
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel


/**
 * Appointment details route,
 *
 * You need to hide the navigation icon when you come after finish a work flow
 * like adding a prescription
 *
 * @param appointmentId the ID of the appointment
 * @param canEdit only a doctor can Edit.
 *
 * @author Ali Mansoura
 */
@Serializable
data class AppointmentDetailsRoute(
    val appointmentId : Int,
    val canEdit: Boolean,
)
fun NavController.navigateToAppointmentDetailsReplacementCurrent(
    appointmentId: Int,
    canEdit: Boolean
){
    navigateReplacingCurrent(
        route = AppointmentDetailsRoute(
            appointmentId = appointmentId,
            canEdit
        )
    )
}

fun NavController.navigateToAppointmentDetails(
    appointmentId: Int,
    canEdit: Boolean
){
    navigateToScreen(
        route = AppointmentDetailsRoute(
            appointmentId = appointmentId,
            canEdit = canEdit
        )
    )
}

fun NavGraphBuilder.appointmentDetailsScreen(
    onNavigateUp : () -> Unit,
    onNavigateToDepartmentDetails: (deptId: Int)-> Unit,
    onNavigateToVaccineDetails: (vaccineId: Int)-> Unit,
    onNavigateToAddMedicalDiagnosis: (
            appointmentId: Int,
            fullName: String,
            patientId: Int?,
            childId: Int?,
            canSkip: Boolean,
    )-> Unit,
    onNavigateToGuardianProfile: (guardianId: Int)-> Unit,
    onNavigateToDoctorSchedule: ()-> Unit,
){
    composable<AppointmentDetailsRoute>{
        val viewModel = koinViewModel<AppointmentDetailsViewModel>()
        val navigationActions = object : AppointmentDetailsNavigationActions{
            override fun navigateToGuardianProfile(guardianId: Int) = onNavigateToGuardianProfile(guardianId)

            override fun navigateUp() = onNavigateUp()
            override fun navigateToAddMedicalDiagnosis(
                appointmentId: Int,
                fullName: String,
                patientId: Int?,
                childId: Int?,
                canSkip: Boolean,
            ) = onNavigateToAddMedicalDiagnosis(
                appointmentId,fullName,patientId,childId,canSkip
            )

            override fun navigateToDepartmentDetails(deptId: Int) = onNavigateToDepartmentDetails(deptId)
            override fun navigateToVaccineDetails(vaccineId: Int) = onNavigateToVaccineDetails(vaccineId)
            override fun navigateToDoctorSchedule() = onNavigateToDoctorSchedule()
        }
        AppointmentDetailsScreen(
            viewModel = viewModel,
            navigationActions = navigationActions
        )
    }
}