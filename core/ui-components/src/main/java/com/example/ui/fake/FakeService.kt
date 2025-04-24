package com.example.ui.fake

import com.example.model.Service
import java.time.Duration
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalUnit
import java.util.concurrent.TimeUnit

fun createSampleServiceData(): List<Service> {
    return listOf(
        Service(
            1,
            "Check-up",
            "Routine medical examination",
            standardDuration = Duration.of(10L, ChronoUnit.MINUTES)
        ),
        Service(
            2,
            "Vaccination",
            "Administering vaccines",
            standardDuration = Duration.of(10L, ChronoUnit.MINUTES)
        ),
        Service(
            3,
            "Consultation",
            "Medical advice and guidance",
            standardDuration = Duration.of(10L, ChronoUnit.MINUTES)
        ),
        Service(
            4,
            "Blood Test",
            "Blood analysis",
            standardDuration = Duration.of(10L, ChronoUnit.MINUTES)
        ),
        Service(
            5,
            "Skin Exam",
            "Skin examination",
            standardDuration = Duration.of(10L, ChronoUnit.MINUTES)
        )
    )
}