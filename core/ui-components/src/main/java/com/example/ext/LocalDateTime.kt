package com.example.ext

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun LocalDateTime.toAppropriateDateTimeFormat(): String {
    val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy - hh:mm a", Locale.ENGLISH)
    return this.format(formatter)
}