package com.example.model.helper

import com.example.model.helper.ext.toCapitalizedString

data class Age(
    val value: Int,
    val unit: AgeUnit,
)

fun Age.toAppropriateFormat():String{
    val value=this.value
    val unitText=if(value==1) this.unit
    else{
        this.unit.name.toCapitalizedString()+"s"
    }
    return "$value $unitText"
}
