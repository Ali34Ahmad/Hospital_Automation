package com.example.ext

import com.example.model.age.Age
import com.example.model.helper.ext.capitalFirstChar


fun Age.toAppropriateAgeFormat():String{
    val value=this.value
    val unitText=if(value==1) this.unit.name.capitalFirstChar()
    else{
        this.unit.name.capitalFirstChar()+"s"
    }
    return "$value $unitText"
}
