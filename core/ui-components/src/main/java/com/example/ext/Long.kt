package com.example.ext

import java.util.Locale
import java.util.concurrent.TimeUnit

fun Long.toTimeFormat(): String {
    if (this < 0) {
        return "--:--" // Or handle negative durations as needed
    }

    val hours = TimeUnit.SECONDS.toHours(this)
    val minutes = TimeUnit.SECONDS.toMinutes(this) % 60

    return when {
        hours > 0 -> String.format(Locale.ROOT, "%d h:%02d min", hours, minutes)
        minutes > 0 -> String.format(Locale.ROOT, "%02d min", minutes)
        else -> String.format(Locale.ROOT, "--:--")
    }
}