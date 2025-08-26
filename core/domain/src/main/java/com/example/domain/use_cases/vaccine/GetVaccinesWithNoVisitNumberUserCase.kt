package com.example.domain.use_cases.vaccine

import com.example.domain.repositories.vaccine.VaccineRepository
import com.example.model.vaccine.VaccineMainInfo
import com.example.utility.network.Result
import com.example.utility.network.rootError


class GetVaccinesWithNoVisitNumberUserCase(
    private val vaccineRepository: VaccineRepository
) {
    suspend operator fun invoke(): Result<List<VaccineMainInfo>, rootError> {
        return vaccineRepository.getVaccinesWithNoVisitNumber()
    }
}