package com.example.model.vaccine

data class UpdateVaccinationTableRequest(
    val updates:List<VaccinationTableUpdate>
)

data class VaccinationTableUpdate(
    val visitNumber:Int?,
    val vaccineId:Int,
)
