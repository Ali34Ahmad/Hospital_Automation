package com.example.data.mapper.residential_address

import com.example.model.residential_address.AddAddressRequest
import com.example.model.residential_address.AddAddressResponse
import com.example.network.model.request.AddressRequestDto
import com.example.network.model.response.AddAddressResponseDto

fun AddAddressRequest.toAddAddressRequestDto() =
    AddressRequestDto(
        governorate = this.governorate,
        city = this.city,
        region = this.region,
        street = this.street,
        note = this.note,
    )

fun AddAddressResponseDto.toAddAddressResponse() =
    AddAddressResponse(
        updatedData = this.updatedData
    )