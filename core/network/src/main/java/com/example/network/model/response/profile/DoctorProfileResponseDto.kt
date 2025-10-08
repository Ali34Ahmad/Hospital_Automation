package com.example.network.model.response.profile

import com.example.network.model.dto.appointment.AppointmentTypeDto
import com.example.network.model.enums.RoleDto
import com.example.network.model.dto.workday.WorkDayDto
import com.example.network.model.enums.GenderDto
import com.example.network.model.response.work_request.ClinicMainInfoDto
import com.example.network.serializer.LocalDateSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class DoctorProfileResponseDto(
    val profile: DoctorProfileDto,
    @SerialName("is_The_Same_Employee")
    val isAccessedByOwner: Boolean?=null,
)

@Serializable
data class DoctorProfileDto(
    @SerialName("userId")
    val userId: Int,
    val role: RoleDto,
    val email: String,
    @SerialName("first_name")
    val firstName: String,
    @SerialName("middle_name")
    val middleName: String?,
    @SerialName("last_name")
    val lastName: String,
    @SerialName("verified_reset_password")
    val verifiedResetPassword: Boolean,
    @SerialName("verified_account")
    val verifiedAccount: Boolean,
    @SerialName("phone_number")
    val phoneNumber: String,
    @SerialName("address_governorate")
    val addressGovernorate: String?,
    @SerialName("address_city")
    val addressCity: String?,
    @SerialName("address_region")
    val addressRegion: String?,
    @SerialName("address_street")
    val addressStreet: String?,
    @SerialName("address_note")
    val addressNote: String?,
    val specialty: String?,
    @SerialName("imgurl")
    val imageUrl: String?,
    @SerialName("medical_license_img_url")
    val medicalLicenseImgUrl: String?,
    val gender: GenderDto?,
    @SerialName("is_suspended")
    val isSuspended: Boolean,
    @SerialName("suspending_reason")
    val suspendingReason: String? = null,
    @SerialName("is_resigned")
    val isResigned: Boolean,
    @Serializable(with = LocalDateSerializer::class)
    @SerialName("work_start_date")
    val workStartDate: LocalDate?,
    @Serializable(with = LocalDateSerializer::class)
    @SerialName("work_end_date")
    val workEndDate: LocalDate?,
    val createdAt: String,
    val updatedAt: String,
    @SerialName("clinic_id")
    val clinicId: Int?,
    @SerialName("clinic")
    val clinic: ClinicMainInfoDto,
    @SerialName("resigned_by")
    val resignedBy: Int?,
    @SerialName("suspended_by")
    val suspendedBy: Int?,
    @SerialName("accepted_by")
    val acceptedBy: Int?,
    @SerialName("work_days")
    val workDays: List<WorkDayDto>,
    @SerialName("appointment_types")
    val appointmentTypes: List<AppointmentTypeDto>,

    )