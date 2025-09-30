package com.example.domain.use_cases.vaccine

import com.example.domain.repositories.vaccine.VaccineRepository
import com.example.model.enums.Role
import com.example.model.vaccine.GenericVaccinationTable
import com.example.model.vaccine.VaccineData
import com.example.utility.network.Result
import com.example.utility.network.NetworkError

class GetGenericVaccinationTableUseCase(
    private val vaccineRepository: VaccineRepository
) {
    suspend operator fun invoke(): Result<GenericVaccinationTable, NetworkError> {
        return vaccineRepository.getGenericVaccinationTable()
    }
}