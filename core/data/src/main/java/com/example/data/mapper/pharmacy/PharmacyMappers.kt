package com.example.data.mapper.pharmacy

import com.example.data.mapper.day_schedule.toDaySchedule
import com.example.data.mapper.enums.toGender
import com.example.model.address.Address
import com.example.model.pharmacy.PharmacistData
import com.example.model.pharmacy.PharmacyData
import com.example.model.pharmacy.PharmacyDetailsResponse
import com.example.model.pharmacy.UserWithAddress
import com.example.model.user.FullName
import com.example.network.model.dto.pharmacy.PharmacyDto
import com.example.network.model.dto.user.UserDto
import com.example.network.model.response.pharmacy.PharmacyDetailsResponseDto
import com.example.network.model.response.pharmacy.UserWithAddressDto

fun PharmacyDto.toPharmacyData() = PharmacyData(
    pharmacyId = pharmacyId,
    name = name,
    addressGovernorate = addressGovernorate,
    addressCity = addressCity,
    addressRegion =addressRegion,
    addressStreet = addressStreet,
    addressNote = addressNote,
    phoneNumber = phone,
    isDeactivated = isDeactivated == true,
    deactivationReason = deactivationReason,
    startDate = startDate,
    endDate =endDate,
    deactivatedBy = deactivatedBy,
    pharmacist = user.toPharmacistData()
)
fun UserDto.toPharmacistData() = PharmacistData(
    id = userId,
    firstName = firstName,
    middleName = middleName,
    lastName = lastName,
    imageUrl = imageUrl
)


fun PharmacyDetailsResponseDto.toPharmacyDetails() =
    PharmacyDetailsResponse(
        pharmacyId = data.pharmacyId,
        phName = data.phName,
        pharmacyAddress = Address(
            governorate = data.addressGovernorate,
            city = data.addressCity,
            region = data.addressRegion,
            street = data.addressStreet,
            note = data.addressNote
        ),
        phoneNumber = data.phoneNumber,
        isDeactivated = data.isDeactivated,
        deactivationReason = data.deactivationReason,
        contractStartDate = data.contractStartDate,
        contractEndDate = data.contractEndDate,
        createdAt = data.createdAt,
        updatedAt = data.updatedAt,
        deactivatedBy = data.deactivatedBy,
        pharmacistId = data.pharmacyId,
        userWithAddress = data.userWithAddress.toUserWithAddress(),
        workDays = data.workDays.map { it.toDaySchedule() }
    )

fun UserWithAddressDto.toUserWithAddress() =
    UserWithAddress(
        userId = userId,
        fullName = FullName(
            firstName = firstName,
            middleName = middleName,
            lastName = lastName
        ),
        imageUrl = imageUrl,
        addressGovernorate = addressGovernorate,
        addressCity = addressCity,
        addressRegion = addressRegion,
        addressStreet = addressStreet,
        addressNote = addressNote,
        isSuspended = isSuspended,
        isResigned = isResigned,
        acceptedBy = acceptedBy,
        gender = genderDto.toGender(),
        email = email,
    )