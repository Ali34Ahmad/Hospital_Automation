package com.example.domain.use_cases.vaccine

import com.example.domain.repositories.vaccine.VaccineRepository
import com.example.model.enums.Role
import com.example.model.vaccine.VaccineData
import com.example.utility.network.Result
import com.example.utility.network.NetworkError

class GetVaccineByIdUseCase(
    private val vaccineRepository: VaccineRepository
) {
    suspend operator fun invoke(id: Int,role: Role): Result<VaccineData, NetworkError> {
        return vaccineRepository.getVaccineById(id=id, role = role)
    }
}