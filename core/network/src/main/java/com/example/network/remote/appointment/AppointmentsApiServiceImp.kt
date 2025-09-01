package com.example.network.remote.appointment

import android.util.Log
import com.example.network.model.enums.RoleDto
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
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

private const val APPOINTMENTS_API_TAG = "AppointmentsApi"
data class AppointmentsApiServiceImp(
    val client: HttpClient,
) : AppointmentsApiService {
    override suspend fun showDoctorAppointments(
        token: String,
        params: String,
        page: Int,
        limit: Int,
        sort: String,
        dateFilter: String?,
        queryFilter: String?,
        doctorId: Int?,
        roleDto: RoleDto,
    ): Result<ShowAppointmentsResponse, NetworkError> =
        doApiCall<ShowAppointmentsResponse>(
            tag = APPOINTMENTS_API_TAG
        ) {
            Log.d(APPOINTMENTS_API_TAG,"showDoctorAppointments")
            val url = ApiRoutes.getDoctorAppointmentsEndPointFor(roleDto).let { base->
                if(roleDto == RoleDto.ADMIN) "$base/$doctorId"
                else base
            }
            client.get(url){
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

    override suspend fun showUserAppointments(
        token: String,
        page: Int,
        limit: Int,
        userId: Int,
        state: String,
        sort: String,
        dateFilter: String?,
        queryFilter: String?,
    ): Result<ShowAppointmentsResponse, NetworkError> =
        doApiCall(
            tag = APPOINTMENTS_API_TAG
        ){
            Log.d(APPOINTMENTS_API_TAG,"showUserAppointments")

            client.get(ApiRoutes.Admin.USER_APPOINTMENTS) {

                parameter("page",page)
                parameter("limit",limit)
                parameter("state",state)
                parameter("id",userId)
                parameter("dateOrder",sort)
                parameter("forWho","user")
                if(!dateFilter.isNullOrBlank()){
                    parameter("dateFilter",dateFilter)
                }
                if (!queryFilter.isNullOrBlank()){
                    parameter("appointmentType",queryFilter)
                }

                bearerAuth(token)
            }
        }

    override suspend fun showChildAppointments(
        token: String,
        page: Int,
        limit: Int,
        childId: Int,
        state: String,
        sort: String,
        dateFilter: String?,
        queryFilter: String?,
    ): Result<ShowAppointmentsResponse, NetworkError> =
        doApiCall(
            tag = APPOINTMENTS_API_TAG
        ){
            Log.d(APPOINTMENTS_API_TAG,"showChildAppointments")
            client.get(ApiRoutes.Admin.CHILD_APPOINTMENTS) {

                parameter("page",page)
                parameter("limit",limit)
                parameter("state",state)
                parameter("id",childId)
                parameter("dateOrder",sort)
                parameter("forWho","child")
                if(!dateFilter.isNullOrBlank()){
                    parameter("dateFilter",dateFilter)
                }
                if (!queryFilter.isNullOrBlank()){
                    parameter("appointmentType",queryFilter)
                }
                bearerAuth(token)
            }
        }

    override suspend fun getAppointmentDetails(
        token: String,
        id: Int,
        roleDto: RoleDto
    ): Result<ShowAppointmentDetails, NetworkError>  =
        doApiCall<ShowAppointmentDetails>(
            tag = APPOINTMENTS_API_TAG
        ) {
            val url = ApiRoutes.getAppointmentByIdEndPointFor(roleDto).let { base ->
                if (roleDto == RoleDto.DOCTOR) "$base/$id" else base
            }

            client.get(url){
                if(roleDto == RoleDto.ADMIN){
                    parameter("type","appointment")
                    parameter("id",id)
                }
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
