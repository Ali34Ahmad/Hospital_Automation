package com.example.data.repositories.residential_address

import com.example.data.mapper.residential_address.toAddAddressRequestDto
import com.example.data.mapper.residential_address.toAddAddressResponse
import com.example.domain.repositories.AddResidentialAddressRepository
import com.example.model.residential_address.AddAddressResponse
import com.example.model.residential_address.AddAddressRequest
import com.example.network.remote.add_residential_address.AddResidentialAddressApiService
import com.example.utility.network.Result
import com.example.utility.network.map
import com.example.utility.network.rootError

class AddResidentialAddressRepositoryImpl(
    private val addResidentialAddressApiService: AddResidentialAddressApiService,
):AddResidentialAddressRepository {
    override suspend fun addResidentialAddress(addAddressRequest: AddAddressRequest): Result<AddAddressResponse, rootError> =
        addResidentialAddressApiService.addResidentialAddress(
            addAddressRequestDto = addAddressRequest.toAddAddressRequestDto()
        ).map { addAddressResponseDto ->
            addAddressResponseDto.toAddAddressResponse()
        }
}