package com.example.network.remote.add_residential_address

import com.example.network.model.request.AddressRequestDto
import com.example.network.model.response.AddAddressResponseDto
import com.example.utility.network.Result
import com.example.utility.network.rootError

interface AddResidentialAddressApiService {
    suspend fun addResidentialAddress(
        addAddressRequestDto: AddressRequestDto
    ): Result<AddAddressResponseDto, rootError>
}