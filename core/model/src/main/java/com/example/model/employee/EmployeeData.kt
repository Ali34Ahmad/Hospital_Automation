package com.example.model.employee

data class EmployeeData(
    val id: Int?,
    val firstName: String,
    val middleName: String,
    val lastName: String,
    val imageUrl: String? = null,
){
    val fullName: String
        get() = "$firstName $middleName $lastName"
}
