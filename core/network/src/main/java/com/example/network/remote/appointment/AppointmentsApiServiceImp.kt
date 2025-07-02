package com.example.network.remote.appointment

import android.util.Log
import com.example.network.model.request.appointment.AddDiagnosisRequest
import com.example.network.model.response.appointments.AddDiagnosisResponse
import com.example.network.model.response.appointments.ShowAppointmentDetails
import com.example.network.model.response.appointments.ShowAppointmentsResponse
import com.example.network.model.response.appointments.UpdatedStateResponse
import com.example.network.utility.ApiRoutes
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType

private const val APPOINTMENTS_API_TAG = "AppointmentsApi"
data class AppointmentsApiServiceImp(
    val client: HttpClient,
) : AppointmentsApiService {
    override suspend fun showAppointments(
        token: String,
        params: String,
        page: Int,
        limit: Int,
        sort: String,
        dateFilter: String?,
        queryFilter: String?
    ): Result<ShowAppointmentsResponse, NetworkError> {
        val response = try {
            client.get(ApiRoutes.Doctor.SHOW_APPOINTMENTS){
                bearerAuth(token)
                url {
                    parameters.append("params", params)
                    parameters.append("page", page.toString())
                    parameters.append("limit", limit.toString())
                    parameters.append("dateOrder",sort)
                    if(!dateFilter.isNullOrBlank()){
                        parameters.append("dateFilter",dateFilter)
                    }
                    if (!queryFilter.isNullOrBlank()){
                        parameters.append("appointment_type",queryFilter)
                    }
                }
            }
        }catch (e: Exception){
            Log.e(APPOINTMENTS_API_TAG, e.message.toString())
            return Result.Error(NetworkError.UNKNOWN)
        }
        return when(response.status){
            HttpStatusCode.OK -> {
                Log.d(APPOINTMENTS_API_TAG,"param:$params,page:$page,limit:$limit,query:$queryFilter,sort:$sort")
                Log.d(APPOINTMENTS_API_TAG,"Success : ${response.bodyAsText()}")
                val body: ShowAppointmentsResponse = response.body()
                Result.Success(body)
            }
            HttpStatusCode.Unauthorized -> {
                Log.e(APPOINTMENTS_API_TAG, "Unauthorized : ${response.status.description}")
                Result.Error(NetworkError.UNAUTHORIZED)
            }
            else -> {
                Log.e(APPOINTMENTS_API_TAG, "Unknown : ${response.bodyAsText()}")
                return Result.Error(NetworkError.UNKNOWN)
            }
        }
    }

    override suspend fun getAppointmentDetails(
        token: String,
        id: Int,
    ): Result<ShowAppointmentDetails, NetworkError> {
        val response = try {
            client.get(ApiRoutes.Doctor.SHOW_SINGLE_APPOINTMENT+"/$id"){
                bearerAuth(token)
            }
        }catch (e: Exception){
            Log.e(APPOINTMENTS_API_TAG,"error : ${e.message}")
            return Result.Error(NetworkError.UNKNOWN)
        }
        return when(response.status){
            HttpStatusCode.OK -> {
                Log.d(APPOINTMENTS_API_TAG,"Success : ${response.bodyAsText()}")
                val body: ShowAppointmentDetails = response.body()
                Result.Success(body)
            }
            HttpStatusCode.Unauthorized -> {
                Log.e(APPOINTMENTS_API_TAG, "Unauthorized : ${response.bodyAsText()}")
                Result.Error(NetworkError.UNAUTHORIZED)
            }
            else -> {
                Log.e(APPOINTMENTS_API_TAG, "Unknown : ${response.bodyAsText()}")
                return Result.Error(NetworkError.UNKNOWN)
            }
        }
    }

    override suspend fun updateAppointmentStateToPassed(
        token: String,
        appointmentId: Int,
    ): Result<UpdatedStateResponse, NetworkError> {
        val response = try {
            client.post(ApiRoutes.Doctor.UPDATE_APPOINTMENT_STATE_TO_PASSED+"/$appointmentId") {
                bearerAuth(token)
            }
        }catch (e: Exception){
            Log.e(APPOINTMENTS_API_TAG,"error : ${e.message}")
            return Result.Error(NetworkError.UNKNOWN)
        }
        return when(response.status){
            HttpStatusCode.OK -> {
                Log.d(APPOINTMENTS_API_TAG,"Success : ${response.bodyAsText()}")
                val body: UpdatedStateResponse = response.body()
                Result.Success(body)
            }
            HttpStatusCode.Unauthorized -> {
                Log.e(APPOINTMENTS_API_TAG, "Unauthorized : ${response.bodyAsText()}")
                Result.Error(NetworkError.UNAUTHORIZED)
            }
            else -> {
                Log.e(APPOINTMENTS_API_TAG, "Unknown : ${response.bodyAsText()}")
                return Result.Error(NetworkError.UNKNOWN)
            }
        }
    }

    override suspend fun updateAppointmentStateToMissed(
        token: String,
        appointmentId: Int,
    ): Result<UpdatedStateResponse, NetworkError> {
        val response = try {
            client.post(ApiRoutes.Doctor.UPDATE_APPOINTMENT_STATE_TO_MISSED+"/$appointmentId") {
                bearerAuth(token)
            }
        }catch (e: Exception){
            Log.e(APPOINTMENTS_API_TAG,"error : ${e.message}")
            return Result.Error(NetworkError.UNKNOWN)
        }
        return when(response.status){
            HttpStatusCode.OK -> {
                Log.d(APPOINTMENTS_API_TAG,"Success : ${response.bodyAsText()}")
                val body: UpdatedStateResponse = response.body()
                Result.Success(body)
            }
            HttpStatusCode.Unauthorized -> {
                Log.e(APPOINTMENTS_API_TAG, "Unauthorized : ${response.bodyAsText()}")
                Result.Error(NetworkError.UNAUTHORIZED)
            }
            else -> {
                Log.e(APPOINTMENTS_API_TAG, "Unknown : ${response.bodyAsText()}")
                return Result.Error(NetworkError.UNKNOWN)
            }
        }

    }

    override suspend fun addDiagnosis(
        token: String,
        appointmentId: Int,
        diagnosis: String,
    ): Result<AddDiagnosisResponse, NetworkError> {
        val response = try{
            client.post(ApiRoutes.Doctor.SHOW_SINGLE_APPOINTMENT+"/$appointmentId") {
                bearerAuth(token)
                contentType(ContentType.Application.Json)

                setBody(
                    AddDiagnosisRequest(
                        diagnosis = diagnosis
                    )
                )
            }
        }catch (e: Exception){
            Log.e(APPOINTMENTS_API_TAG,"error : ${e.message}")
            return Result.Error(NetworkError.UNKNOWN)
        }
        return when(response.status){
            HttpStatusCode.OK -> {
                Log.d(APPOINTMENTS_API_TAG,"Success : ${response.bodyAsText()}")
                val body: AddDiagnosisResponse = response.body()
                Result.Success(body)
            }
            HttpStatusCode.Unauthorized -> {
                Log.e(APPOINTMENTS_API_TAG, "Unauthorized : ${response.status.description}")
                Result.Error(NetworkError.UNAUTHORIZED)
            }
            else -> {
                Log.e(APPOINTMENTS_API_TAG, "Unknown : ${response.status.description}")
                return Result.Error(NetworkError.UNKNOWN)
            }
        }
    }
}
