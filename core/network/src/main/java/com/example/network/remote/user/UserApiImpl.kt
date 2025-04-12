package com.example.network.remote.user

import com.example.network.model.request.NetworkFullName
import com.example.network.model.response.NetworkMessage
import com.example.network.model.response.NetworkUser
import com.example.network.utility.ApiRoutes
import com.example.network.utility.Resource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType

class UserApiImpl(
    private val client: HttpClient
) : UserApiService{
    override suspend fun getUserProfile(token: String, fullName: NetworkFullName): Resource<NetworkUser> =
        try {
            val response = client.get(ApiRoutes.SHOW_USER_PROFILE){
                contentType(ContentType.Application.Json)
                header("Authorization", token)
                setBody(fullName)
            }
            when(response.status){
                HttpStatusCode.OK -> {
                    val user: NetworkUser = response.body()
                    Resource.Success<NetworkUser>(data = user)
                }
                HttpStatusCode.BadRequest -> {
                    val message: NetworkMessage = response.body()
                    Resource.Error<NetworkUser>(message = message.message)
                }
                else -> {
                    Resource.Error<NetworkUser>(message = "unexpected error")
                }
            }
        }catch (e: Exception){
            Resource.Error<NetworkUser>(message = e.message?: "unknown error")
        }
    }