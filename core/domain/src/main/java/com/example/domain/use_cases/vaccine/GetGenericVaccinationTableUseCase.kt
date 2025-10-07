package com.example.domain.use_cases.vaccine

import com.example.domain.repositories.vaccine.VaccineRepository
import com.example.model.vaccine.GenericVaccinationTableData
import com.example.utility.network.Result
import com.example.utility.network.NetworkError

class GetGenericVaccinationTableUseCase(
    private val vaccineRepository: VaccineRepository
) {
    suspend operator fun invoke(): Result<GenericVaccinationTableData, NetworkError> {
        return vaccineRepository.getGenericVaccinationTable()
    }
}