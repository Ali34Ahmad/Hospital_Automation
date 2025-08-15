package com.example.data.mapper.employee

import com.example.model.employee.EmployeeData
import com.example.network.model.dto.user.UserDto

fun UserDto.toEmployeeData() = EmployeeData(
    id = userId,
    firstName = firstName,
    middleName = middleName,
    lastName = lastName,
    imageUrl = imageUrl
)