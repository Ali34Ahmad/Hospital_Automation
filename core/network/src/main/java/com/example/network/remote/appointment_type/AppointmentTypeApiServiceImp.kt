package com.example.network.remote.appointment_type

import com.example.network.model.dto.appointment.AppointmentTypeSummaryDto
import com.example.network.utility.ApiRoutes
import com.example.network.utility.doApiCall
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
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
            tag = "$APP_TYPE_TAG : create"
        ){
            val requestObjectsJson = Json.encodeToString(
                mapOf("request_objects" to listOf(appointmentType))
            )
            client.post(ApiRoutes.Doctor.APPOINTMENT_TYPE_CRUD) {
                bearerAuth(token)
                setBody(
                    MultiPartFormDataContent(
                        formData {
                            append("request_type", "create")
                            append(
                                "request_objects", requestObjectsJson,
                                headers = Headers.build {
                                    append(HttpHeaders.ContentType, "application/json")
                                })
                        }
                    )
                )
            }
        }

    override suspend fun updateAppointType(
        token: String,
        appointmentType: AppointmentTypeSummaryDto,
        id: Int,
    ): Result<Unit, NetworkError> =
        doApiCall(
            tag = "$APP_TYPE_TAG: update"
        ){
            val idsJson = Json.encodeToString(mapOf("ids" to listOf(id)))
            val requestObjectsJson = Json.encodeToString(mapOf("request_objects" to listOf(appointmentType)))

            client.post(ApiRoutes.Doctor.APPOINTMENT_TYPE_CRUD) {
                bearerAuth(token)
                setBody(
                   MultiPartFormDataContent(
                        formData {
                            append("request_type", "update")
                            append("request_objects", requestObjectsJson,
                                Headers.build {
                                    append(HttpHeaders.ContentType, "application/json")
                                })
                            append("ids", idsJson,Headers.build {
                                append(HttpHeaders.ContentType, "application/json")
                            })
                        }
                    )
                )
            }
        }

    override suspend fun deleteAppointmentType(
        token: String,
        id: Int,
    ): Result<Unit, NetworkError> =
        doApiCall(
            tag = "$APP_TYPE_TAG: delete"
        ){
            val idsJson = Json.encodeToString(mapOf("ids" to listOf(id)))

            client.post(ApiRoutes.Doctor.APPOINTMENT_TYPE_CRUD) {
                bearerAuth(token)
                setBody(
                    MultiPartFormDataContent(
                        formData {
                            append("request_type", "delete")
                            append("ids", idsJson, Headers.build {
                                append(HttpHeaders.ContentType, "application/json")
                            })                        }
                    )
                )
            }
        }
}