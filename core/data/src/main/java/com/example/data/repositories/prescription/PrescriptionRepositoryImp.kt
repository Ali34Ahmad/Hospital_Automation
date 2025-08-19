package com.example.data.repositories.prescription

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.mapper.enums.toRoleDto
import com.example.data.mapper.prescription.toPrescriptionDetails
import com.example.data.mapper.prescription.toPrescriptionMedicineDto
import com.example.data.paging_sources.medical_prescription.MedicalPrescriptionPagingSource
import com.example.domain.model.constants.PagingConstants
import com.example.domain.repositories.local.UserPreferencesRepository
import com.example.domain.repositories.prescription.PrescriptionRepository
import com.example.model.prescription.PrescriptionMedicineData
import com.example.model.prescription.PrescriptionWithUser
import com.example.model.role_config.RoleAppConfig
import com.example.network.remote.prescription.PrescriptionApiService
import com.example.utility.network.map
import kotlinx.coroutines.flow.Flow

class PrescriptionRepositoryImp(
    private val apiService: PrescriptionApiService,
    private val userPreferencesRepository: UserPreferencesRepository,
    private val roleAppConfig: RoleAppConfig,
)
    : PrescriptionRepository{
    override suspend fun addPrescription(
        childId: Int?,
        patientId: Int?,
        note: String,
        medicines: List<PrescriptionMedicineData>,
    ) = userPreferencesRepository.executeWithValidTokenNetwork { token->
        apiService.addPrescription(
            token = token,
            patientId = patientId,
            childId = childId,
            note = note,
            medicines = medicines.map { it.toPrescriptionMedicineDto() }
        ).map { it.prescription.id }
    }

    override suspend fun getAllMedicalPrescriptionsForCurrentDoctor(): Flow<PagingData<PrescriptionWithUser>> =
        userPreferencesRepository.executeFlowWithValidToken { token ->
            Pager(
                config = PagingConfig(
                    pageSize = PagingConstants.PAGE_SIZE
                ),
                pagingSourceFactory = {
                    MedicalPrescriptionPagingSource(
                        token = token,
                        medicalPrescriptionsApiService = apiService,
                        role=roleAppConfig.role.toRoleDto(),
                    )
                }
            ).flow
        }

    override suspend fun getPrescriptionDetailsById(id: Int) =
        userPreferencesRepository.executeWithValidToken { token ->
            apiService.getPrescriptionDetailsById(token,id,roleAppConfig.role.toRoleDto())
                .map { prescriptionDetailsDto ->
                    prescriptionDetailsDto.toPrescriptionDetails()
                }
        }


}