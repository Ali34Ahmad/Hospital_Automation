package com.example.data.mapper.enums

import com.example.model.enums.Gender
import com.example.network.model.enums.GenderDto

fun Gender.toGender(): GenderDto {
    return when (this) {
        Gender.MALE -> GenderDto.MALE
        Gender.FEMALE -> GenderDto.FEMALE
    }
}

fun GenderDto.toGender(): Gender {
    return when (this) {
        GenderDto.MALE -> Gender.MALE
        GenderDto.FEMALE -> Gender.FEMALE
    }
}

