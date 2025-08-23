package com.example.network.remote.user

import com.example.network.model.enums.RoleDto
import com.example.network.model.response.relations.ChildGuardianRelationResponse
import com.example.network.model.response.user.GetGuardianByChildIdResponse
import com.example.network.model.response.user.GetGuardianByIdResponse
import com.example.network.model.response.user.GetUsersByNameResponse
import com.example.utility.network.NetworkError
import com.example.utility.network.Result

interface UserApiService {

    suspend fun getUserProfile(
        token: String,
        id: Int,
        roleDto: RoleDto
    ): Result<GetGuardianByIdResponse, NetworkError>

    suspend fun getGuardiansByChildId(
        token: String,
        childId: Int,
    ): Result<GetGuardianByChildIdResponse, NetworkError>

    suspend fun addGuardianToChild(
        token: String,
        childId: Int,
        userId: Int
    ): Result<ChildGuardianRelationResponse, NetworkError>

    suspend fun getUsersByName(
        token: String,
        page: Int,
        limit: Int,
        name: String
    ): Result<GetUsersByNameResponse, NetworkError>

}