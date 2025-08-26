package com.example.domain.use_cases.vaccine

import com.example.domain.repositories.vaccine.VaccineRepository
import com.example.model.vaccine.GenericVaccinationTable
import com.example.model.vaccine.VaccinesIdsToVisitNumber
import com.example.utility.network.Result
import com.example.utility.network.rootError

class UpdateVaccinesVisitNumberUseCase(
    private val vaccineRepository: VaccineRepository
) {
    suspend operator fun invoke(vaccinesIdsToVisitNumber: VaccinesIdsToVisitNumber): Result<GenericVaccinationTable, rootError> {
        return vaccineRepository.updateVaccinesVisitNumber(vaccinesIdsToVisitNumber)
    }
}