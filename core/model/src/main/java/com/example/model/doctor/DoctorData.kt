package com.example.model.doctor

data class DoctorData(
    val id: Int?,
    val firstName: String,
    val middleName: String,
    val lastName: String,
    val imageUrl: String? = null,
    val speciality: String?
){
    val fullName: String
        get() = "$firstName $middleName $lastName"
}
