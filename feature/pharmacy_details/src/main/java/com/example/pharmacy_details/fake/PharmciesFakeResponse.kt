package com.example.pharmacy_details.fake

import com.example.model.address.Address
import com.example.model.doctor.day_scedule.DaySchedule
import com.example.model.enums.Gender
import com.example.model.pharmacy.PharmacyDetailsResponse
import com.example.model.pharmacy.UserWithAddress
import com.example.model.user.FullName
import com.example.ui.fake.createSampleWorkDayList
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime


fun createSamplePharmacyDetailsResponse(): List<PharmacyDetailsResponse> {
    val samplePharmacies = mutableListOf<PharmacyDetailsResponse>()

    // Pharmacy 1
    samplePharmacies.add(
        PharmacyDetailsResponse(
            pharmacyId = 1,
            phName = "City Central Pharmacy",
            pharmacyAddress = Address(
                governorate = "State A",
                city = "Metropolis",
                region = "Downtown",
                street = "123 Main St",
                note = "Corner of Main and First"
            ),
            phoneNumber = "555-0101",
            isDeactivated = false,
            deactivationReason = null,
            contractStartDate = LocalDate.of(2023, 1, 15),
            contractEndDate = LocalDate.of(2025, 1, 14),
            createdAt = LocalDateTime.of(2022, 12, 1, 10, 0, 0),
            updatedAt = LocalDateTime.of(2024, 5, 20, 14, 30, 0),
            deactivatedBy = null,
            pharmacistId = 101,
            userWithAddress = UserWithAddress(
                userId = 101,
                fullName = FullName("Dr. Alice", "Mary", "Wonderland"),
                imageUrl = "https://example.com/pharmacist_alice.jpg",
                addressGovernorate = "State A",
                addressCity = "Metropolis",
                addressRegion = "Uptown",
                addressStreet = "456 Oak Avenue",
                addressNote = "Apartment 3B",
                isSuspended = false,
                isResigned = false,
                acceptedBy = 1 ,
                gender = Gender.MALE,
                email = "aliahmad@gmail.com"
            ),
            workDays = createSampleWorkDayList(),
        )
    )

    // Pharmacy 2
    samplePharmacies.add(
        PharmacyDetailsResponse(
            pharmacyId = 2,
            phName = "Green Valley Rx",
            pharmacyAddress = Address(
                governorate = "State B",
                city = "Springfield",
                region = "Westside",
                street = "789 Pine Ln",
                note = "Next to the bakery"
            ),
            phoneNumber = "555-0202",
            isDeactivated = true,
            deactivationReason = null,
            contractStartDate = LocalDate.of(2024, 3, 1),
            contractEndDate = null, // Open-ended contract
            createdAt = LocalDateTime.of(2024, 2, 10, 8, 30, 0),
            updatedAt = LocalDateTime.of(2024, 6, 15, 11, 0, 0),
            deactivatedBy = null,
            pharmacistId = 102,
            userWithAddress = UserWithAddress(
                userId = 102,
                fullName = FullName("Bob", null, "The Pharmacist"),
                imageUrl = null, // No image
                addressGovernorate = "State B",
                addressCity = "Springfield",
                addressRegion = "North End",
                addressStreet = "101 Maple Drive",
                addressNote = "Blue house",
                isSuspended = true,
                isResigned = false,
                acceptedBy = null,
                gender = Gender.FEMALE,
                email = "mariam@gmail.com"
            ),
            workDays = createSampleWorkDayList(),
        )
    )

    // Pharmacy 3 - Deactivated
    samplePharmacies.add(
        PharmacyDetailsResponse(
            pharmacyId = 3,
            phName = "Old Town Medications",
            pharmacyAddress = Address(
                governorate = "State C",
                city = "Old Town",
                region = "Historic District",
                street = "321 Cobblestone Rd",
                note = null
            ),
            phoneNumber = "555-0303",
            isDeactivated = true,
            deactivationReason = "Business closed permanently.",
            contractStartDate = LocalDate.of(2020, 5, 1),
            contractEndDate = LocalDate.of(2023, 5, 1),
            createdAt = LocalDateTime.of(2020, 4, 1, 9, 0, 0),
            updatedAt = LocalDateTime.of(2023, 5, 10, 17, 0, 0),
            deactivatedBy = 2, // Admin ID
            pharmacistId = 103,
            userWithAddress = UserWithAddress(
                userId = 103,
                fullName = FullName("Carol", "Anne", "Pillsbury"),
                imageUrl = "https://example.com/pharmacist_carol.jpg",
                addressGovernorate = "State C",
                addressCity = "New City",
                addressRegion = "Suburbia",
                addressStreet = "AAA- Street",
                addressNote = "Opposite of university",
                isSuspended = true,
                isResigned = true,
                acceptedBy = 1,
                gender = Gender.MALE,
                email = "alimansoura@gmail.com",
            ),
            workDays = createSampleWorkDayList(),
        )
    )
    return samplePharmacies
}