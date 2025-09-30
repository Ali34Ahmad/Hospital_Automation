package com.example.network.remote.pharmacy

import com.example.network.model.response.pharmacy.GetFilteredPharmaciesResponse
import com.example.utility.network.NetworkError
import com.example.utility.network.Result

interface AdminPharmacyApiService {
    suspend fun getPharmacies(
        token: String,
        query: String,
        status: String,
        page: Int,
        limit: Int,
    ): Result<GetFilteredPharmaciesResponse, NetworkError>
}