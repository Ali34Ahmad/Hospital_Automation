package com.example.domain.use_cases.vaccine

import com.example.domain.repositories.vaccine.VaccineRepository
import com.example.model.enums.Role
import com.example.model.vaccine.GenericVaccinationTable
import com.example.model.vaccine.VaccineData
import com.example.utility.network.Result
import com.example.utility.network.rootError

class GetGenericVaccinationTableUseCase(
    private val vaccineRepository: VaccineRepository
) {
    suspend operator fun invoke(): Result<GenericVaccinationTable, rootError> {
        return vaccineRepository.getGenericVaccinationTable()
    }
}