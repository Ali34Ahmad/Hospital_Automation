package com.example.constants.enums

enum class AvailabilityStatus(var suspendingReason: String? = null) {
    OPEN,
    CLOSED,
    SUSPENDED,
    RESIGNED,
}
