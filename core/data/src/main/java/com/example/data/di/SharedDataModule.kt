package com.example.data.di

import com.example.data.repositories.appointment.AppointmentsRepositoryImp
import com.example.data.repositories.child.ChildRepositoryImp
import com.example.data.repositories.clinic.ClinicRepositoryImp
import com.example.data.repositories.download_file.DownloadFileRepositoryImpl
import com.example.data.repositories.employee_account_management.EmployeeAccountManagementRepositoryImpl
import com.example.data.repositories.medicine.MedicineRepositoryImp
import com.example.data.repositories.user.UserRepositoryImp
import com.example.data.repositories.user_preferences.UserPreferencesRepositoryImpl
import com.example.data.repositories.work_request.WorkRequestRepositoryImp
import com.example.datastore.di.dataStoreModule
import com.example.domain.repositories.AppointmentsRepository
import com.example.domain.repositories.ChildRepository
import com.example.domain.repositories.ClinicRepository
import com.example.domain.repositories.MedicineRepository
import com.example.domain.repositories.UserRepository
import com.example.domain.repositories.account_management.EmployeeAccountManagementRepository
import com.example.domain.repositories.file.DownloadFileRepository
import com.example.domain.repositories.local.UserPreferencesRepository
import com.example.domain.repositories.work_request.WorkRequestRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val commonDataModule = module{
    includes(
        dataStoreModule,
        )
    singleOf(::UserPreferencesRepositoryImpl) { bind<UserPreferencesRepository>() }
    singleOf(::EmployeeAccountManagementRepositoryImpl) { bind<EmployeeAccountManagementRepository>() }
    singleOf(::ChildRepositoryImp){bind<ChildRepository>()}

    singleOf(::ClinicRepositoryImp) { bind<ClinicRepository>() }
    singleOf(::AppointmentsRepositoryImp){bind<AppointmentsRepository>()}
    singleOf(::WorkRequestRepositoryImp){bind<WorkRequestRepository>()}
    singleOf(::UserRepositoryImp){bind<UserRepository>()}
    singleOf(::EmployeeAccountManagementRepositoryImpl) { bind<EmployeeAccountManagementRepository>() }
    singleOf(::DownloadFileRepositoryImpl) { bind<DownloadFileRepository>() }
    singleOf(::MedicineRepositoryImp){bind<MedicineRepository>()}

}