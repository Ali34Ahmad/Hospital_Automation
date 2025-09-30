package com.example.domain.use_cases.vaccine

import com.example.domain.repositories.vaccine.VaccineRepository
import com.example.model.vaccine.VaccineData
import com.example.utility.network.Result
import com.example.utility.network.NetworkError

class AddNewVaccineUseCase(
    private val vaccineRepository: VaccineRepository
) {
    suspend operator fun invoke(vaccineData: VaccineData): Result<VaccineData, NetworkError> {
        return vaccineRepository.addNewVaccine(vaccineData = vaccineData)
    }
}