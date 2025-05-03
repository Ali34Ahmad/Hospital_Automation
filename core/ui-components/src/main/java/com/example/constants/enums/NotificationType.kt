package com.example.constants.enums

enum class NotificationType(val text:String){
    ADDING_VACCINE("New Vaccine"),
    DELETING_VACCINE("Deleted Vaccine"),
    BOOKING_APPOINTMENT("Appointment"),
    DISPENSING_PRESCRIPTION("Prescription"),
    SUSPENDING_ACCOUNT("Suspend"),
    ENDING_CONTRACT("Contract Ended"),
}