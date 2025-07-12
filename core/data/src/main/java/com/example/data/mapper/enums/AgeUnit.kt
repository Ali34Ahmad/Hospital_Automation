package com.example.data.mapper.enums

import com.example.model.enums.AgeUnit
import com.example.network.model.enums.AgeUnitDto

fun AgeUnitDto.toAgeUnit(): AgeUnit{
    return when(this){
        AgeUnitDto.DAY -> AgeUnit.DAY
        AgeUnitDto.WEEK -> AgeUnit.WEEK
        AgeUnitDto.MONTH -> AgeUnit.MONTH
        AgeUnitDto.YEAR -> AgeUnit.YEAR
        AgeUnitDto.NOT_SPECIFIED -> AgeUnit.NONE
    }
}
fun AgeUnit.toAgeUnitDto(): AgeUnitDto{
    return when(this){
        AgeUnit.DAY -> AgeUnitDto.DAY
        AgeUnit.WEEK -> AgeUnitDto.WEEK
        AgeUnit.MONTH -> AgeUnitDto.MONTH
        AgeUnit.YEAR -> AgeUnitDto.YEAR
        AgeUnit.NONE -> AgeUnitDto.NOT_SPECIFIED
    }
}