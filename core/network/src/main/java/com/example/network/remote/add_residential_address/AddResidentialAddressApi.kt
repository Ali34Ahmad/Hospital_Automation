package com.example.network.remote.add_residential_address

import com.example.network.model.request.AddressRequest
import com.example.network.model.request.RegistrationRequest
import com.example.network.model.response.AddressResponse
import com.example.network.model.response.RegistrationResponse
import com.example.network.utility.Resource

interface AddResidentialAddressApi {
    suspend fun addResidentialAddress(
        addressRequest: AddressRequest
    ): Resource<AddressResponse>
}