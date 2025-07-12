package com.example.ext

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun LocalDate.toAppropriateDateFormat():String{
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return this.format(formatter)
}
fun LocalDate.isToday() =
    this.isEqual(LocalDate.now())
