package com.example.data.mapper.user

import com.example.model.guardian.GuardianData
import com.example.model.guardian.GuardianFullData
import com.example.network.model.dto.user.UserDto
import com.example.network.model.dto.user.UserFullDto
import com.example.network.utility.ApiRoutes

internal fun UserFullDto.toGuardianData() = GuardianData(
    id = userId,
    img = "${ApiRoutes.BASE_URL}/$imgUrl",
    fullName = "$firstName $middleName $lastName"
)

internal fun UserDto.toGuardianData() = GuardianData(
    id = userId?:-1,
    img = "${ApiRoutes.BASE_URL}/$imageUrl",
    fullName = "$firstName $middleName $lastName"
)


internal fun UserFullDto.toGuardianFullData() = GuardianFullData(
    userId = userId,
    email = email,
    firstName = firstName,
    middleName =middleName,
    lastName = lastName,
    phoneNumber = phoneNumber,
    addressGovernorate = addressGovernorate,
    addressCity = addressCity,
    addressRegion = addressRegion,
    addressStreet = addressStreet,
    addressNote = addressNote,
    gender = gender,
    imgUrl = "${ApiRoutes.BASE_URL}/$imgUrl"
)