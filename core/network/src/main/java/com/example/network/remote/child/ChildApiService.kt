package com.example.network.remote.child

import com.example.network.model.request.child.AddChildRequest
import com.example.network.model.request.NetworkFullName
import com.example.network.model.response.ChildrenResponse
import com.example.network.model.response.NetworkMessage
import com.example.network.model.response.ShowChildProfileResponse
import com.example.network.model.response.child.AddChildResponse
import com.example.network.utility.Resource
import com.example.network.utility.Result
import com.example.network.utility.rootError
import java.io.File

interface ChildApiService {
    /**
     * Show child profile by full name
     * need update to use id instead of full name
     */
    suspend fun showChildProfile(
        token: String,
        fullName: NetworkFullName
    ) : Resource<ShowChildProfileResponse>

    suspend fun getChildrenByName(
        page: Int,
        limit: Int,
        token: String,
        name: String
    ) : Resource<ChildrenResponse>

    suspend fun addChild(
        token: String,
        child: AddChildRequest
    ) : Result<AddChildResponse, rootError>

    /**
     * I use Id instead of full name
     */
    suspend fun uploadChildCertificate(
        token: String,
        id: String,
        image: File,
    ): Resource<NetworkMessage>

}