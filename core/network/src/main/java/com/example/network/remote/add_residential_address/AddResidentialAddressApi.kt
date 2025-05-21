package com.example.network.remote.add_residential_address

import com.example.network.model.request.AddressRequest
import com.example.network.model.response.AddAddressResponse
import com.example.utility.network.Result
import com.example.utility.network.rootError

interface AddResidentialAddressApi {
    suspend fun addResidentialAddress(
        addressRequest: AddressRequest
    ): Result<AddAddressResponse, rootError>
}