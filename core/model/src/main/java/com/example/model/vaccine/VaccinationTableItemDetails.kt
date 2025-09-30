package com.example.model.vaccine

import com.example.model.enums.VaccineStatus
import com.example.model.user.UserMainInfo
import java.time.LocalDate

data class VaccinationTableItemDetails(
    val vaccineStatus: VaccineStatus,
    val vaccinationDate: LocalDate?,
    val administratedBy: UserMainInfo?,
    val nextVisitDate: LocalDate?,
    val isAvailableNow:Boolean,
    val appointmentId:Int?,
)
