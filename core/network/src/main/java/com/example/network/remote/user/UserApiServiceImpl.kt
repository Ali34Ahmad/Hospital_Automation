package com.example.network.remote.user

import android.util.Log
import com.example.network.model.response.NetworkMessage
import com.example.network.model.dto.user.UserFullDto
import com.example.network.model.response.relations.ChildGuardianRelationResponse
import com.example.network.model.response.user.GetGuardianByChildIdResponse
import com.example.network.model.response.user.GetGuardianByIdResponse
import com.example.network.model.response.user.GetUsersByNameResponse
import com.example.network.utility.ApiRoutes
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.http.parameters

private const val TAG = "UserApiServiceImpl"
internal class UserApiServiceImpl(
    private val client: HttpClient
) : UserApiService{
    override suspend fun getUserProfile(token: String,id: Int): Result<GetGuardianByIdResponse, NetworkError> =
        try {
            val response = client.get(ApiRoutes.SHOW_USER_PROFILE){
                contentType(ContentType.Application.Json)
                parameter("id",id)
                bearerAuth(token)
            }

                when(response.status){
                HttpStatusCode.OK -> {
                    val user: GetGuardianByIdResponse = response.body()
                    Result.Success<GetGuardianByIdResponse>(user)
                }
                HttpStatusCode.BadRequest -> {
                    Log.e("Get User Profile", "status: ${response.status.description}")
                    Result.Error<NetworkError>(NetworkError.BAD_REQUEST)
                }
                else -> {
                    Log.e("Get User Profile", "status: ${response.status.description}")
                    Result.Error<NetworkError>(NetworkError.UNKNOWN)
                }
            }
        }catch (e: Exception){
            Log.e("Get User Profile", "getUserProfile: ${e.message}")
            Result.Error<NetworkError>(NetworkError.UNKNOWN)
        }

    override suspend fun addGuardianToChild(
        token: String,
        childId: Int,
        userId: Int,
    ): Result<ChildGuardianRelationResponse, NetworkError> {
        val response = try {
            client.post(ApiRoutes.GUARDIAN_FOR_CHILD+"/$childId/$userId") {
                bearerAuth(token)
            }
        }catch (e: Exception){
            Log.e(TAG, "addChildForGuardian: ${e.message}" )
            return Result.Error<NetworkError>(NetworkError.UNKNOWN)
        }
        return when(response.status){
            HttpStatusCode.OK  -> {
                val body: ChildGuardianRelationResponse = response.body()
                Result.Success<ChildGuardianRelationResponse>(body)
            }
            HttpStatusCode.BadRequest -> {
                Log.e(TAG, "addChildForGuardian: ${response.status}" )
                val body : NetworkMessage = response.body<NetworkMessage>()
                val message = body.message
                Result.Error(
                    if (message.contains("This Guardian Is Existing In The Child Profile", ignoreCase = true))
                        NetworkError.GUARDIAN_ALREADY_ASSIGNED
                    else
                        NetworkError.UNKNOWN
                )
            }else -> {
                Log.e(TAG, "addChildForGuardian: ${response.status}" )
                Result.Error<NetworkError>(NetworkError.UNKNOWN)
            }
        }

    }

    override suspend fun getUsersByName(
        token: String,
        page: Int,
        limit: Int,
        name: String,
    ): Result<GetUsersByNameResponse, NetworkError> {
        val response = try {
            client.get(ApiRoutes.USERS_BY_NAME){
                parameters {
                    append("page", page.toString())
                    append("limit", limit.toString())
                    append("name",name)
                }

                bearerAuth(token)

            }
        }catch (e: Exception){
            Log.e(TAG, "getUsersByName: ${e.message}" )
            return Result.Error<NetworkError>(NetworkError.UNKNOWN)
        }
        return when(response.status){
            HttpStatusCode.OK -> {
                val body: GetUsersByNameResponse = response.body()
                Result.Success<GetUsersByNameResponse>(body)
            }
            else -> {
                Result.Error<NetworkError>(NetworkError.UNKNOWN)
            }
        }
    }

    override suspend fun getGuardianByChildId(
        token: String,
        childId: Int,
    ): Result<GetGuardianByChildIdResponse, NetworkError> {
        val response = try {
            client.post(ApiRoutes.GUARDIANS_BY_CHILD_ID+"/$childId") {
                bearerAuth(token)
            }
        }catch (e: Exception){
            Log.e(TAG, "getGuardianByChildId: ${e.message}" )
            return Result.Error<NetworkError>(NetworkError.UNKNOWN)
        }
        return when(response.status){
            HttpStatusCode.OK -> {
                val body: GetGuardianByChildIdResponse = response.body()
                Result.Success<GetGuardianByChildIdResponse>(body)
            }
            else -> {
                Result.Error<NetworkError>(NetworkError.UNKNOWN)
            }
        }
    }
}
