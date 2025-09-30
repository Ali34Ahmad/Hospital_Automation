package com.example.ui_components.components.tables.vaccination_table.child_table

import com.example.model.enums.VaccineStatus
import com.example.model.user.FullName
import com.example.model.user.UserMainInfo
import com.example.model.vaccine.ChildVaccinationTableData
import com.example.model.vaccine.ChildVaccinationTableVisit
import com.example.model.vaccine.VaccinationTableItemDetails
import com.example.model.vaccine.VaccineMainInfo
import com.example.model.vaccine.VaccineWithVaccinationTableDetails
import java.time.LocalDate

fun createChildVaccinationTableDataSample(): ChildVaccinationTableData {
    return ChildVaccinationTableData(
        visitNumbersToVaccines = listOf(
            ChildVaccinationTableVisit(
                visitNumber = 1,
                vaccinesWithVaccinationTableDetails = listOf(
                    VaccineWithVaccinationTableDetails(
                        vaccineMainInfo = VaccineMainInfo(id = 1, name = "Rubella"),
                        vaccinationTableItemDetails = VaccinationTableItemDetails(
                            vaccineStatus = VaccineStatus.PASSED,
                            vaccinationDate = LocalDate.of(2024, 1, 11),
                            administratedBy = UserMainInfo(
                                id = 101,
                                fullName = FullName("Ali", "Shafik", "Ahmad"),
                                imageUrl = null,
                                subInfo = null
                            ),
                            nextVisitDate = LocalDate.of(2024, 2, 11),
                            isAvailableNow = true,
                            appointmentId = 1,
                        )
                    ),
                    VaccineWithVaccinationTableDetails(
                        vaccineMainInfo = VaccineMainInfo(id = 2, name = "Smallpox"),
                        vaccinationTableItemDetails = VaccinationTableItemDetails(
                            vaccineStatus = VaccineStatus.MISSED,
                            vaccinationDate = null,
                            administratedBy = null,
                            nextVisitDate = LocalDate.of(2024, 2, 11),
                            isAvailableNow = false,
                            appointmentId = null,
                        )
                    ),
                    VaccineWithVaccinationTableDetails(
                        vaccineMainInfo = VaccineMainInfo(id = 3, name = "Quadrivalent"),
                        vaccinationTableItemDetails = VaccinationTableItemDetails(
                            vaccineStatus = VaccineStatus.NOT_SPECIFIED,
                            vaccinationDate = null,
                            administratedBy = null,
                            nextVisitDate = null,
                            isAvailableNow = false,
                            appointmentId = null,
                        )
                    )
                )
            ),
            ChildVaccinationTableVisit(
                visitNumber = 2,
                vaccinesWithVaccinationTableDetails = listOf(
                    VaccineWithVaccinationTableDetails(
                        vaccineMainInfo = VaccineMainInfo(id = 4, name = "Zero polio"),
                        vaccinationTableItemDetails = VaccinationTableItemDetails(
                            vaccineStatus = VaccineStatus.NOT_SPECIFIED,
                            vaccinationDate = null,
                            administratedBy = null,
                            nextVisitDate = null,
                            isAvailableNow = true,
                            appointmentId = null
                        )
                    ),
                    VaccineWithVaccinationTableDetails(
                        vaccineMainInfo = VaccineMainInfo(id = 5, name = "Adult bivalent 1"),
                        vaccinationTableItemDetails = VaccinationTableItemDetails(
                            vaccineStatus = VaccineStatus.NOT_SPECIFIED,
                            vaccinationDate = null,
                            administratedBy = null,
                            nextVisitDate = null,
                            isAvailableNow = true,
                            appointmentId = null
                        )
                    )
                )
            ),
            ChildVaccinationTableVisit(
                visitNumber = 3,
                vaccinesWithVaccinationTableDetails = listOf(
                    VaccineWithVaccinationTableDetails(
                        vaccineMainInfo = VaccineMainInfo(id = 6, name = "Oral polio 1"),
                        vaccinationTableItemDetails = VaccinationTableItemDetails(
                            vaccineStatus = VaccineStatus.MISSED,
                            vaccinationDate = null,
                            administratedBy = null,
                            nextVisitDate = null,
                            isAvailableNow = false,
                            appointmentId = null,
                        )
                    ),
                )
            )
        )
    )
}