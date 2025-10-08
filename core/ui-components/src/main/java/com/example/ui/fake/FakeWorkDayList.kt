package com.example.ui.fake

import com.example.model.doctor.day_scedule.DaySchedule
import com.example.model.doctor.workday.WorkDaySummaryData
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
            id = 2,
            doctorId = 101,
            clinicId = null,
            pharmacyId = null,
            dayOfWeek = DayOfWeek.SATURDAY,
            startTime = LocalTime.of(11, 0),
            endTime = LocalTime.of(15, 0)
        ),
        DaySchedule(
            id = 3,
            doctorId = 101,
            clinicId = null,
            pharmacyId = null,
            dayOfWeek = DayOfWeek.SATURDAY,
            startTime = LocalTime.of(11, 0),
            endTime = LocalTime.of(15, 0)
        ),
        DaySchedule(
            id = 4,
            doctorId = 101,
            clinicId = null,
            pharmacyId = null,
            dayOfWeek = DayOfWeek.SATURDAY,
            startTime = LocalTime.of(11, 0),
            endTime = LocalTime.of(15, 0)
        ),
        DaySchedule(
            id = 5,
            doctorId = 101,
            clinicId = null,
            pharmacyId = null,
            dayOfWeek = DayOfWeek.SATURDAY,
            startTime = LocalTime.of(11, 0),
            endTime = LocalTime.of(15, 0)
        ),
        DaySchedule(
            id = 6,
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

fun createWorkDaysList() = listOf(
    WorkDaySummaryData(
        id = 1,
        day = DayOfWeek.SUNDAY,
        startTime = LocalTime.of(8,0),
        endTime = LocalTime.of(16,0)
    ),
    WorkDaySummaryData(
        id = 2,
        day = DayOfWeek.MONDAY,
        startTime = LocalTime.of(8,0),
        endTime = LocalTime.of(16,0)
    ),
    WorkDaySummaryData(
        id = 3,
        day = DayOfWeek.TUESDAY,
        startTime = LocalTime.of(8,0),
        endTime = LocalTime.of(16,0)
    ),
    WorkDaySummaryData(
        id = 4,
        day = DayOfWeek.WEDNESDAY,
        startTime = LocalTime.of(8,0),
        endTime = LocalTime.of(16,0)
    ),
    WorkDaySummaryData(
        id = 5,
        day = DayOfWeek.THURSDAY,
        startTime = LocalTime.of(8,0),
        endTime = LocalTime.of(16,0)
    ),
)