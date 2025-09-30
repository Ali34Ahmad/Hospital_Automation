package com.example.network.utility

import android.util.Log
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.client.statement.request
import io.ktor.http.HttpStatusCode

/**
 * Executes a safe and unified API call using the provided suspend HTTP request body.
 * This function handles common HTTP response statuses and returns a unified [Result] object.
 *
 * @param T The expected type of the successful response body.
 * @param tag The tag used for logging purposes.
 * @param body A suspend lambda that performs the actual HTTP request and returns a [HttpResponse].
 *
 * @author Ali Mansoura
 */
suspend inline fun <reified T> doApiCall(
    tag: String,
    body: suspend () -> HttpResponse,
): Result<T, NetworkError> =
    try {
        Log.d(tag, "Doing API call")
        val response = body()
        Log.d(tag, "url :${response.request.url}")
        when (response.status) {
            HttpStatusCode.OK -> {
                val data: T = response.body()
                Log.d(tag, "SUCCESS,\n\tbody: ${response.bodyAsText()}")
                Result.Success<T>(data)
            }

            HttpStatusCode.BadRequest -> {
                Log.e(tag, "BAD_REQUEST,\n\tbody: ${response.bodyAsText()}")
                Result.Error<NetworkError>(NetworkError.BAD_REQUEST)
            }

            HttpStatusCode.Unauthorized -> {
                Log.e(tag, "UNAUTHORIZED,\n\tbody:  ${response.bodyAsText()}")
                Result.Error<NetworkError>(NetworkError.UNAUTHORIZED)
            }

            else -> {
                Log.e(
                    tag,
                    "UNKNOWN,\n\tmessage = ${response.status.description},body ${response.bodyAsText()}"
                )
                Result.Error<NetworkError>(NetworkError.UNKNOWN)
            }
        }
    } catch (e: Exception) {
        Log.e(tag, "EXCEPTION,\n\tmessage: ${e.message}")
        Result.Error<NetworkError>(NetworkError.UNKNOWN)
    }

