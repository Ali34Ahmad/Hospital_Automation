package com.example.constants.enums

enum class RequestState(val text: String) {
    PENDING("Request"),
    REJECTED("Rejection"),
    EXPIRED("Request Expired"),
    SUSPENDED("Suspend"),
    CONFIRMED("Acceptance"),
    CONTRACT_ENDED("Contract Ended"),
}