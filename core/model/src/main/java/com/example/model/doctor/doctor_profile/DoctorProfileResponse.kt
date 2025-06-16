package com.example.model.doctor.doctor_profile

import com.example.model.address.Address
import com.example.model.doctor.appointment.AppointmentTypeData
import com.example.model.doctor.day_scedule.DaySchedule
import com.example.model.enums.DoctorStatus
import com.example.model.enums.Gender
import com.example.model.enums.Role
import com.example.model.user.FullName


// The response wrapper for the DoctorProfile
data class DoctorProfileResponse(
    val profile: DoctorProfile,
    val isAccessedByOwner: Boolean,
)

data class DoctorProfile(
    val userId: Int,
    val role: Role,
    val email: String,
    val fullName: FullName,
    val verifiedResetPassword: Boolean,
    val verifiedAccount: Boolean,
    val phoneNumber: String,
    val address: Address,
    val specialty: String?,
    val department: String?,
    val currentStatus: DoctorStatus,
    val availabilitySchedule: List<DaySchedule>,
    val appointmentTypes: List<AppointmentTypeData>,
    val imageUrl: String?,
    val gender: Gender?,
    val isSuspended: Boolean,
    val suspendingReason: String?,
    val isResigned: Boolean,
    val workStartDate: String?,
    val workEndDate: String?,
    val createdAt: String,
    val updatedAt: String,
    val clinicId: Int?,
    val resignedBy: Int?,
    val suspendedBy: Int?,
    val acceptedBy: Int?
)