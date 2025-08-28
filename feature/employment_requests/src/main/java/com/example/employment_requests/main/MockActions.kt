package com.example.employment_requests.main

import com.example.model.enums.ScreenState
import com.example.model.work_request.RequestState


fun mockRequestsNavigationUiActions() = object : RequestsNavigationUiActions {
    override fun navigateUp() {

    }

    override fun navigateToRequestDetailsScreen(vaccineId: Int) {

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
