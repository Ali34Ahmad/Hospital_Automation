package com.example.domain.use_cases.vaccine

import com.example.domain.repositories.vaccine.VaccineRepository
import com.example.model.vaccine.GenericVaccinationTable
import com.example.model.vaccine.VaccineIdToVisitNumber
import com.example.utility.network.Result
import com.example.utility.network.NetworkError

class UpdateVaccineVisitNumberUseCase(
    private val vaccineRepository: VaccineRepository
) {
    suspend operator fun invoke(vaccineIdToVisitNumber: VaccineIdToVisitNumber): Result<GenericVaccinationTable, NetworkError> {
        return vaccineRepository.updateVaccineVisitNumber(vaccineIdToVisitNumber)
    }
}