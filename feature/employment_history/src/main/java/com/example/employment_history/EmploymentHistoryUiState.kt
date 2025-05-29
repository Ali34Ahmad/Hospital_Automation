package com.example.employment_history

import com.example.model.employment_history.EmploymentHistoryResponse
import com.example.network.model.response.EmploymentHistoryResponseDto
import com.example.utility.network.Error

data class EmploymentHistoryUiState(
    val employmentHistory: EmploymentHistoryResponse?=null,
    val isLoading: Boolean=true,
    val fetchingHistoryError: Error?=null,
//
//    val showLoadingDialog: Boolean=false,
//    val loadingDialogText: String="",
//
    val showErrorDialog: Boolean=false,
    val errorDialogText: String="",
)
