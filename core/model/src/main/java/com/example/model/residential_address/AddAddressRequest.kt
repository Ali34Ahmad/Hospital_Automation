package com.example.model.residential_address

data class AddAddressRequest(
    val governorate: String,
    val city: String,
    val region: String,
    val street: String,
    val note: String? = null
)