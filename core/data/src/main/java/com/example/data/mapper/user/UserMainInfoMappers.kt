package com.example.data.mapper.user

import com.example.model.user.FullName
import com.example.model.user.UserMainInfo
import com.example.network.model.response.user.UserMainInfoDto

fun UserMainInfoDto.toUserMainInfo()=
    UserMainInfo(
        id = id,
        fullName = FullName(firstName,middleName,lastName),
        imageUrl = imageUrl,
        subInfo = subInfo
    )