package com.example.network.remote.account_management

import android.util.Log
import com.example.datastore.repositories.UserPreferencesRepository
import com.example.network.model.request.DeactivateMyEmployeeAccountRequest
import com.example.network.model.response.DeactivateMyEmployeeAccountResponse
import com.example.network.model.response.NetworkMessage
import com.example.network.utility.ApiRoutes
import com.example.utility.network.NetworkError
import com.example.utility.network.rootError
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.flow.first
import com.example.utility.network.Result

class EmployeeAccountManagementApiServiceImpl(
    private val client: HttpClient,
    private val userPreferencesRepository: UserPreferencesRepository,
) : EmployeeAccountManagementApiService {

    override suspend fun deactivateMyEmployeeAccount(deactivateMyEmployeeAccountRequest: DeactivateMyEmployeeAccountRequest):
            Result<DeactivateMyEmployeeAccountResponse, rootError> =
        try {
            val response: HttpResponse = client.post(ApiRoutes.DEACTIVATE_MY_ACCOUNT) {
                contentType(ContentType.Application.Json)
                val token=userPreferencesRepository.userPreferencesFlow.first().token
                if (token.isNullOrBlank()){
                    return@post
                }
                Log.v("MyToken",token)
                bearerAuth(token)
            }
            when (response.status.value) {
                in 200..299 -> {
                    val responseBody: DeactivateMyEmployeeAccountResponse = response.body()
                    Log.v("DeactivateAccount: In Range 2xx", "Successfully Deactivated ${responseBody.updatedData}")
                    Result.Success(data = responseBody)
                }

                else -> {
                    val errorBody: NetworkMessage = response.body()
                    Log.e("DeactivateAccount: Out of Range 2xx", errorBody.message)
                    Result.Error(NetworkError.UNKNOWN)
                }
            }
        } catch (e: Exception) {
            Log.e("DeactivateAccount Exception:", e.message ?: "Unknown")
            Result.Error(NetworkError.UNKNOWN)
        }

}