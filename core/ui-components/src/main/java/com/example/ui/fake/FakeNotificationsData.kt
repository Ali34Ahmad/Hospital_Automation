package com.example.ui.fake

import com.example.constants.enums.NotificationType
import com.example.model.Notification
import com.example.model.Request
import java.time.LocalDateTime

fun createSampleNotifications(): List<Notification> {
    val requests: List<Request> = createSampleRequests()
    val now = LocalDateTime.now()
    return listOf(
        // General notification (no request, appointment, vaccine, or pharmacist)
        Notification(
            id = 1,
            title = "System Update",
            message = "A new system update is available.",
            sendTime = now.minusDays(1),
            type = NotificationType.BOOKING_APPOINTMENT,
        ),
        // Notification related to a request (e.g., request confirmation)
        Notification(
            id = 2,
            title = "Request Confirmed",
            message = "Your request for equipment has been confirmed.",
            userId = 102,
            request = requests.first(), // Find the corresponding request
            sendTime = now.minusHours(2)
        ),
        // Notification related to an appointment
        Notification(
            id = 3,
            title = "Appointment Reminder",
            message = "You have an appointment tomorrow.",
            userId = 105,
            appointmentId = 501,
            sendTime = now.plusDays(1)
        ),
        // Notification related to a vaccine
        Notification(
            id = 4,
            title = "Vaccine Available",
            message = "The flu vaccine is now available at our clinic.",
            userId = 106,
            vaccineId = 201,
            sendTime = now
        ),
        // Notification related to a pharmacist
        Notification(
            id = 5,
            title = "Pharmacist Available",
            message = "Our pharmacist, John Doe, is available for consultation.",
            userId = 107,
            pharmacistId = 301,
            sendTime = now.plusHours(1)
        ),
        // Notification related to a rejected request
        Notification(
            id = 6,
            title = "Request Rejected",
            message = "Your request has been rejected.",
            userId = 103,
            request = requests[2],
            sendTime = now.minusDays(2)
        ),
        // Notification related to a pending request
        Notification(
            id = 7,
            title = "Request Pending",
            message = "Your request is pending.",
            userId = 101,
            request = requests.first { it.id == 1 },
            sendTime = now.minusDays(3)
        )
    )
}