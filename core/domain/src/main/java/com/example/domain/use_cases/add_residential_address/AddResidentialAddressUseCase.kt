package com.example.domain.use_cases.add_residential_address

import com.example.domain.repositories.AddResidentialAddressRepository
import com.example.model.residential_address.AddAddressRequest
import com.example.model.residential_address.AddAddressResponse
import com.example.utility.network.rootError
import com.example.utility.network.Result

class AddResidentialAddressUseCase(
    private val addResidentialAddressRepository: AddResidentialAddressRepository
) {
    suspend operator fun invoke(
        addAddressRequest: AddAddressRequest
    ): Result<AddAddressResponse, rootError> {
        return addResidentialAddressRepository.addResidentialAddress(addAddressRequest)
    }
}