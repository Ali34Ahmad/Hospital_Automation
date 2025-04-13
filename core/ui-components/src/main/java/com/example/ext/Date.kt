package com.example.ext

import androidx.compose.ui.text.intl.Locale
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.text.format
import kotlin.time.Duration.Companion.days

fun Date.toAppropriateFormat():String{
    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale("en").platformLocale)
    return sdf.format(this)
}