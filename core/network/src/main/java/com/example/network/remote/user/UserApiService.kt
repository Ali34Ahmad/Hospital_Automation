package com.example.network.remote.user

import com.example.network.model.request.NetworkFullName
import com.example.network.model.response.NetworkUser
import com.example.network.utility.Resource

interface UserApiService {

    suspend fun getUserProfile(token: String, fullName: NetworkFullName) : Resource<NetworkUser>


//    suspend fun getUsersByName(
//        token: String,
//        page: Int,
//        limit: Int,
//        name: String
//    )

}