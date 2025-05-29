package com.example.utility.ui

fun formatAddress(
    governorate: String?,
    city: String?,
    region: String?,
    street: String?,
    note: String?,
): String {
    val addressParts = listOfNotNull(
        street?.takeIf { it.isNotBlank() },
        region?.takeIf { it.isNotBlank() },
        city?.takeIf { it.isNotBlank() },
        governorate?.takeIf { it.isNotBlank() },
        note?.takeIf { it.isNotBlank() }?.let { "($it)" }
    )

    return if (addressParts.isEmpty()) {
        ""
    } else {
        addressParts.joinToString(separator = ", ")
    }
}
