package com.example.model.employment_history

import com.example.model.user.UserMainInfo
import java.time.LocalDate

data class PharmacyContractResponse(
    val pharmacyInHistory: PharmacyInHistory? = null,
    val acceptedBy: UserMainInfo? = null,
    val fileSize: Long? = null,
    val deactivatedBy: UserMainInfo? = null
)

data class PharmacyInHistory(
    val id: Int? = null,
    val phName: String? = null,

    val isDeactivated: Boolean? = null,
    val deactivationReason: String? = null,

    val contractStartDate: LocalDate? = null,
    val contractEndDate: LocalDate? = null,

    val deactivatedBy: String? = null,
    val pharmacistId: Int? = null,
    val pharmacist: PharmacistInHistoryData? = null
)

data class PharmacistInHistoryData(
    val userId: Int? = null,
    val firstName: String? = null,
    val middleName: String? = null,
    val lastName: String? = null,
    val acceptedBy: Int? = null,
    val pharmacyFileUrl: String? = null
)