package com.example.network.remote.child

import com.example.network.model.enums.RoleDto
import com.example.network.model.request.child.AddChildRequest
import com.example.network.model.response.child.AddChildResponse
import com.example.network.model.response.child.ChildFullResponse
import com.example.network.model.response.child.GetChildrenAddedByEmployeeResponse
import com.example.network.model.response.child.GetChildrenByGuardianIdResponse
import com.example.network.model.response.child.GetChildrenByNameResponse
import com.example.network.model.response.child.UploadCertificatedResponse
import com.example.network.utility.ApiRoutes
import com.example.network.utility.doApiCall
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import io.ktor.client.HttpClient
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
import io.ktor.http.contentType
import io.ktor.http.headers
import java.io.File


private const val CHILD_API_TAG = "child_api_service"
internal class ChildApiServiceImpl(
    private val client: HttpClient
): ChildApiService {

    override suspend fun getChildProfile(
        token: String,
        id: Int,
        roleDto: RoleDto
    ): Result<ChildFullResponse, NetworkError> =
        doApiCall<ChildFullResponse>(
            tag = CHILD_API_TAG
        ) {
            client.get(ApiRoutes.getChildProfileByIdEndPoint(role = roleDto)) {
                bearerAuth(token)
                parameter("id", id)
            }
        }

    override suspend fun getChildrenByGuardianId(
        token: String,
        guardianId: Int,
    ): Result<GetChildrenByGuardianIdResponse, NetworkError> =
        doApiCall<GetChildrenByGuardianIdResponse>(
            tag = CHILD_API_TAG
        ) {
            client.get(ApiRoutes.CHILDREN_BY_GUARDIAN_ID + "/$guardianId") {
                bearerAuth(token)
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
    ): Result<GetChildrenAddedByEmployeeResponse, NetworkError> =
        doApiCall<GetChildrenAddedByEmployeeResponse>(
            tag = CHILD_API_TAG
        ) {
            client.get(ApiRoutes.CHILDREN_BY_EMPLOYEE_ID) {

                bearerAuth(token)

                parameter("page",page)
                parameter("limit",limit)
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
    ): Result<GetChildrenByNameResponse, NetworkError>  =
        doApiCall<GetChildrenByNameResponse>(
            tag = CHILD_API_TAG
        ) {
            client.get(ApiRoutes.CHILDREN_BY_NAME) {
            //query parameters
            parameter("name",name)
            parameter("page",page)
            parameter("limit",limit)
            //auth token
            bearerAuth(token)
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
    ) : Result<AddChildResponse, NetworkError> =
        doApiCall<AddChildResponse>(
            tag = CHILD_API_TAG
        ) {
            client.post(ApiRoutes.ADD_CHILD+"/$guardianId"){
            contentType(ContentType.Application.Json)
            bearerAuth(token)
            setBody(child)
            }
        }
    override suspend fun uploadChildCertificate(
        token: String,
        id: String,
        image: File,
    ): Result<UploadCertificatedResponse, NetworkError> =
        doApiCall<UploadCertificatedResponse>(
            tag = CHILD_API_TAG
        ) {
            client.post(ApiRoutes.UPLOAD_CHILD_CERTIFICATE+"/$id") {
                headers {
                    bearerAuth(token)
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
    ): Result<GetChildrenByNameResponse, NetworkError> =
        doApiCall<GetChildrenByNameResponse>(
            tag = CHILD_API_TAG
        ) {
            client.get(ApiRoutes.SEARCH_FOR_CHILDREN_ADDED_BY_EMPLOYEE_BY_NAME) {
                bearerAuth(token)
                parameter("name",name)
                parameter("page",page)
                parameter("limit",limit)
            }
        }

}
