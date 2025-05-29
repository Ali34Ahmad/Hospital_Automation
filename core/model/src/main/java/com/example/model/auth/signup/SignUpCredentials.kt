package com.example.model.auth.signup

import com.example.model.enums.Gender

data class SignUpCredentials(
    val role: String,
    val email: String,
    val firstName: String,
    val middleName: String,
    val lastName: String,
    val password: String,
    val phoneNumber: String,
    val gender: Gender,
)