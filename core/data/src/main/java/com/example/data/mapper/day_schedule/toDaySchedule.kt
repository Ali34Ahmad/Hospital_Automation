package com.example.data.mapper.day_schedule

import com.example.model.doctor.day_scedule.DaySchedule
import com.example.network.model.dto.WorkDayDto

fun WorkDayDto.toDaySchedule(): DaySchedule {
    return DaySchedule(
        id = this.id,
        doctorId = this.doctorId,
        clinicId = this.clinicId,
        pharmacyId = this.pharmacyId,
        dayOfWeek = this.day,
        startTime = this.workStartTime,
        endTime = this.workEndTime
    )
}