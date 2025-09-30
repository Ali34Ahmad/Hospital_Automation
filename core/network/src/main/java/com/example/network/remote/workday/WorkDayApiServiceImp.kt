package com.example.network.remote.workday

import com.example.network.model.dto.workday.WorkDaySummaryDto
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

private const val WORKDAY_TAG = "WorkdayApi"
class WorkDayApiServiceImp(
    private val client: HttpClient
): WorkDayApiService {
    override suspend fun createWorkDay(
        token: String,
        workday: WorkDaySummaryDto,
    ): Result<Unit, NetworkError> =
        doApiCall(
            tag = WORKDAY_TAG
        ){
            client.post(ApiRoutes.Doctor.WORK_DAY_CRUD) {
                bearerAuth(token)
                setBody(
                    formData {
                        append("request_type", "create")
                        append("request_objects", Json.encodeToString(listOf(workday)))
                    }
                )
            }
        }

    override suspend fun updateWorkDay(
        token: String,
        workday: WorkDaySummaryDto,
        id: Int,
    ): Result<Unit, NetworkError> =
        doApiCall(
            tag = WORKDAY_TAG
        ){
            client.post(ApiRoutes.Doctor.WORK_DAY_CRUD) {
                bearerAuth(token)
                setBody(
                    formData {
                        append("request_type", "update")
                        append("request_objects", Json.encodeToString(listOf(workday)))
                        append("ids", Json.encodeToString(listOf(id)))
                    }
                )
            }
        }

    override suspend fun deleteWorkDay(
        token: String,
        id: Int,
    ): Result<Unit, NetworkError> =
        doApiCall(
            tag = WORKDAY_TAG
        ){
            client.post(ApiRoutes.Doctor.WORK_DAY_CRUD) {
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