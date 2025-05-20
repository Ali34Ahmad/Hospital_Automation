package com.example.data.mapper.user

import com.example.model.guardian.GuardianData
import com.example.network.model.dto.user.UserFullDto

internal fun UserFullDto.toGuardianData() = GuardianData(
    id = userId,
    img = imgUrl,
    fullName = "$firstName $middleName $lastName"
)
