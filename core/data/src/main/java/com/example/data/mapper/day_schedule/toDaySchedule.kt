package com.example.data.mapper.day_schedule

import com.example.model.doctor.day_scedule.DaySchedule
import com.example.model.doctor.workday.WorkDaySummaryData
import com.example.network.model.dto.workday.WorkDayDto
import com.example.network.model.dto.workday.WorkDaySummaryDto
import java.time.DayOfWeek

fun WorkDayDto.toDaySchedule(): DaySchedule {
    return DaySchedule(
        id = this.id,
        doctorId = this.doctorId,
        clinicId = this.clinicId,
        pharmacyId = this.pharmacyId,
        dayOfWeek = this.day?: DayOfWeek.FRIDAY,
        startTime = this.workStartTime,
        endTime = this.workEndTime
    )
}

internal fun WorkDaySummaryData.toWorkDaySummaryDto() =
    WorkDaySummaryDto(
        day = day,
        startTime = startTime,
        endTime = endTime
    )

internal fun DaySchedule.toWorkDaySummary() = WorkDaySummaryData(
    id = id,
    day = dayOfWeek,
    startTime = startTime,
    endTime = endTime
)