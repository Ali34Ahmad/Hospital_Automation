package com.example.model.residential_address

import com.example.model.enums.Role

data class AddAddressRequest(
    val governorate: String,
    val city: String,
    val region: String,
    val street: String,
    val note: String? = null,
    val role: Role,
)