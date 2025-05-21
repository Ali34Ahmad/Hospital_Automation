package com.example.ext

import java.text.DecimalFormat
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

fun Long.toFileSizeFormatReadable(): String {
    if (this <= 0f) {
        return "0 ${FileSizeUnit.BYTES.displayName}"
    }

    var size = this.toDouble()
    var currentUnit = FileSizeUnit.BYTES

    while (size >= 1024.0 && currentUnit.ordinal < FileSizeUnit.entries.size - 1) {
        size /= 1024.0
        currentUnit = FileSizeUnit.entries[currentUnit.ordinal + 1]
    }

    // Format to one decimal place if needed, otherwise no decimal places for "Bytes"
    val formatPattern = if (currentUnit == FileSizeUnit.BYTES) "#,##0" else "#,##0.#"
    val decimalFormat = DecimalFormat(formatPattern)

    return "${decimalFormat.format(size)} ${currentUnit.displayName}"
}