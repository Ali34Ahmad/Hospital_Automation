package com.example.employment_requests.main

import com.example.model.enums.ScreenState
import com.example.model.work_request.RequestState

class RequestsUiActions(
    navigationActions: RequestsNavigationUiActions,
    businessActions: RequestsBusinessUiActions,
) : RequestsBusinessUiActions by businessActions,
    RequestsNavigationUiActions by navigationActions


interface RequestsBusinessUiActions {
    fun onChangeRequestState(id:Int, state: RequestState)
    fun onRefresh()
    fun onUpdateScreenState(screenState: ScreenState)
    fun clearToastMessage()
    fun onToggleTheme()
    fun onToggleDrawer()
}

interface RequestsNavigationUiActions {
    fun navigateToEmployeeProfileDetailsScreen(employeeId:Int?)
    fun navigateToDoctorProfileDetailsScreen(doctorId:Int?)
    fun navigateToPharmacyDetailsScreen(pharmacyId: Int?)
    fun navigateToAdminProfile()
    fun navigateToVaccines()
    fun navigateToVaccineTable()
}
