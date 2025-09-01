package com.example.doctor_schedule.navigation

import androidx.annotation.Keep
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.doctor_schedule.presentation.ScheduleNavigationActions
import com.example.doctor_schedule.presentation.ScheduleScreen
import com.example.doctor_schedule.presentation.ScheduleViewModel
import com.example.model.enums.Role
import com.example.navigation.extesion.navigateReplacingCurrent
import com.example.navigation.extesion.navigateToScreen
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

/**
 * Represents a route for fetching appointments related to a specific role.
 *
 * @param id           The unique identifier of the target entity (doctor, user, or child).
 * @param hasAdminAccess  Indicates whether the request has admin privileges (enables additional features).
 * @param searchType   Specifies the role for which appointments are being requested:
 *                     [Role.DOCTOR], [Role.USER], or [Role.CHILD].
 * @param name The name of the (doctor,user, or child).
 * @param speciality The doctor speciality.
 *
 *
 * @see Role
 * @author Ali Mansoura
 */
@Serializable
data class ScheduleRoute(
    val id: Int? = null,
    val hasAdminAccess: Boolean,
    val searchType: AppointmentSearchType,
    val name: String?,
    val speciality: String?,
    val imageUrl: String?
)

@Keep
enum class AppointmentSearchType{
    DOCTOR,
    CHILD,
    USER,
}

fun NavController.navigateToScheduleScreen(
    id: Int? = null,
    hasAdminAccess: Boolean = false,
    searchType: AppointmentSearchType = AppointmentSearchType.DOCTOR,
    name: String? = null,
    speciality: String? = null,
    imageUrl: String? = null
){
    navigateToScreen(
        route = ScheduleRoute(
            id = id,
            hasAdminAccess = hasAdminAccess,
            searchType = searchType,
            name = name,
            speciality = speciality,
            imageUrl = imageUrl
        )
    )
}
fun NavController.navigateToScheduleScreenReplacingCurrent(
    id: Int? = null,
    hasAdminAccess: Boolean = false,
    searchType: AppointmentSearchType = AppointmentSearchType.DOCTOR,
    name: String? = null,
    speciality: String? = null,
    imageUrl: String? = null
){
    navigateReplacingCurrent(
        route = ScheduleRoute(
            id = id,
            hasAdminAccess = hasAdminAccess,
            searchType = searchType,
            name = name,
            speciality = speciality,
            imageUrl = imageUrl
        ),
    )
}

fun NavGraphBuilder.scheduleScreen(
    onNavigateToAppointmentDetails: (doctorId: Int)-> Unit,
    onNavigateToProfile: (Int)-> Unit,
    onNavigateToMedicalRecords: () -> Unit,
    onNavigateToPrescriptions: () -> Unit,
    onNavigateToVaccines: () -> Unit,
    onNavigateToNotifications: () -> Unit,
    onNavigateToVaccineTable: () -> Unit,
    onNavigateToUserProfile: (Int) -> Unit,
    onNavigateToChildProfile: (Int) -> Unit,
    onNavigateUp: ()-> Unit,
){
    composable<ScheduleRoute> {
        val viewModel = koinViewModel<ScheduleViewModel>()
        val navigationActions = object : ScheduleNavigationActions{
            override fun navigateToAppointmentDetails(doctorId: Int)= onNavigateToAppointmentDetails(doctorId)
            override fun navigateToProfile() = onNavigateToProfile(id)

            override fun navigateToChildProfile(id: Int) = onNavigateToChildProfile(id)

            override fun navigateToUserProfileProfile(id: Int) = onNavigateToUserProfile(id)

            override fun navigateToNotifications() = onNavigateToNotifications()
            override fun navigateToMedicalRecords() = onNavigateToMedicalRecords()
            override fun navigateToPrescriptions() = onNavigateToPrescriptions()
            override fun navigateToVaccines() =onNavigateToVaccines()
            override fun navigateToVaccineTable() = onNavigateToVaccineTable()
            override fun navigateUp() = onNavigateUp()
        }
        ScheduleScreen(
            viewModel = viewModel,
            navigationActions = navigationActions
        )
    }
}
