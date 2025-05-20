package com.example.model.child

data class ChildFullData(
    val numberOfGuardians: Int? = null,
    val childId: Int? = null,
    val firstName: String,
    val lastName: String,
    val fatherFirstName: String,
    val fatherLastName: String,
    val motherFirstName: String,
    val motherLastName: String,
    val dateOfBirth: String,
    val birthCertificateImgUrl: String? = null,
    val gender: String,
    val employeeId: Int? = null,
)
