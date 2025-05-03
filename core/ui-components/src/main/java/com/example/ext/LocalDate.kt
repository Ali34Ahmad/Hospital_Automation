package com.example.ext

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun LocalDate.toAppropriateFormat():String{
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    return this.format(formatter)
}