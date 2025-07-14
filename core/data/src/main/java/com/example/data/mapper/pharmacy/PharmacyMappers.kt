package com.example.data.mapper.pharmacy

import com.example.model.pharmacy.PharmacistData
import com.example.model.pharmacy.PharmacyData
import com.example.network.model.dto.pharmacy.PharmacyDto
import com.example.network.model.dto.user.UserDto

fun PharmacyDto.toPharmacyData() = PharmacyData(
    pharmacyId = pharmacyId,
    name = name,
    addressGovernorate = addressGovernorate,
    addressCity = addressCity,
    addressRegion =addressRegion,
    addressStreet = addressStreet,
    addressNote = addressNote,
    phoneNumber = phoneNumber,
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