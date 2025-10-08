package com.example.network.remote.workday

import android.util.Log
import com.example.network.model.dto.workday.WorkDaySummaryDto
import com.example.network.utility.ApiRoutes
import com.example.network.utility.doApiCall
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitForm
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.content.PartData
import io.ktor.http.contentType
import io.ktor.http.headersOf
import io.ktor.http.parameters
import io.ktor.utils.io.InternalAPI
import kotlinx.serialization.json.Json

private const val WORKDAY_TAG = "WorkdayApi"
class WorkDayApiServiceImp(
    private val client: HttpClient
): WorkDayApiService {
    override suspend fun createWorkDay(
        token: String,
        workday: WorkDaySummaryDto,
    ): Result<Unit, NetworkError> =
        doApiCall(
            tag = "$WORKDAY_TAG : create workday"
        ){
            val requestObjectsJson = Json.encodeToString(
                mapOf("request_objects" to listOf(workday))
            )
            client.post(ApiRoutes.Doctor.WORK_DAY_CRUD) {
                bearerAuth(token)
                setBody(
                    MultiPartFormDataContent(
                        formData{
                            append("request_type", "create")
                            append("request_objects",
                                requestObjectsJson,
                                headers =  Headers.build {
                                    append(HttpHeaders.ContentType, "application/json")
                                }
                            )
                        }
                    )
                )
            }
        }

    override suspend fun updateWorkDay(
        token: String,
        workday: WorkDaySummaryDto,
        id: Int,
    ): Result<Unit, NetworkError> =
        doApiCall(
            tag = "$WORKDAY_TAG : update"
        ){
            val idsJson = Json.encodeToString(mapOf("ids" to listOf(id)))
            val requestObjectsJson = Json.encodeToString(mapOf("request_objects" to listOf(workday)))
            client.post(ApiRoutes.Doctor.WORK_DAY_CRUD) {
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

    @OptIn(InternalAPI::class)
    override suspend fun deleteWorkDay(
        token: String,
        id: Int,
    ): Result<Unit, NetworkError> =
        doApiCall(
            tag = "$WORKDAY_TAG: delete"
        ){
            val idsJson = Json.encodeToString(mapOf("ids" to listOf(id)))

            client.post(ApiRoutes.Doctor.WORK_DAY_CRUD) {
                bearerAuth(token)

                setBody(
                    MultiPartFormDataContent(
                        formData {
                            append("request_type", "delete")
                            append("ids", idsJson, Headers.build {
                                append(HttpHeaders.ContentType, "application/json")
                            })
                        }
                    )
                )
            }
        }
}