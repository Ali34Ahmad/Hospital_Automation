package com.example.ext

import androidx.compose.ui.text.intl.Locale
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date

fun Date.toAppropriateAddressFormat():String{
    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale("en").platformLocale)
    return sdf.format(this)
}
fun main() {
    val localDate = LocalDate.now()
    print(localDate.toAppropriateDateFormat())
}