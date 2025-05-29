package com.example.ext

import com.example.model.helper.Age
import com.example.model.helper.ext.toCapitalizedString


fun Age.toAppropriateFormat():String{
    val value=this.value
    val unitText=if(value==1) this.unit.name.toCapitalizedString()
    else{
        this.unit.name.toCapitalizedString()+"s"
    }
    return "$value $unitText"
}