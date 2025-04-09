package com.example.hospital_automation.core.ext

fun String.clickableTextRange(text: String): IntRange {
    val startIndex = this.indexOf(text)
    if (startIndex == -1) {
        return 0..0 // Name not found
    }
    val endIndex = startIndex + text.length - 1
    return startIndex..endIndex
}

fun String.toCapitalizedString():String{
    if (isEmpty()) {
        return this
    }
    return this[0].uppercase() + this.substring(1).lowercase()
}