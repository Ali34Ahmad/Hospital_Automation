package com.example.ui.fake

import com.example.model.WorkDay
import java.time.DayOfWeek
import java.time.LocalTime

fun createSampleWorkDayList(): List<WorkDay> {
    return listOf(
        WorkDay(
            id = 1,
            doctorId = 101,
            clinicId = 201,
            pharmacyId = null,
            day = DayOfWeek.MONDAY,
            workStartTime = LocalTime.of(9, 0),
            workEndTime = LocalTime.of(17, 0)
        ),
        WorkDay(
            id = 2,
            doctorId = 101,
            clinicId = 201,
            pharmacyId = null,
            day = DayOfWeek.TUESDAY,
            workStartTime = LocalTime.of(9, 0),
            workEndTime = LocalTime.of(17, 0)
        ),
        WorkDay(
            id = 3,
            doctorId = 101,
            clinicId = 201,
            pharmacyId = null,
            day = DayOfWeek.WEDNESDAY,
            workStartTime = LocalTime.of(9, 0),
            workEndTime = LocalTime.of(13, 0)
        ),
        WorkDay(
            id = 4,
            doctorId = 101,
            clinicId = null,
            pharmacyId = 301,
            day = DayOfWeek.THURSDAY,
            workStartTime = LocalTime.of(10, 0),
            workEndTime = LocalTime.of(18, 0)
        ),
        WorkDay(
            id = 5,
            doctorId = 101,
            clinicId = null,
            pharmacyId = 301,
            day = DayOfWeek.FRIDAY,
            workStartTime = LocalTime.of(10, 0),
            workEndTime = LocalTime.of(18, 0)
        ),
        WorkDay(
            id = null,
            doctorId = 101,
            clinicId = null,
            pharmacyId = null,
            day = DayOfWeek.SATURDAY,
            workStartTime = LocalTime.of(11, 0),
            workEndTime = LocalTime.of(15, 0)
        ),
        // You can add more WorkDay instances as needed
    )
}