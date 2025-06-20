package com.example.model.doctor.day_scedule

import com.example.model.enums.DoctorStatus
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

data class DaySchedule(
    val id:Int,
    val doctorId:Int?,
    val clinicId:Int?,
    val pharmacyId:Int?,
    val dayOfWeek: DayOfWeek,
    val startTime: LocalTime,
    val endTime: LocalTime
)


object DoctorStatusChecker {
    fun getDoctorStatus(availabilitySchedule: List<DaySchedule>): DoctorStatus {
        val currentDayOfWeek = LocalDate.now().dayOfWeek
        val currentTime = LocalTime.now()

        val currentDaySchedule = availabilitySchedule.find { it.dayOfWeek == currentDayOfWeek }
        if (currentDaySchedule == null) return DoctorStatus.CLOSED

        if (currentTime.isAfter(currentDaySchedule.startTime) && currentTime.isBefore(
                currentDaySchedule.endTime
            )
        ) {
            return DoctorStatus.OPENED
        }

        return DoctorStatus.CLOSED
    }
}
