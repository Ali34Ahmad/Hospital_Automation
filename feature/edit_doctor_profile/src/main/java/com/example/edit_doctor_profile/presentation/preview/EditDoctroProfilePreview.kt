package com.example.edit_doctor_profile.presentation.preview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.edit_doctor_profile.presentation.EditDoctorProfileScreen
import com.example.edit_doctor_profile.presentation.EditDoctorProfileUIState
import com.example.edit_doctor_profile.presentation.ProfileDialog
import com.example.model.doctor.doctor_profile.DoctorProfileSummary
import com.example.model.enums.ScreenState
import com.example.model.user.FullName
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme

@DarkAndLightModePreview
@Composable
fun EditDoctorProfilePreview() {
    var uiState by remember {
        mutableStateOf(
            value = EditDoctorProfileUIState(
                screenState = ScreenState.SUCCESS,
                workDays = mockWorkdays,
                profileData = DoctorProfileSummary(
                    fullName = FullName("Ali", "Mazen", "Jamal"),
                    availabilitySchedule = emptyList(),
                    imageUrl = "fake image url",
                    appointmentTypes = emptyList()
                ),
                currentDialog = ProfileDialog.APPOINTMENT_TYPE,
                currentWorkDay = mockWorkday,
                selectedDayOfWeek = null,
                selectedStartTime = null,
                selectedEndTime = null,
                isRefreshing = false,
                toastMessage = null,
                appointmentTypes = mockAppointmentTypes,
                selectedAppointmentType = mockAppointmentType,
                isNameValid = true,
                isDurationValid = true,
                nameErrorText = null,
                durationErrorText = null
            )
        )
    }
    Hospital_AutomationTheme {
        EditDoctorProfileScreen(
            uiState = uiState,
            onAction = {},
            navigationActions = mockNavigationActions,
        )
    }
}