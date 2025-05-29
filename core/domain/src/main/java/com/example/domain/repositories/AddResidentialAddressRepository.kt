package com.example.domain.repositories

import com.example.model.residential_address.AddAddressResponse
import com.example.model.residential_address.AddAddressRequest
import com.example.utility.network.Result
import com.example.utility.network.rootError

interface AddResidentialAddressRepository {
    suspend fun addResidentialAddress(
        addAddressRequest: AddAddressRequest
    ): Result<AddAddressResponse, rootError>
}