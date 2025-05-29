package com.example.network.remote.account_management

import android.util.Log
import com.example.datastore.repositories.UserPreferencesRepository
import com.example.network.model.request.DeactivateMyEmployeeAccountRequestDto
import com.example.network.model.response.CheckEmployeePermissionResponseDto
import com.example.network.model.response.DeactivateMyEmployeeAccountResponseDto
import com.example.network.model.response.NetworkMessage
import com.example.network.model.response.ReactivateMyEmployeeAccountResponseDto
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
import io.ktor.client.request.get
import io.ktor.client.request.setBody

class EmployeeAccountManagementApiServiceImpl(
    private val client: HttpClient,
    private val userPreferencesRepository: UserPreferencesRepository,
) : EmployeeAccountManagementApiService {

    override suspend fun deactivateMyEmployeeAccount(deactivateMyEmployeeAccountRequestDto: DeactivateMyEmployeeAccountRequestDto):
            Result<DeactivateMyEmployeeAccountResponseDto, rootError> =
        try {
            val response: HttpResponse = client.post(ApiRoutes.DEACTIVATE_MY_ACCOUNT) {
                contentType(ContentType.Application.Json)
                val token=userPreferencesRepository.userPreferencesFlow.first().token
                if (token.isNullOrBlank()){
                    return@post
                }
                Log.v("MyToken",token)
                bearerAuth(token)
                setBody(deactivateMyEmployeeAccountRequestDto)
            }
            when (response.status.value) {
                in 200..299 -> {
                    val responseBody: DeactivateMyEmployeeAccountResponseDto = response.body()
                    val responseBodyText: String = response.body()
                    Log.v("DeactivateAccount: In Range 2xx", "Successfully Deactivated ${responseBody.updatedData}")
                    Log.v("DeactivateAccount: Body", responseBodyText)
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

    override suspend fun reactivateMyEmployeeAccount(): Result<ReactivateMyEmployeeAccountResponseDto, rootError> =
        try {
            val response: HttpResponse = client.post(ApiRoutes.REACTIVATE_MY_ACCOUNT) {
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
                    val responseBody: ReactivateMyEmployeeAccountResponseDto = response.body()
                    val responseBodyText: String = response.body()
                    Log.v("ReactivateAccount: In Range 2xx", "Successfully Deactivated ${responseBody.updatedData}")
                    Log.v("ReactivateAccount: Body", responseBodyText)
                    Result.Success(data = responseBody)
                }

                else -> {
                    val errorBody: NetworkMessage = response.body()
                    Log.e("ReactivateAccount: Out of Range 2xx", errorBody.message)
                    Result.Error(NetworkError.UNKNOWN)
                }
            }
        } catch (e: Exception) {
            Log.e("ReactivateAccount Exception:", e.message ?: "Unknown")
            Result.Error(NetworkError.UNKNOWN)
        }

    override suspend fun checkEmployeePermission(): Result<CheckEmployeePermissionResponseDto, rootError> =
        try {
            val response: HttpResponse = client.get(ApiRoutes.CHECK_EMPLOYEE_PERMISSION) {
                contentType(ContentType.Application.Json)
                val token=userPreferencesRepository.userPreferencesFlow.first().token
                if (token.isNullOrBlank()){
                    return@get
                }
                bearerAuth(token)
            }
            when (response.status.value) {
                in 200..299 -> {
                    val responseBody: CheckEmployeePermissionResponseDto = response.body()
                    Log.v("Check Employee Permission: In Range 2xx", "Successfully Checked ${responseBody.permissionGranted}")
                    Result.Success(data = responseBody)
                }

                else -> {
                    val errorBody: String = response.body()
                    Log.e("Check Employee Permission: Out of Range 2xx", errorBody)
                    Result.Error(NetworkError.UNKNOWN)
                }
            }
        } catch (e: Exception) {
            Log.e("Check Employee Permission Exception:", e.message ?: "Unknown")
            Result.Error(NetworkError.UNKNOWN)
        }

}