package com.example.data.mapper.employment_history

import com.example.data.mapper.user.toUserMainInfo
import com.example.model.employment_history.PharmacyInHistory
import com.example.model.employment_history.PharmacistInHistoryData
import com.example.model.employment_history.PharmacyContractResponse
import com.example.network.model.response.profile.PharmacyInHistoryDto
import com.example.network.model.response.profile.PharmacistInHistoryDataDto
import com.example.network.model.response.profile.PharmacyContractHistoryDto
import com.example.network.utility.ApiRoutes

fun PharmacyContractHistoryDto.toPharmacyContractResponse(): PharmacyContractResponse {
    return PharmacyContractResponse(
        pharmacyInHistory = this.pharmacyInHistoryDto?.toCurrentPharmacy(),
        acceptedBy = this.acceptedBy?.toUserMainInfo(),
        fileSize = this.fileSize,
        deactivatedBy = this.deactivatedBy?.toUserMainInfo()
    )
}

fun PharmacyInHistoryDto.toCurrentPharmacy(): PharmacyInHistory {
    return PharmacyInHistory(
        id = this.id,
        phName = this.phName,
        isDeactivated = this.isDeactivated,
        deactivationReason = this.deactivationReason,
        contractStartDate = this.contractStartDate,
        contractEndDate = this.contractEndDate,
        deactivatedBy = this.deactivatedBy,
        pharmacistId = this.pharmacistId,
        pharmacist = this.pharmacist?.toPharmacistInHistoryData()
    )
}

fun PharmacistInHistoryDataDto.toPharmacistInHistoryData()=
    PharmacistInHistoryData(
        userId = this.userId,
        firstName = this.firstName,
        middleName = this.middleName,
        lastName = this.lastName,
        acceptedBy = this.acceptedBy,
        pharmacyFileUrl = "${ApiRoutes.BASE_URL}/${this.pharmacyFileUrl}"
    )