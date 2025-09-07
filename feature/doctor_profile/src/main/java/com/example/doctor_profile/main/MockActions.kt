package com.example.doctor_profile.main


fun mockDoctorProfileNavigationUiActions()=object : DoctorProfileNavigationUiActions {
    override fun navigateToAppointmentsScreen(doctorId: Int?, name: String, specialty: String?) {

    }

    override fun navigateToEmploymentHistoryScreen(doctorId:Int?) {

    }

    override fun navigateUp() {

    }

    override fun navigateToLoginScreen() {

    }

    override fun navigateToPrescriptionsScreen(doctorId:Int?) {

    }

    override fun navigateToMedicalRecordsScreen(doctorId: Int?) {

    }

    override fun navigateToDepartmentDetailsScreen(clinicId: Int) {

    }

}

fun mockDoctorProfileBusinessUiActions()= object : DoctorProfileBusinessUiActions {
    override fun onDeactivateAccount() {

    }

    override fun onReactivateAccount() {

    }

    override fun onLogout() {

    }

    override fun onResignDoctor() {

    }

    override fun onHideErrorDialog() {

    }

    override fun onRefresh() {

    }

    override fun onClearToastMessage() {

    }

    override fun onUpdateSelectedAppointmentTypeDialog(index: Int?) {

    }

}
