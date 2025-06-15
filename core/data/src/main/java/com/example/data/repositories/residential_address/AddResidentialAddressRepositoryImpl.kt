package com.example.data.repositories.residential_address

import com.example.data.mapper.enums.toRoleDto
import com.example.data.mapper.residential_address.toAddAddressRequestDto
import com.example.data.mapper.residential_address.toAddAddressResponse
import com.example.domain.repositories.AddResidentialAddressRepository
import com.example.domain.repositories.local.UserPreferencesRepository
import com.example.model.residential_address.AddAddressResponse
import com.example.model.residential_address.AddAddressRequest
import com.example.network.remote.add_residential_address.AddResidentialAddressApiService
import com.example.utility.network.Result
import com.example.utility.network.map
import com.example.utility.network.rootError

class AddResidentialAddressRepositoryImpl(
    private val addResidentialAddressApiService: AddResidentialAddressApiService,
    private val userPreferencesRepository: UserPreferencesRepository,
) : AddResidentialAddressRepository {
    override suspend fun addResidentialAddress(addAddressRequest: AddAddressRequest): Result<AddAddressResponse, rootError> =
        userPreferencesRepository.executeWithValidToken { token ->
            addResidentialAddressApiService.addResidentialAddress(
                token,
                addAddressRequestDto = addAddressRequest.toAddAddressRequestDto(),
                role = addAddressRequest.role.toRoleDto(),
            ).map { addAddressResponseDto ->
                addAddressResponseDto.toAddAddressResponse()
            }
        }
}