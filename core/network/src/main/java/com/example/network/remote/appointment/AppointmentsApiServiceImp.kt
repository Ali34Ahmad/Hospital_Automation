package com.example.network.remote.appointment

import com.example.network.model.request.appointment.AddDiagnosisRequest
import com.example.network.model.response.appointments.AddDiagnosisResponse
import com.example.network.model.response.appointments.ShowAppointmentDetails
import com.example.network.model.response.appointments.ShowAppointmentsResponse
import com.example.network.model.response.appointments.UpdatedStateResponse
import com.example.network.utility.ApiRoutes
import com.example.network.utility.doApiCall
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
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
    ): Result<ShowAppointmentsResponse, NetworkError> =
        doApiCall<ShowAppointmentsResponse>(
            tag = APPOINTMENTS_API_TAG
        ) {
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
        }

    override suspend fun getAppointmentDetails(
        token: String,
        id: Int,
    ): Result<ShowAppointmentDetails, NetworkError>  =
        doApiCall<ShowAppointmentDetails>(
            tag = APPOINTMENTS_API_TAG
        ) {
            client.get(ApiRoutes.Doctor.SHOW_SINGLE_APPOINTMENT+"/$id"){
                bearerAuth(token)
            }
        }

    override suspend fun updateAppointmentStateToPassed(
        token: String,
        appointmentId: Int,
    ): Result<UpdatedStateResponse, NetworkError> =
        doApiCall<UpdatedStateResponse>(
            tag = APPOINTMENTS_API_TAG
        ) {
            client.post(ApiRoutes.Doctor.UPDATE_APPOINTMENT_STATE_TO_PASSED+"/$appointmentId") {
                bearerAuth(token)
            }
        }


    override suspend fun updateAppointmentStateToMissed(
        token: String,
        appointmentId: Int,
    ): Result<UpdatedStateResponse, NetworkError> =
        doApiCall<UpdatedStateResponse>(
            tag = APPOINTMENTS_API_TAG
        ) {
            client.post(ApiRoutes.Doctor.UPDATE_APPOINTMENT_STATE_TO_MISSED+"/$appointmentId") {
                bearerAuth(token)
            }
        }
    override suspend fun addDiagnosis(
        token: String,
        appointmentId: Int,
        diagnosis: String,
    ): Result<AddDiagnosisResponse, NetworkError> =
        doApiCall<AddDiagnosisResponse>(
            tag = APPOINTMENTS_API_TAG
        ) {
            client.post(ApiRoutes.Doctor.ADD_MEDICAL_DIAGNOSIS+"/$appointmentId") {
                bearerAuth(token)
                contentType(ContentType.Application.Json)
                setBody(
                    AddDiagnosisRequest(
                        diagnosis = diagnosis
                    )
                )
            }
        }
}
