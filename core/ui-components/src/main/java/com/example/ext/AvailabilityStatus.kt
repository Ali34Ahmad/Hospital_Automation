package com.example.ext

import com.example.constants.enums.AvailabilityStatus

fun AvailabilityStatus.copy(suspendingReason: String?): AvailabilityStatus {
    return when (this) {
        AvailabilityStatus.OPEN -> AvailabilityStatus.OPEN
        AvailabilityStatus.CLOSED -> AvailabilityStatus.CLOSED
        AvailabilityStatus.RESIGNED -> AvailabilityStatus.RESIGNED
        AvailabilityStatus.SUSPENDED -> AvailabilityStatus.SUSPENDED.apply {
            this.suspendingReason = suspendingReason
        }
    }
}