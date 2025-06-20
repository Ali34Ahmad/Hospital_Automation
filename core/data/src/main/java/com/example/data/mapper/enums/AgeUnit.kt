package com.example.data.mapper.enums

import com.example.model.enums.AgeUnit
import com.example.network.model.enums.AgeUnitDto

fun AgeUnitDto.toAgeUnit(): AgeUnit{
    return when(this){
        AgeUnitDto.DAY -> AgeUnit.DAY
        AgeUnitDto.WEEK -> AgeUnit.WEEK
        AgeUnitDto.MONTH -> AgeUnit.MONTH
        AgeUnitDto.YEAR -> AgeUnit.YEAR
        AgeUnitDto.NOT_SPECIFIED -> AgeUnit.NOT_SPECIFIED
    }
}