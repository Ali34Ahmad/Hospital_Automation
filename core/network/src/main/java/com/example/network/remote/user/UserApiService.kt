package com.example.network.remote.user

import com.example.network.model.request.NetworkFullName
import com.example.network.model.dto.user.UserFullDto
import com.example.network.model.response.relations.ChildGuardianRelationResponse
import com.example.network.model.response.user.GetGuardiansByChildIdResponse
import com.example.network.model.response.user.GetUsersByNameResponse
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import com.example.network.utility.Resource

interface UserApiService {

    suspend fun getUserProfile(token: String, fullName: NetworkFullName) : Resource<UserFullDto>

    suspend fun getGuardiansByChildId(
        token: String,
        childId: Int
    ): Result<GetGuardiansByChildIdResponse, NetworkError>

    suspend fun addGuardianToChild(token: String, childId: Int, userId: Int) : Result<ChildGuardianRelationResponse, NetworkError>

    suspend fun getUsersByName(
        token: String,
        page: Int,
        limit: Int,
        name: String
    ): Result<GetUsersByNameResponse, NetworkError>

}