package com.example.model.auth.signup

import com.example.model.enums.Gender
import com.example.model.enums.Role

data class BaseRegistrationRequest(
    val role: Role,
    val email: String,
    val firstName: String,
    val middleName: String,
    val lastName: String,
    val password: String,
    val phoneNumber: String,
    val gender: Gender,
)