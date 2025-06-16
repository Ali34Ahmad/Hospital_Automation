package com.example.doctor_profile.fake

import com.example.model.address.Address
import com.example.model.doctor.appointment.AppointmentTypeData
import com.example.model.doctor.day_scedule.DaySchedule
import com.example.model.doctor.doctor_profile.DoctorProfile
import com.example.model.doctor.doctor_profile.DoctorProfileResponse
import com.example.model.enums.DoctorStatus
import com.example.model.enums.Gender
import com.example.model.enums.Role
import com.example.model.user.FullName
import java.time.DayOfWeek


fun createSampleDoctorProfileResponse(): List<DoctorProfileResponse> {
    val sampleDoctorProfile = DoctorProfile(
        userId = 3, // Matches the example image userId
        role = Role.DOCTOR,
        email = "ali.ahmad.doc@example.com",
        fullName = FullName(
            firstName = "Ali",
            middleName = null, // Based on image, no middle name displayed
            lastName = "Ahmad"
        ),
        verifiedResetPassword = true,
        verifiedAccount = true,
        phoneNumber = "+963 931 661 772", // From image
        address = Address(
            governorate = "Lattakia", // From image
            city = "Lattakia",
            region = null, // Not specified in image detail
            street = "Athar Street", // From image
            note = null
        ),
        specialty = "Cardiologist", // From image
        department = "Department of Digestive Surgery", // From image
        currentStatus = DoctorStatus.OPENED, // From image
        availabilitySchedule = listOf( // From image
            DaySchedule(
                id = 1,
                dayOfWeek = DayOfWeek.SUNDAY,
                startTime = "12:30 PM",
                endTime = "04:00 PM",
                doctorId = null,
                clinicId = 1,
                pharmacyId = null
            ),
            DaySchedule(
                id = 2,
                dayOfWeek = DayOfWeek.MONDAY,
                startTime = "09:30 AM",
                endTime = "02:00 PM",
                doctorId = 1,
                clinicId = null,
                pharmacyId = null,
            ),
            DaySchedule(
                id = 3,
                dayOfWeek = DayOfWeek.TUESDAY,
                startTime = "09:00 AM",
                endTime = "04:00 PM",
                doctorId = null,
                clinicId = null,
                pharmacyId = 1,
            )
            // Add more days if needed
        ),
        appointmentTypes = createSampleAppointmentTypes(),
        imageUrl = "https://placehold.co/400x400/AEDFFD/333783?text=Dr.+Ali", // Placeholder for image
        gender = Gender.MALE, // From image
        isSuspended = false,
        suspendingReason = null,
        isResigned = false,
        workStartDate = "2018-09-01", // Example date
        workEndDate = null, // Still working
        createdAt = "2018-08-20T09:00:00Z",
        updatedAt = "2024-05-24T10:15:30Z",
        clinicId = 42, // Example clinic ID
        resignedBy = null,
        suspendedBy = null,
        acceptedBy = 1 // Example admin ID
    )

    return listOf(
        DoctorProfileResponse(profile = sampleDoctorProfile, isAccessedByOwner = true),
        DoctorProfileResponse(profile = sampleDoctorProfile, isAccessedByOwner = false)
    )
}

fun createSampleAppointmentTypes(): List<AppointmentTypeData> {
    val appointmentTypes = listOf(
        AppointmentTypeData(
            id = 1,
            name = "General Consultation",
            standardDurationInMinutes = 30,
            description = "Standard check-up and discussion of health concerns.",
            doctorId = 201
        ),
        AppointmentTypeData(
            id = 2,
            name = "Pediatric Well Child Visit (0-2 Yrs)",
            standardDurationInMinutes = 45,
            description = "Growth monitoring, vaccinations, and developmental assessment for infants and toddlers.",
            doctorId = 202
        ),
        AppointmentTypeData(
            id = 3,
            name = "Follow-up Visit",
            standardDurationInMinutes = 20,
            description = "Brief follow-up to discuss test results or treatment progress.",
            doctorId = 201
        ),
        AppointmentTypeData(
            id = 4,
            name = "Physiotherapy Session",
            standardDurationInMinutes = 50,
            description = "Rehabilitation session including exercises and manual therapy.",
            doctorId = 203
        ),
        AppointmentTypeData(
            id = 5,
            name = "Urgent Care Slot",
            standardDurationInMinutes = 25,
            description = "For acute, non-life-threatening conditions requiring prompt attention.",
            doctorId = 204
        )
    )
    return appointmentTypes
}