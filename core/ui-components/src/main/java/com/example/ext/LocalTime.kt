package com.example.ext

import java.time.LocalTime
import java.time.format.DateTimeFormatter

fun LocalTime.toAppropriateTimeFormat(): String {
    val formatter = DateTimeFormatter.ofPattern("hh:mm a")
    return this.format(formatter)
}

fun ClosedRange<LocalTime>.toAppropriateAddressFormat(): String {
    return this.start.toAppropriateTimeFormat()+" - "+this.endInclusive.toAppropriateTimeFormat()
}

