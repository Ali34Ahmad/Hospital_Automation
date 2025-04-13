package com.example.network.remote.user

import com.example.network.model.request.AddChildForGuardianRequest

import com.example.network.model.request.NetworkFullName
import com.example.network.model.response.NetworkMessage
import com.example.network.model.response.NetworkUser
import com.example.network.utility.Resource

interface UserApiService {

    suspend fun getUserProfile(token: String, fullName: NetworkFullName) : Resource<NetworkUser>

    suspend fun addChildForGuardian(token: String,ids: AddChildForGuardianRequest) : Resource<NetworkMessage>

//    suspend fun getUsersByName(
//        token: String,
//        page: Int,
//        limit: Int,
//        name: String
//    )


}