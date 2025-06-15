package com.example.network.remote.add_residential_address

import com.example.network.model.enums.RoleDto
import com.example.network.model.request.AddressRequestDto
import com.example.network.model.response.AddAddressResponseDto
import com.example.utility.network.Result
import com.example.utility.network.rootError

interface AddResidentialAddressApiService {
    suspend fun addResidentialAddress(
        token: String,
        addAddressRequestDto: AddressRequestDto,
        role: RoleDto
    ): Result<AddAddressResponseDto, rootError>
}