package com.example.model.pharmacy

data class PharmacistData(
    val id: Int?,
    val firstName: String,
    val middleName: String,
    val lastName: String,
    val imageUrl: String?
){
    val fullName: String
        get()="$firstName $middleName $lastName"
}
