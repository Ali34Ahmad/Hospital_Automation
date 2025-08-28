package com.example.employment_requests.main

import com.example.model.enums.ScreenState
import com.example.model.work_request.RequestState
import com.example.utility.ui.AppNavigationUiAction

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
}

interface RequestsNavigationUiActions {
    fun navigateUp()
    fun navigateToRequestDetailsScreen(vaccineId:Int)
}
