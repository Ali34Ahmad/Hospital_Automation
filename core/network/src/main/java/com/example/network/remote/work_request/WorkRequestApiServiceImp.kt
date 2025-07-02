package com.example.network.remote.work_request

import android.util.Log
import com.example.network.model.enums.RequestTypeDto
import com.example.network.model.request.work_request.WorkRequestRequest
import com.example.network.model.response.work_request.WorkRequestResponse
import com.example.network.utility.ApiRoutes
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
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
    ): Result<WorkRequestResponse, NetworkError> {
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
                val body: WorkRequestResponse = response.body()
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
}
