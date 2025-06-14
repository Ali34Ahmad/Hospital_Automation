package com.example.network.remote.doctor

import android.util.Log
import com.example.network.model.response.doctor.appointments.ShowAppointmentsResponse
import com.example.network.utility.ApiRoutes
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode

private const val DOCTOR_API_TAG = "DoctorApi"
data class DoctorApiServiceImpl(
    val client: HttpClient,
) : DoctorApiService{
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
            client.get(ApiRoutes.SHOW_APPOINTMENTS){
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
            Log.e(DOCTOR_API_TAG, e.message.toString())
            return Result.Error(NetworkError.UNKNOWN)
        }
        return when(response.status){
            HttpStatusCode.OK -> {
                Log.d(DOCTOR_API_TAG,"Success : ${response.bodyAsText()}")
                val body: ShowAppointmentsResponse = response.body()
                Result.Success(body)
            }
            HttpStatusCode.Unauthorized -> {
                Log.e(DOCTOR_API_TAG, "Unauthorized : ${response.bodyAsText()}")
                Result.Error(NetworkError.UNAUTHORIZED)
            }
            else -> {
                Log.e(DOCTOR_API_TAG, "Unknown : ${response.bodyAsText()}")
                return Result.Error(NetworkError.UNKNOWN)
            }
        }
    }
}
