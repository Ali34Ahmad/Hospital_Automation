package com.example.domain.use_cases.vaccine

import com.example.domain.repositories.vaccine.VaccineRepository
import com.example.model.vaccine.GenericVaccinationTable
import com.example.model.vaccine.VaccineIdToVisitNumber
import com.example.utility.network.Result
import com.example.utility.network.rootError

class UpdateVaccineVisitNumberUseCase(
    private val vaccineRepository: VaccineRepository
) {
    suspend operator fun invoke(vaccineIdToVisitNumber: VaccineIdToVisitNumber): Result<GenericVaccinationTable, rootError> {
        return vaccineRepository.updateVaccineVisitNumber(vaccineIdToVisitNumber)
    }
}