package com.example.network.remote.work_request

import android.util.Log
import com.example.network.model.enums.RequestStateDto
import com.example.network.model.enums.RequestStateWrapperDto
import com.example.network.model.enums.RequestTypeDto
import com.example.network.model.request.work_request.WorkRequestRequest
import com.example.network.model.response.work_request.ChangeRequestStateResponseDto
import com.example.network.model.response.work_request.GetRequestsResponseDto
import com.example.network.model.response.work_request.WorkRequestResponseDto
import com.example.network.utility.ApiRoutes
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType


const val WORK_REQUEST_TAG = "WorkRequestApi"

data class WorkRequestApiServiceImp(
    private val client: HttpClient
): WorkRequestApiService{
    override suspend fun sendWorkRequest(
        token: String,
        clinicId: Int,
        requestType: RequestTypeDto,
    ): Result<WorkRequestResponseDto, NetworkError> {
        val response = try {
            client.post(ApiRoutes.Doctor.SEND_WORK_REQUEST+"/$clinicId") {
                bearerAuth(token)
                contentType(ContentType.Application.Json)
                setBody(
                    WorkRequestRequest(
                        requestType = requestType
                    )
                )
            }
        }catch (e: Exception){
            Log.e(WORK_REQUEST_TAG, e.message.toString())
            return Result.Error(NetworkError.UNKNOWN)
        }
        return when(response.status){
            HttpStatusCode.OK -> {
                Log.d(WORK_REQUEST_TAG,"Success : ${response.bodyAsText()}")
                val body: WorkRequestResponseDto = response.body()
                Result.Success(body)
            }
            HttpStatusCode.Unauthorized -> {
                Log.e(WORK_REQUEST_TAG, "Unauthorized : ${response.status.description}")
                Result.Error(NetworkError.UNAUTHORIZED)
            }
            else -> {
                Log.e(WORK_REQUEST_TAG, "Unknown : ${response.bodyAsText()}")
                return Result.Error(NetworkError.UNKNOWN)
            }
        }
    }

    override suspend fun getRequests(
        token: String,
        page: Int,
        limit: Int
    ): Result<GetRequestsResponseDto, NetworkError> =
        try {
            val response = client.get(ApiRoutes.Admin.GET_EMPLOYMENT_REQUESTS) {
                url{
                    parameter("page",page)
                    parameter("limit",limit)
                }
                contentType(ContentType.Application.Json)
                bearerAuth(token)
            }
            when (response.status.value) {
                in 200..299 -> {
                    val responseText = response.bodyAsText()
                    Log.v("GetRequestsApi${response.status.value}", responseText)

                    val getRequestsResponse: GetRequestsResponseDto = response.body()
                    Log.v("GetRequestsApi: in of range 2xx", getRequestsResponse.data.toString())
                    Result.Success(data = getRequestsResponse)
                }

                else -> {
                    val errorBody = response.bodyAsText()
                    Log.e("GetRequestsApi: Out of range 2xx", errorBody)
                    Result.Error(NetworkError.UNKNOWN)
                }
            }
        }catch (e: Exception){
            Log.e("GetRequestsApi Exception:", e.localizedMessage ?: "Unknown Error")
            Result.Error(NetworkError.UNKNOWN)
        }

    override suspend fun changeWorkRequestState(
        token: String,
        requestId:Int,
        state: RequestStateDto
    ): Result<ChangeRequestStateResponseDto, NetworkError> = try {
            val response = client.post("${ApiRoutes.Admin.CHANGE_EMPLOYMENT_REQUEST_STATE}/$requestId") {
                contentType(ContentType.Application.Json)
                bearerAuth(token)
                setBody(RequestStateWrapperDto(state))
            }
            when (response.status.value) {
                in 200..299 -> {
                    val responseText = response.bodyAsText()
                    Log.v("ChangeRequestStateApi${response.status.value}", responseText)

                    val getRequestsResponse: ChangeRequestStateResponseDto = response.body()
                    Log.v("ChangeRequestStateApi: in of range 2xx", getRequestsResponse.updatedRequest.toString())
                    Result.Success(data = getRequestsResponse)
                }

                else -> {
                    val errorBody = response.bodyAsText()
                    Log.e("ChangeRequestStateApi: Out of range 2xx", errorBody)
                    Result.Error(NetworkError.UNKNOWN)
                }
            }
        }catch (e: Exception){
            Log.e("ChangeRequestStateApi Exception:", e.localizedMessage ?: "Unknown Error")
            Result.Error(NetworkError.UNKNOWN)
        }
}
