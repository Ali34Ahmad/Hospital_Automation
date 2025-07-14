package com.example.data.di

import com.example.data.repositories.admin_profile.AdminProfileRepositoryImpl
import com.example.data.repositories.appointment.AppointmentsRepositoryImp
import com.example.data.repositories.auth.AuthRepositoryImpl
import com.example.data.repositories.auth.signup.DoctorSignUpRepositoryImpl
import com.example.data.repositories.clinic.ClinicRepositoryImp
import com.example.data.repositories.doctor.DoctorProfileRepositoryImpl
import com.example.data.repositories.download_file.DownloadFileRepositoryImpl
import com.example.data.repositories.employee_account_management.EmployeeAccountManagementRepositoryImpl
import com.example.data.repositories.employment_history.EmploymentHistoryRepositoryImpl
import com.example.data.repositories.medicine.MedicineRepositoryImp
import com.example.data.repositories.pharmacy.PharmacyRepositoryImp
import com.example.data.repositories.residential_address.AddResidentialAddressRepositoryImpl
import com.example.data.repositories.upload_employee_file.UploadEmploymentDocumentsRepositoryImpl
import com.example.data.repositories.upload_employee_image.UploadEmployeeProfileImageRepositoryImpl
import com.example.data.repositories.user_preferences.UserPreferencesRepositoryImpl
import com.example.data.repositories.vaccine.VaccineRepositoryImpl
import com.example.data.repositories.prescription.PrescriptionRepositoryImp
import com.example.data.repositories.work_request.WorkRequestRepositoryImp
import com.example.datastore.di.dataStoreModule
import com.example.domain.di.domainModules.doctorDomainModule
import com.example.domain.repositories.AddResidentialAddressRepository
import com.example.domain.repositories.AppointmentsRepository
import com.example.domain.repositories.ClinicRepository
import com.example.domain.repositories.MedicineRepository
import com.example.domain.repositories.account_management.EmployeeAccountManagementRepository
import com.example.domain.repositories.auth.AuthRepository
import com.example.domain.repositories.auth.singup.DoctorSignUpRepository
import com.example.domain.repositories.file.DownloadFileRepository
import com.example.domain.repositories.file.UploadEmployeeProfileImageRepository
import com.example.domain.repositories.file.UploadEmploymentDocumentsRepository
import com.example.domain.repositories.local.UserPreferencesRepository
import com.example.domain.repositories.pharmacy.PharmacyRepository
import com.example.domain.repositories.profile.AdminProfileRepository
import com.example.domain.repositories.profile.DoctorProfileRepository
import com.example.domain.repositories.profile.EmploymentHistoryRepository
import com.example.domain.repositories.vaccine.VaccineRepository
import com.example.domain.repositories.prescription.PrescriptionRepository
import com.example.domain.repositories.work_request.WorkRequestRepository
import com.example.network.di.doctorNetworkModule
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val doctorDataModule = module {
    includes(doctorNetworkModule, dataStoreModule, doctorDomainModule,sharedDataModule)
    singleOf(::PrescriptionRepositoryImp){bind<PrescriptionRepository>()}
    singleOf(::PharmacyRepositoryImp){bind<PharmacyRepository>()}
    singleOf(::MedicineRepositoryImp){bind<MedicineRepository>()}
    singleOf(::WorkRequestRepositoryImp){bind<WorkRequestRepository>()}
    singleOf(::ClinicRepositoryImp) { bind<ClinicRepository>() }
    singleOf(::AppointmentsRepositoryImp){bind<AppointmentsRepository>()}
    singleOf(::DoctorSignUpRepositoryImpl) { bind<DoctorSignUpRepository>() }
    singleOf(::DoctorSignUpRepositoryImpl) { bind<DoctorSignUpRepository>() }

    singleOf(::AuthRepositoryImpl) { bind<AuthRepository>() }

    singleOf(::DoctorProfileRepositoryImpl) { bind<DoctorProfileRepository>() }

    singleOf(::AddResidentialAddressRepositoryImpl) { bind<AddResidentialAddressRepository>() }

    singleOf(::UploadEmploymentDocumentsRepositoryImpl) { bind<UploadEmploymentDocumentsRepository>() }

    singleOf(::UploadEmployeeProfileImageRepositoryImpl) { bind<UploadEmployeeProfileImageRepository>() }

    singleOf(::EmploymentHistoryRepositoryImpl) { bind<EmploymentHistoryRepository>() }

    singleOf(::AdminProfileRepositoryImpl) { bind<AdminProfileRepository>() }

    singleOf(::DownloadFileRepositoryImpl) { bind<DownloadFileRepository>() }

    singleOf(::UserPreferencesRepositoryImpl) { bind<UserPreferencesRepository>() }

    singleOf(::EmployeeAccountManagementRepositoryImpl) { bind<EmployeeAccountManagementRepository>() }

    singleOf(::VaccineRepositoryImpl) { bind<VaccineRepository>() }


}