package com.example.network.remote.account_management

import com.example.network.model.request.DeactivateMyEmployeeAccountRequest
import com.example.network.model.response.DeactivateMyEmployeeAccountResponse
import com.example.utility.network.Result
import com.example.utility.network.rootError

interface EmployeeAccountManagementApiService {
    suspend fun deactivateMyEmployeeAccount(
        deactivateMyEmployeeAccountRequest:DeactivateMyEmployeeAccountRequest
    ): Result<DeactivateMyEmployeeAccountResponse, rootError>

}