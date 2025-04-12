package com.example.network.remote.child

import com.example.network.model.request.NetworkFullName
import com.example.network.model.response.ChildrenResponse
import com.example.network.model.response.ShowChildProfileResponse
import com.example.network.utility.Resource

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

}