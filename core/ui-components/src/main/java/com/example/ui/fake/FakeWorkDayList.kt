package com.example.ui.fake

import com.example.model.doctor.day_scedule.DaySchedule
import java.time.DayOfWeek
import java.time.LocalTime

fun createSampleWorkDayList(): List<DaySchedule> {
    return listOf(
        DaySchedule(
            id = 1,
            doctorId = 101,
            clinicId = null,
            pharmacyId = null,
            dayOfWeek = DayOfWeek.SATURDAY,
            startTime = LocalTime.of(11, 0),
            endTime = LocalTime.of(15, 0)
        ),
        DaySchedule(
            id = 1,
            doctorId = 101,
            clinicId = null,
            pharmacyId = null,
            dayOfWeek = DayOfWeek.SATURDAY,
            startTime = LocalTime.of(11, 0),
            endTime = LocalTime.of(15, 0)
        ),
        DaySchedule(
            id = 1,
            doctorId = 101,
            clinicId = null,
            pharmacyId = null,
            dayOfWeek = DayOfWeek.SATURDAY,
            startTime = LocalTime.of(11, 0),
            endTime = LocalTime.of(15, 0)
        ),
        DaySchedule(
            id = 1,
            doctorId = 101,
            clinicId = null,
            pharmacyId = null,
            dayOfWeek = DayOfWeek.SATURDAY,
            startTime = LocalTime.of(11, 0),
            endTime = LocalTime.of(15, 0)
        ),
        DaySchedule(
            id = 1,
            doctorId = 101,
            clinicId = null,
            pharmacyId = null,
            dayOfWeek = DayOfWeek.SATURDAY,
            startTime = LocalTime.of(11, 0),
            endTime = LocalTime.of(15, 0)
        ),
        DaySchedule(
            id = 1,
            doctorId = 101,
            clinicId = null,
            pharmacyId = null,
            dayOfWeek = DayOfWeek.SATURDAY,
            startTime = LocalTime.of(11, 0),
            endTime = LocalTime.of(15, 0)
        ),
        // You can add more DaySchedule instances as needed
    )
}