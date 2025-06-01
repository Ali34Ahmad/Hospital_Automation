package com.example.network.remote.child

import android.util.Log
import com.example.network.model.dto.child.ChildDto
import com.example.network.model.request.child.AddChildRequest
import com.example.network.model.response.NetworkMessage
import com.example.network.model.response.child.AddChildResponse
import com.example.network.model.response.child.ChildFullResponse
import com.example.network.model.response.child.GetChildrenAddedByEmployeeResponse
import com.example.network.model.response.child.GetChildrenByGuardianIdResponse
import com.example.network.model.response.child.GetChildrenByNameResponse
import com.example.network.model.response.child.UploadCertificatedResponse
import com.example.network.utility.ApiRoutes
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.http.headers
import io.ktor.http.parameters
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException
import java.io.File


private const val TAG = "child_api_service"
internal class ChildApiServiceImpl(
    private val client: HttpClient
): ChildApiService {

    override suspend fun getChildProfile(
        token: String,
        id: Int,
    ): Result<ChildFullResponse, NetworkError> {
        val response = try {
            Log.d(TAG,"start fetching data")
            client.get(ApiRoutes.CHILD_BY_ID) {
                bearerAuth(token)
                parameter("id", id)
            }

        } catch (e: Exception){
            Log.e(TAG, "getChildProfile: ${e.message}")
            return Result.Error<NetworkError>(NetworkError.UNKNOWN)
        }
        return when(response.status) {
            HttpStatusCode.OK -> {
                Log.d(TAG,"success")
                val profile: ChildFullResponse = response.body()
                Result.Success<ChildFullResponse>(profile)
            }
            HttpStatusCode.BadRequest -> {
                val body: NetworkMessage = response.body()
                Result.Error<NetworkError>(
                    if(body.message.contains("Wrong Id!",ignoreCase = true))
                        NetworkError.WRONG_ID
                    else
                        NetworkError.UNKNOWN
                )
            }
            else -> {
                Result.Error<NetworkError>(NetworkError.UNKNOWN)
            }
        }
    }

    override suspend fun getChildrenByGuardianId(
        token: String,
        guardianId: Int,
    ): Result<GetChildrenByGuardianIdResponse, NetworkError> {
        val response = try {
            client.get(ApiRoutes.CHILDREN_BY_GUARDIAN_ID + "/$guardianId") {
                bearerAuth(token)
            }
        }catch (e: Exception){
            Log.e(TAG, "getChildrenByGuardianId: ${e.message}")
            return Result.Error<NetworkError>(NetworkError.UNKNOWN)
        }
        return when(response.status){
            HttpStatusCode.OK -> {
                val data: GetChildrenByGuardianIdResponse = response.body()
                Result.Success<GetChildrenByGuardianIdResponse>(data)
            }
            else -> {
                Log.e(TAG, "getChildrenByGuardianId: ${response.status.description}")
                Result.Error<NetworkError>(NetworkError.UNKNOWN)
            }
        }
    }

    /**
     * get children added by employee id
     * page by page.
     * @author Ali Mansoura
     */
    override suspend fun getChildrenAddedByEmployee(
        token: String,
        page: Int,
        limit: Int
    ): Result<GetChildrenAddedByEmployeeResponse, NetworkError> {
        try {
            val response = client.get(ApiRoutes.CHILDREN_BY_EMPLOYEE_ID) {

                bearerAuth(token)

                parameter("page",page)
                parameter("limit",limit)
            }
            return when(response.status){
                HttpStatusCode.OK -> {
                    Log.d(TAG,"Success")
                    val data: GetChildrenAddedByEmployeeResponse = response.body()
                    Result.Success<GetChildrenAddedByEmployeeResponse>(data)
                }
                else ->{
                    Log.e(TAG,"error: ${response.status.description}")
                    Result.Error<NetworkError>(NetworkError.UNKNOWN)
                }
            }
        }catch (e: Exception){
            Log.e(TAG,"error: ${e.message}")
            return Result.Error<NetworkError>(NetworkError.UNKNOWN)
        }
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
    ): Result<GetChildrenByNameResponse, NetworkError>  {
        val response = try {
            client.get(ApiRoutes.CHILDREN_BY_NAME) {
                //query parameters
                parameters {
                    append("page", page.toString())
                    append("limit", limit.toString())
                    append("name",name)
                }
                //auth token
                bearerAuth(token)

            }
        } catch (e: Exception){
            Log.e(TAG, "uploadChildCertificate: ${e.message}")
            return Result.Error<NetworkError>(NetworkError.UNKNOWN)
        }
        return when(response.status) {
            HttpStatusCode.OK -> {
                val children: GetChildrenByNameResponse = response.body()
                Result.Success<GetChildrenByNameResponse>(children)
            }
            else -> {
                Result.Error<NetworkError>(NetworkError.UNKNOWN)
            }
        }
    }


    /**
     * add the child essential info to the database.
     * @param guardianId the employee who add the child
     * @author Ali Mansoura
     */
    override suspend fun addChild(
        token: String,
        guardianId: Int,
        child: AddChildRequest
    ) : Result<AddChildResponse, NetworkError>{

        val response = try {
            Log.d(TAG, "add child: fetching data")
            client.post(ApiRoutes.ADD_CHILD+"/$guardianId"){
                contentType(ContentType.Application.Json)
                bearerAuth(token)
                setBody(child)
            }
        }catch (e: Exception){
            Log.e(TAG, "add child: ${e.message}")
             return Result.Error<NetworkError>(error = NetworkError.UNKNOWN)
        }
        when(response.status){
            HttpStatusCode.OK ->{
                val body : AddChildResponse = response.body()
                return Result.Success<AddChildResponse>(data = body)
            }
            else -> {
                Log.e(TAG, "add child: unknown error")
                return Result.Error<NetworkError>(error = NetworkError.UNKNOWN)
            }
        }
    }
    override suspend fun uploadChildCertificate(
        token: String,
        id: String,
        image: File,
    ): Result<UploadCertificatedResponse, NetworkError> {
        val response = try {
            client.post(ApiRoutes.UPLOAD_CHILD_CERTIFICATE+"/$id") {
                headers {
                    append(HttpHeaders.Authorization, "Bearer $token")
                    append(HttpHeaders.ContentType, ContentType.Application.Pdf.contentType)
                }

                setBody(
                    MultiPartFormDataContent(
                        formData {
                            append(
                                key = "image",
                                value = image.readBytes(),
                                headers = Headers.build {
                                    append(HttpHeaders.ContentDisposition, "filename=\"${image.name}\"")
                                }
                            )
                        }
                    )
                )
            }

        } catch(e: UnresolvedAddressException) {
            Log.e(TAG, "uploadChildCertificate: ${e.message}")
            return Result.Error(NetworkError.NO_INTERNET)
        } catch(e: SerializationException) {
            Log.e(TAG, "uploadChildCertificate: ${e.message}")
            return Result.Error(NetworkError.SERIALIZATION)
        }
        catch (e: Exception){
            Log.e(TAG, "uploadChildCertificate: ${e.message}")
            return Result.Error<NetworkError>(error = NetworkError.UNKNOWN)
        }

        return  when(response.status){
            HttpStatusCode.OK ->{
                val body: UploadCertificatedResponse  = response.body()
                Result.Success<UploadCertificatedResponse>(body)
            }
            HttpStatusCode.UnprocessableEntity ->{
                Result.Error<NetworkError>( error = NetworkError.UNPROCESSABLE_ENTITY)
            }
            else -> {
                Result.Error<NetworkError>( error = NetworkError.UNKNOWN)
            }
        }
    }

    /**
     * search for users added by employee
     * @param name :It filters the result by child's name
     * @author Ali Mansoura.
     */
    override suspend fun searchForChildrenAddedByEmployee(
        token: String,
        name: String,
        page: Int,
        limit: Int
    ): Result<GetChildrenByNameResponse, NetworkError> {
        val response = try {
            client.get(ApiRoutes.SEARCH_FOR_CHILDREN_ADDED_BY_EMPLOYEE_BY_NAME) {
                parameters {
                    append("name",name)
                    append("page",page.toString())
                    append("limit",limit.toString())
                }
                bearerAuth(token)
            }

        }catch (e: Exception){
            Log.e(TAG,"msg : ${e.message}, cause: ${e.cause?.message}")
            return Result.Error<NetworkError>(NetworkError.UNKNOWN)
        }
        return when(response.status){
            HttpStatusCode.OK ->{
                Log.d(TAG,"success!")
                val body : GetChildrenByNameResponse= response.body()
                Result.Success<GetChildrenByNameResponse>(body)
            }
            HttpStatusCode.UnprocessableEntity ->{
                Log.d(TAG,"Error description: ${response.status.description}")
                    Result.Error<NetworkError>( error = NetworkError.UNPROCESSABLE_ENTITY)
                }
            else -> {
                Log.d(TAG,"Error description: ${response.status.description}")
                Result.Error<NetworkError>( error = NetworkError.UNKNOWN)
            }
        }
    }
}
