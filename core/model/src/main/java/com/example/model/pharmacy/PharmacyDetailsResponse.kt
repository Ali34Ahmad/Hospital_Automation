package com.example.model.pharmacy

import com.example.model.address.Address
import com.example.model.doctor.day_scedule.DaySchedule
import com.example.model.enums.Gender
import com.example.model.user.FullName
import java.time.LocalDate
import java.time.LocalDateTime

data class PharmacyDetailsResponse(
    val pharmacyId: Int,
    val phName: String,
    val pharmacyAddress: Address,
    val phoneNumber: String,
    val isDeactivated: Boolean?, // Can be null
    val deactivationReason: String?,
    val contractStartDate: LocalDate? = null,
    val contractEndDate: LocalDate? = null,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val deactivatedBy: Int?,
    val pharmacistId: Int,
    val userWithAddress: UserWithAddress,
    val workDays: List<DaySchedule>
)

data class UserWithAddress(
    val userId: Int,
    val fullName: FullName,
    val imageUrl: String?,
    val addressGovernorate: String,
    val addressCity: String,
    val addressRegion: String,
    val addressStreet: String,
    val addressNote: String?,
    val isSuspended: Boolean,
    val isResigned: Boolean,
    val acceptedBy: Int?,
    val gender: Gender,
    val email: String,
)