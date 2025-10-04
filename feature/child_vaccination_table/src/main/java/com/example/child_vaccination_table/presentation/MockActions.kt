package com.example.child_vaccination_table.presentation


fun mockNavigationUiActions()=object : ChildVaccinationTableNavigationUiActions {
    override fun navigateToVaccineDetailsScreen(vaccineId: Int) {

    }

    override fun navigateToDoctorProfileScreen(doctorId: Int) {

    }

    override fun navigateToAppointmentDetailsScreen(appointmentId: Int) {

    }

    override fun navigateUp() {

    }

}

fun mockBusinessUiActions()=object : ChildVaccinationTableBusinessUiActions {
    override fun onRefresh() {

    }

    override fun clearToastMessage() {

    }

}
