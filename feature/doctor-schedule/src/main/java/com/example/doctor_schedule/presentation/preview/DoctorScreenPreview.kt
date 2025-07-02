package com.example.doctor_schedule.presentation.preview

import androidx.compose.runtime.Composable
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.doctor_schedule.presentation.DoctorScheduleNavigationActions
import com.example.doctor_schedule.presentation.DoctorScheduleScreen
import com.example.doctor_schedule.presentation.DoctorScheduleUIState
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
            imageUrl = "",
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
        DoctorScheduleScreen(
            uiState = DoctorScheduleUIState(screenState = ScreenState.SUCCESS, doctorId = 112),
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
        DoctorScheduleScreen(
            uiState = DoctorScheduleUIState(
                screenState = ScreenState.SUCCESS,
                doctorId = 112,
                isPermissionGranted = true,
                selectedDate = LocalDate.now()
            ),
            appointments = appointments.collectAsLazyPagingItems(),
            navigationActions = mockActions,
            onAction ={},
        )
    }
}
internal val mockActions =  object : DoctorScheduleNavigationActions{
    override fun navigateToAppointmentDetails(doctorId: Int) {

    }

    override fun navigateToDoctorProfile(doctorId: Int) {
        TODO("Not yet implemented")
    }

    override fun navigateToNotifications(doctorId: Int) {
        TODO("Not yet implemented")
    }

    override fun navigateToMedicalRecords(doctorId: Int) {
        TODO("Not yet implemented")
    }

    override fun navigateToPrescriptions(doctorId: Int) {
        TODO("Not yet implemented")
    }

    override fun navigateToVaccines(doctorId: Int) {
        TODO("Not yet implemented")
    }
}