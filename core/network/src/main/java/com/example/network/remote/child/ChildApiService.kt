package com.example.network.remote.child

import com.example.network.model.request.child.AddChildRequest
import com.example.network.model.response.child.AddChildResponse
import com.example.network.model.response.child.ChildFullResponse
import com.example.network.model.response.child.GetChildrenByGuardianIdResponse
import com.example.network.model.response.child.GetChildrenByNameResponse
import com.example.network.model.response.child.UploadCertificatedResponse
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import java.io.File

interface ChildApiService {

    suspend fun getChildProfile(
        token: String,
        id: Int
    ) : Result<ChildFullResponse, NetworkError>

    suspend fun getChildrenByName(
        page: Int,
        limit: Int,
        token: String,
        name: String
    ) : Result<GetChildrenByNameResponse, NetworkError>

    suspend fun addChild(
        token: String,
        guardianId: Int,
        child: AddChildRequest
    ) : Result<AddChildResponse, NetworkError>

    suspend fun uploadChildCertificate(
        token: String,
        id: String,
        image: File,
    ): Result<UploadCertificatedResponse, NetworkError>


    suspend fun getChildrenByGuardianId(
        token: String,
        guardianId: String,
    ): Result<GetChildrenByGuardianIdResponse, NetworkError>
}