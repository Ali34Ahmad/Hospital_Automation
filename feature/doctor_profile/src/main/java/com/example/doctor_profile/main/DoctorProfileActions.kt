package com.example.doctor_profile.main

class DoctorProfileUiActions(
    navigationActions: DoctorProfileNavigationUiActions,
    businessActions: DoctorProfileBusinessUiActions,
) : DoctorProfileBusinessUiActions by businessActions,
    DoctorProfileNavigationUiActions by navigationActions


interface DoctorProfileBusinessUiActions {
    fun onDeactivateAccount()
    fun onReactivateAccount()
    fun onLogout()
    fun onResignDoctor()
    fun onHideErrorDialog()
    fun onRefresh()
    fun onClearToastMessage()
    fun onUpdateSelectedAppointmentTypeDialog(index: Int?)
}

interface DoctorProfileNavigationUiActions {
    fun navigateToAppointmentsScreen(
        doctorId: Int?,
        name: String,
        specialty: String?,
        imageUrl: String?,
    )

    fun navigateToEmploymentHistoryScreen(doctorId: Int?)
    fun navigateUp()
    fun navigateToLoginScreen()
    fun navigateToPrescriptionsScreen(doctorId: Int?)
    fun navigateToMedicalRecordsScreen(doctorId: Int?)
    fun navigateToDepartmentDetailsScreen(clinicId: Int)
}