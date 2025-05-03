package com.example.ext

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun LocalDateTime.toAppropriateFormat(): String {
    val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy - hh:mm a")
    return this.format(formatter)
}