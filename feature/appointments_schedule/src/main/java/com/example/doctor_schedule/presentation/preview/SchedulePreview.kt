package com.example.doctor_schedule.presentation.preview

import androidx.compose.runtime.Composable
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.doctor_schedule.navigation.AppointmentSearchType
import com.example.doctor_schedule.presentation.ScheduleNavigationActions
import com.example.doctor_schedule.presentation.ScheduleScreen
import com.example.doctor_schedule.presentation.ScheduleUIState
import com.example.doctor_schedule.presentation.model.AppointmentUIModel
import com.example.model.enums.ScreenState
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.util.UiText
import kotlinx.coroutines.flow.flowOf
import java.time.LocalDate
import java.time.LocalTime

val appointments = flowOf(
    PagingData.from(listOf(
        AppointmentUIModel.SeparatorModel(desc = UiText.DynamicString("Today")),
        AppointmentUIModel.AppointmentModel(
            imageUrl =null,
            id = 1,
            fullName = "Ali Mansoura",
            appointmentType = "Check Up",
            date = LocalDate.of(2025,10,10),
            time = LocalTime.of(10,10,)
        )
    ))
)
@DarkAndLightModePreview
@Composable
fun DoctorSchedulePermissionsRequiredPreview(){
    Hospital_AutomationTheme {
        ScheduleScreen(
            uiState = ScheduleUIState(
                id = null,
                screenState = ScreenState.SUCCESS,
                hasAdminAccess = false,
                searchType = AppointmentSearchType.DOCTOR,
                name = "Ali Mansoura",
                speciality = "Dentist",
                imageUrl = "fake",
            ),
            appointments = appointments.collectAsLazyPagingItems(),
            navigationActions = mockActions,
            onAction ={}
        )
    }
}
@DarkAndLightModePreview
@Composable
fun DoctorSchedulePreview(){
    Hospital_AutomationTheme {
        ScheduleScreen(
            uiState = ScheduleUIState(
                screenState = ScreenState.SUCCESS,
                selectedDate = LocalDate.now(),
                isPermissionGranted = true,
                id = null,
                hasAdminAccess = false,
                name = "Ali Mansoura",
                speciality = "Dentist",
                imageUrl = "fake",
                searchType = AppointmentSearchType.DOCTOR,
            ),

            appointments = appointments.collectAsLazyPagingItems(),
            navigationActions = mockActions,
            onAction ={},
        )
    }
}
internal val mockActions =  object : ScheduleNavigationActions{
    override fun navigateToAppointmentDetails(doctorId: Int) {

    }

    override fun navigateToVaccineTable() {

    }

    override fun navigateUp() {

    }

    override fun navigateToProfile() {

    }

    override fun navigateToChildProfile(id: Int) {

    }

    override fun navigateToUserProfileProfile(id: Int) {

    }

    override fun navigateToNotifications() {

    }

    override fun navigateToMedicalRecords() {

    }

    override fun navigateToPrescriptions() {

    }

    override fun navigateToVaccines() {

    }
}