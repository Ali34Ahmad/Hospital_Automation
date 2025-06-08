package com.example.network.remote.employment_history

import android.util.Log
import com.example.datastore.repositories.UserPreferencesRepository
import com.example.network.model.response.NetworkMessage
import com.example.network.model.response.EmploymentHistoryResponseDto
import com.example.network.utility.ApiRoutes
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import com.example.utility.network.rootError
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.flow.first

class EmploymentHistoryApiServiceImpl(
    private val client: HttpClient,
    private val userPreferencesRepository: UserPreferencesRepository,
) : EmploymentHistoryApiService {
    override suspend fun getEmploymentHistory(): Result<EmploymentHistoryResponseDto, rootError> = try {
        val response = client.get(ApiRoutes.EMPLOYMENT_HISTORY) {
            contentType(ContentType.Application.Json)
            val token = userPreferencesRepository.userPreferencesFlow.first().token
            if (token == null) return@get
            bearerAuth(token)
        }
        when (response.status.value) {
            in 200..299 -> {
                val employmentHistory: EmploymentHistoryResponseDto = response.body()
                val bodyText: String = response.bodyAsText()
                Log.v("EmploymentHistoryApi:In Range 2xx", "$employmentHistory")
                Log.v("EmploymentHistoryApi:In Range 2xx", "Body: $bodyText")
                Result.Success(data = employmentHistory)
            }

            else -> {
                val errorMessage: NetworkMessage = response.body()
                Log.e("EmploymentHistoryApi:Out of Range 2xx", errorMessage.message)
                Result.Error(NetworkError.UNKNOWN)
            }
        }
    } catch (e: Exception) {
        Log.e("EmploymentHistoryApi:Exception", e.message?:"Unknown")
        Result.Error(NetworkError.UNKNOWN)
    }
}