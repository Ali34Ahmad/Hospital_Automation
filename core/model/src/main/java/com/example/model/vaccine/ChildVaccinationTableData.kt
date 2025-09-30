package com.example.model.vaccine

data class ChildVaccinationTableData(
    val visitNumbersToVaccines: List<ChildVaccinationTableVisit>
)

data class ChildVaccinationTableVisit(
    val visitNumber: Int,
    val vaccinesWithVaccinationTableDetails: List<VaccineWithVaccinationTableDetails>
)

data class VaccineWithVaccinationTableDetails(
    val vaccineMainInfo:VaccineMainInfo,
    val vaccinationTableItemDetails:VaccinationTableItemDetails,
)