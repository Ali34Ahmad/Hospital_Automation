package com.example.model.child

import com.example.model.age.Age
import com.example.model.helper.ext.toAgeFromDate

data class ChildData(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val fatherFirstName: String,
    val fatherLastName: String,
    val motherFirstName: String,
    val motherLastName: String,
    val dateOfBirth: String,
){
    val fullName: String
        get() = "$firstName $lastName"
    val age: Age
        get() = dateOfBirth.toAgeFromDate()
    val fatherFullName: String
        get() = "$fatherFirstName $fatherLastName"
    val motherFullName: String
        get() = "$motherFirstName $motherLastName"
}