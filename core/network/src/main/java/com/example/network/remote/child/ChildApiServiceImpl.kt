package com.example.network.remote.child

import com.example.network.model.request.AddChildRequest
import com.example.network.model.request.NetworkFullName
import com.example.network.model.response.ChildrenResponse
import com.example.network.model.response.NetworkMessage
import com.example.network.model.response.ShowChildProfileResponse
import com.example.network.utility.ApiRoutes
import com.example.network.utility.Resource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.http.parameters
import kotlinx.serialization.json.buildJsonObject
import java.io.File

internal class ChildApiServiceImpl(
    private val client: HttpClient
): ChildApiService {

    override suspend fun showChildProfile(
        token: String,
        fullName: NetworkFullName,
    ): Resource<ShowChildProfileResponse> = try {
            val response: HttpResponse = client.get(ApiRoutes.SHOW_CHILD_PROFILE) {
                contentType(ContentType.Application.Json)
                header(HttpHeaders.Authorization, "Bearer $token")
                setBody(fullName)
            }
            when(response.status) {
                HttpStatusCode.OK -> {
                    val profile: ShowChildProfileResponse = response.body()
                    Resource.Success<ShowChildProfileResponse>(data = profile)
                }
                HttpStatusCode.BadRequest -> {
                    val message: NetworkMessage = response.body()
                    Resource.Error<ShowChildProfileResponse>(message = message.message)
                }
                else -> {
                    Resource.Error<ShowChildProfileResponse>(message = "unexpected error")
                }
            }
            } catch (e: Exception){
            Resource.Error(message = e.message?: "unknown error")
        }


    /**
     * get children by name
     * need search for using mapOf function
     */
    override suspend fun getChildrenByName(
        page: Int,
        limit: Int,
        token: String,
        name: String,
    ): Resource<ChildrenResponse>  =
        try {
            val response: HttpResponse = client.get(ApiRoutes.SEARCH_FOR_CHILD) {
                contentType(ContentType.Application.Json)

                parameter("page",page)
                parameter("limit",limit)

                header(HttpHeaders.Authorization, "Bearer $token")

                setBody(mapOf("name" to name))

            }
            when(response.status) {
                HttpStatusCode.OK -> {
                    val children: ChildrenResponse = response.body()
                    Resource.Success<ChildrenResponse>(data = children)
                }
                else -> {
                    Resource.Error<ChildrenResponse>(message = "unexpected error")
                }
            }
        } catch (e: Exception){
            Resource.Error(message = e.message?: "unknown error")
        }

    override suspend fun addChild(token: String, child: AddChildRequest) : Resource<NetworkMessage> =
        try {
            val response = client.post(ApiRoutes.ADD_CHILD){
                contentType(ContentType.Application.Json)

                header(HttpHeaders.Authorization,"Bearer $token")
                setBody(child)
            }

            val message: NetworkMessage = response.body()
            Resource.Success<NetworkMessage>(message)

        }catch (e: Exception){
            Resource.Error<NetworkMessage>(message = e.message?: "unknown error")
        }

    override suspend fun uploadChildCertificate(
        token: String,
        id: String,
        image: File,
    ): Resource<NetworkMessage> =
        try {
            val response: HttpResponse = client.post(ApiRoutes.UPLOAD_CHILD_CERTIFICATE) {
                header(HttpHeaders.Authorization, "Bearer $token")
                MultiPartFormDataContent(
                    formData {
                        append("id", id)
                        append(
                            key = "image",
                            value = image.readBytes(),
                            headers = Headers.build {
                                append(HttpHeaders.ContentDisposition, "filename=\"${image.name}\"")
                            }
                        )
                    }
                )
            }
            when(response.status){
                HttpStatusCode.OK ->{
                    val message: NetworkMessage = response.body()
                    Resource.Success(message)
                }
                HttpStatusCode.UnprocessableEntity ->{
                    val message: NetworkMessage = response.body()
                    Resource.Error<NetworkMessage>(message.message)
                }
                else -> {
                    Resource.Error<NetworkMessage>(message = "unexpected error")
                }
            }
        }catch (e: Exception){
            Resource.Error<NetworkMessage>(message = e.message?: "unknown error")
        }


}
