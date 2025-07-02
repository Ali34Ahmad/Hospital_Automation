package com.example.data.mapper.doctor

import com.example.data.mapper.day_schedule.toDaySchedule
import com.example.data.mapper.enums.toGender
import com.example.data.mapper.enums.toRole
import com.example.model.address.Address
import com.example.model.doctor.DoctorData
import com.example.model.doctor.day_scedule.DaySchedule
import com.example.model.doctor.day_scedule.DoctorStatusChecker
import com.example.model.doctor.doctor_profile.DoctorProfile
import com.example.model.doctor.doctor_profile.DoctorProfileResponse
import com.example.model.enums.DoctorStatus
import com.example.model.user.FullName
import com.example.network.model.dto.WorkDayDto
import com.example.network.model.dto.user.UserDto
import com.example.network.model.response.profile.DoctorProfileDto
import com.example.network.model.response.profile.DoctorProfileResponseDto
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.TextStyle
import java.util.Locale

fun DoctorProfileResponseDto.toDoctorProfileResponse(): DoctorProfileResponse {
    return DoctorProfileResponse(
        profile = this.profile.toDoctorProfile()
    )
}

fun DoctorProfileDto.toDoctorProfile(): DoctorProfile {
    val fullName = FullName(
        firstName = this.firstName,
        middleName = this.middleName,
        lastName = this.lastName
    )

    val address = Address(
        governorate = this.addressGovernorate,
        city = this.addressCity,
        region = this.addressRegion,
        street = this.addressStreet,
        note = this.addressNote
    )
    val doctorStatus = DoctorStatusChecker.getDoctorStatus(this.workDays.map { it.toDaySchedule() })

    return DoctorProfile(
        userId = this.userId,
        role = this.role.toRole(),
        email = this.email,
        fullName = fullName,
        verifiedResetPassword = this.verifiedResetPassword,
        verifiedAccount = this.verifiedAccount,
        phoneNumber = this.phoneNumber,
        address = address,
        specialty = this.specialty,
        department = "this.clinicId",
        currentStatus = doctorStatus,
        availabilitySchedule = this.workDays.map { it.toDaySchedule() },
        appointmentTypes = this.appointmentTypes.map { it.toAppointmentTypeData() },
        imageUrl = this.imageUrl,
        gender = this.gender?.toGender(),
        isSuspended = this.isSuspended,
        suspendingReason = this.suspendingReason,
        isResigned = this.isResigned,
        workStartDate = this.workStartDate,
        workEndDate = this.workEndDate,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        clinicId = this.clinicId,
        resignedBy = this.resignedBy,
        suspendedBy = this.suspendedBy,
        acceptedBy = this.acceptedBy
    )
}


fun UserDto.toDoctorData() =
    DoctorData(
        id = userId,
        firstName = firstName,
        lastName = lastName,
        imageUrl = imageUrl,
        speciality = speciality
    )