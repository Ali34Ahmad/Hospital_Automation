package com.example.network.remote.appointment_type

import com.example.network.model.dto.doctor.appointment.AppointmentTypeSummaryDto
import com.example.network.utility.ApiRoutes
import com.example.network.utility.doApiCall
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.forms.formData
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.serialization.json.Json

private const val APP_TYPE_TAG = "APPOINTMENT TYPE API"
class AppointmentTypeApiServiceImp(
    private val client: HttpClient
): AppointmentTypeApiService {
    override suspend fun createAppointmentType(
        token: String,
        appointmentType: AppointmentTypeSummaryDto,
    ): Result<Unit, NetworkError> =
        doApiCall(
            tag = APP_TYPE_TAG
        ){
            client.post(ApiRoutes.Doctor.APPOINTMENT_TYPE_CRUD) {
                bearerAuth(token)
                setBody(
                    formData {
                        append("request_type", "create")
                        append("request_objects", Json.encodeToString(listOf(appointmentType)))
                    }
                )
            }
        }

    override suspend fun updateAppointType(
        token: String,
        appointmentType: AppointmentTypeSummaryDto,
        id: Int,
    ): Result<Unit, NetworkError> =
        doApiCall(
            tag = APP_TYPE_TAG
        ){
            client.post(ApiRoutes.Doctor.APPOINTMENT_TYPE_CRUD) {
                bearerAuth(token)
                setBody(
                    formData {
                        append("request_type", "update")
                        append("request_objects", Json.encodeToString(listOf(appointmentType)))
                        append("ids", Json.encodeToString(listOf(id)))
                    }
                )
            }
        }

    override suspend fun deleteAppointmentType(
        token: String,
        id: Int,
    ): Result<Unit, NetworkError> =
        doApiCall(
            tag = APP_TYPE_TAG
        ){
            client.post(ApiRoutes.Doctor.APPOINTMENT_TYPE_CRUD) {
                bearerAuth(token)
                setBody(
                    formData {
                        append("request_type", "delete")
                        append("ids", Json.encodeToString(listOf(id)))
                    }
                )
            }
        }
}