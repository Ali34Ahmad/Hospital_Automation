package com.example.ext

import com.example.model.age.Age



fun Age.toAppropriateFormat():String{
    val value=this.value
    val unitText=if(value==1) this.unit.name
//        .toCapitalized()
    else{
        this.unit.name
//            .toCapitalized()+"s"
    }
    return "$value $unitText"
}
