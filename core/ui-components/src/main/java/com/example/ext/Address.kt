package com.example.ext

import com.example.model.address.Address

fun Address.toAppropriateFormat(): String {
    val addressParts = listOfNotNull(
        this.street?.takeIf { it.isNotBlank() },
        this.region?.takeIf { it.isNotBlank() },
        this.city?.takeIf { it.isNotBlank() },
        this.governorate?.takeIf { it.isNotBlank() },
        this.note?.takeIf { it.isNotBlank() }?.let { "($it)" }
    )

    return if (addressParts.isEmpty()) {
        ""
    } else {
        addressParts.joinToString(separator = ", ")
    }
}
