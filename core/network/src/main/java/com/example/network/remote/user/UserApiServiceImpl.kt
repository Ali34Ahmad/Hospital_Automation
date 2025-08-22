package com.example.network.remote.user

import com.example.network.model.enums.RoleDto
import com.example.network.model.request.user.DeactivateUserRequest
import com.example.network.model.response.UpdatedData
import com.example.network.model.response.relations.ChildGuardianRelationResponse
import com.example.network.model.response.user.GetGuardianByChildIdResponse
import com.example.network.model.response.user.GetGuardianByIdResponse
import com.example.network.model.response.user.GetUsersByNameResponse
import com.example.network.utility.ApiRoutes
import com.example.network.utility.doApiCall
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

private const val TAG = "UserApiServiceImpl"
internal class UserApiServiceImpl(
    private val client: HttpClient
) : UserApiService{
    override suspend fun getUserProfile(
        token: String,
        id: Int,
        roleDto: RoleDto,
    ): Result<GetGuardianByIdResponse, NetworkError> =
        doApiCall(
            tag = TAG
        ) {
            client.get(ApiRoutes.getGuardianByIdEndPointForRole(roleDto)){
                contentType(ContentType.Application.Json)
                parameter("id",id)
                if (roleDto== RoleDto.ADMIN){
                    parameter("type","patient")
                }
                bearerAuth(token)
            }
        }

    override suspend fun addGuardianToChild(
        token: String,
        childId: Int,
        userId: Int,
    ): Result<ChildGuardianRelationResponse, NetworkError> =
        doApiCall(
            tag = TAG
        ) {
            client.post(ApiRoutes.GUARDIAN_FOR_CHILD+"/$childId/$userId") {
                bearerAuth(token)
            }
        }

    override suspend fun getUsersByName(
        token: String,
        page: Int,
        limit: Int,
        name: String,
    ): Result<GetUsersByNameResponse, NetworkError> =
        doApiCall(
            tag = TAG
        ) {
            client.get(ApiRoutes.USERS_BY_NAME){
                parameter("name",name)
                parameter("page",page)
                parameter("limit",limit)
                bearerAuth(token)

            }
        }

    override suspend fun deactivateUser(
        token: String,
        userId: Int,
        deactivationReason: String,
    ): Result<UpdatedData, NetworkError> =
        doApiCall(
            tag = TAG
        ) {
            client.post(ApiRoutes.Admin.DEACTIVATE_USER+"/$userId") {
                contentType(ContentType.Application.Json)
                setBody(
                    DeactivateUserRequest(
                        state = "suspend",
                        suspendingReason = deactivationReason
                    )
                )
                bearerAuth(token)
            }
        }

    override suspend fun reactivateUser(
        token: String,
        userId: Int,
    ): Result<UpdatedData, NetworkError> =
        doApiCall(
            tag = TAG
        ) {
            client.post(ApiRoutes.Admin.REACTIVATE_USER+"/$userId") {
                bearerAuth(token)
            }
        }

    override suspend fun getGuardiansByChildId(
        token: String,
        childId: Int,
    ): Result<GetGuardianByChildIdResponse, NetworkError> =
        doApiCall(
            tag = TAG
        ) {
            client.get(ApiRoutes.GUARDIANS_BY_CHILD_ID+"/$childId") {
                bearerAuth(token)
            }
        }
}
