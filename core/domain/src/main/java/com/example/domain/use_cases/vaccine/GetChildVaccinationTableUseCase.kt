package com.example.domain.use_cases.vaccine

import com.example.domain.repositories.vaccine.VaccineRepository
import com.example.model.vaccine.ChildVaccinationTableData
import com.example.model.vaccine.GenericVaccinationTable
import com.example.utility.network.Result
import com.example.utility.network.NetworkError

class GetChildVaccinationTableUseCase(
    private val vaccineRepository: VaccineRepository
) {
    suspend operator fun invoke(childId:Int): Result<ChildVaccinationTableData, NetworkError> {
        return vaccineRepository.getChildVaccinationTable(childId)
    }
}