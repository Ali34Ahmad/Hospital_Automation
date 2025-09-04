package com.example.employment_requests.main

import com.example.model.enums.ScreenState
import com.example.model.work_request.RequestState


fun mockRequestsNavigationUiActions() = object : RequestsNavigationUiActions {
    override fun navigateToEmployeeProfileDetailsScreen(employeeId: Int?) {

    }

    override fun navigateToDoctorProfileDetailsScreen(doctorId: Int?) {

    }

    override fun navigateToPharmacyDetailsScreen(pharmacyId: Int?) {

    }

}

fun mockRequestsBusinessUiActions() = object : RequestsBusinessUiActions {
    override fun onChangeRequestState(
        id: Int,
        state: RequestState
    ) {

    }

    override fun onRefresh() {

    }

    override fun onUpdateScreenState(screenState: ScreenState) {

    }

    override fun clearToastMessage() {

    }

}
