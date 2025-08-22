package com.example.model.age

import com.example.model.enums.AgeUnit
import com.example.model.helper.ext.capitalFirstChar

data class Age(
    val value: Int,
    val unit: AgeUnit,
)

fun Age.toAppropriateFormat():String{
    val value=this.value
    val unitText=if(value==1) this.unit
    else{
        this.unit.name.capitalFirstChar()+"s"
    }
    return "$value $unitText"
}
