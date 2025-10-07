package com.example.ui.fake

import com.example.model.vaccine.VaccinationTableVisit
import com.example.model.vaccine.VaccineMainInfo

fun createFakeVaccinationData(): List<VaccinationTableVisit> {
    return listOf(
        VaccinationTableVisit(
            visitNumber = 1,
            vaccines = listOf(
                VaccineMainInfo(id = 1, name = "Vaccine A"),
                VaccineMainInfo(id = 2, name = "Vaccine B"),
                VaccineMainInfo(id = 3, name = "Vaccine C"),
            )
        ),
        VaccinationTableVisit(
            visitNumber = 2,
            vaccines = listOf(
                VaccineMainInfo(id = 4, name = "Vaccine A"),
            )
        ),
        VaccinationTableVisit(
            visitNumber = 3,
            vaccines = listOf(
                VaccineMainInfo(id = 5, name = "Vaccine A"),
                VaccineMainInfo(id = 6, name = "Vaccine B"),
                VaccineMainInfo(id = 7, name = "Vaccine C"),
            ),
        ),
        VaccinationTableVisit(
            visitNumber = 4,
            vaccines = listOf(),
        ),
    )
}