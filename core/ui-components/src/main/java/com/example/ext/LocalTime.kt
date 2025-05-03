package com.example.ext

import java.time.LocalTime
import java.time.format.DateTimeFormatter

fun LocalTime.toAppropriateFormat(): String {
    val formatter = DateTimeFormatter.ofPattern("hh:mm a")
    return this.format(formatter)
}

fun ClosedRange<LocalTime>.toAppropriateFormat(): String {
    return this.start.toAppropriateFormat()+" - "+this.endInclusive.toAppropriateFormat()
}

