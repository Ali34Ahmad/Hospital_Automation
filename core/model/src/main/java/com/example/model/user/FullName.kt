package com.example.model.user

data class FullName(
    val firstName: String,
    val middleName: String?,
    val lastName: String,
){
    override fun toString(): String {
        return "$firstName $middleName $lastName"
    }
}
