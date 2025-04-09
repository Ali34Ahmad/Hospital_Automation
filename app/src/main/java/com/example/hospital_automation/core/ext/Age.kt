package com.example.hospital_automation.core.ext

import com.example.hospital_automation.core.model.Age

fun Age.toAppropriateFormat():String{
    val value=this.value
    val unitText=if(value==1) this.unit.name.toCapitalizedString()
    else{
        this.unit.name.toCapitalizedString()+"s"
    }
    return "$value $unitText"
}