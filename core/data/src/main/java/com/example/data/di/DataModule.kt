package com.example.data.di

import com.example.data.repositories.admin.clinic.AdminClinicRepositoryImp
import com.example.data.repositories.admin.doctor.AdminDoctorRepositoryImp
import com.example.data.repositories.admin.employee.AdminEmployeeRepositoryImp
import com.example.data.repositories.admin.pharmacy.AdminPharmacyRepositoryImp
import com.example.data.repositories.admin_profile.AdminProfileRepositoryImpl
import com.example.data.repositories.appointment.AppointmentsRepositoryImp
import com.example.data.repositories.appointment_type.AppointmentTypeRepositoryImp
import com.example.data.repositories.auth.AuthRepositoryImpl
import com.example.data.repositories.child.ChildRepositoryImp
import com.example.data.repositories.clinic.ClinicRepositoryImp
import com.example.data.repositories.doctor.DoctorProfileRepositoryImpl
import com.example.data.repositories.download_file.DownloadFileRepositoryImpl
import com.example.data.repositories.employee_account_management.UserAccountManagementRepositoryImpl
import com.example.data.repositories.employee_profile.EmployeeProfileRepositoryImpl
import com.example.data.repositories.employment_history.EmploymentHistoryRepositoryImpl
import com.example.data.repositories.medical_record.MedicalRecordRepositoryImpl
import com.example.data.repositories.medicine.MedicineRepositoryImp
import com.example.data.repositories.pharmacy.PharmacyRepositoryImp
import com.example.data.repositories.prescription.PrescriptionRepositoryImp
import com.example.data.repositories.residential_address.AddResidentialAddressRepositoryImpl
import com.example.data.repositories.upload_child_file.UploadChildDocumentsRepositoryImpl
import com.example.data.repositories.upload_employee_file.UploadEmploymentDocumentsRepositoryImpl
import com.example.data.repositories.upload_employee_image.UploadEmployeeProfileImageRepositoryImpl
import com.example.data.repositories.user.UserRepositoryImp
import com.example.data.repositories.user_preferences.UserPreferencesRepositoryImpl
import com.example.data.repositories.vaccine.VaccineRepositoryImpl
import com.example.data.repositories.work_day.WorkDayRepositoryImp
import com.example.data.repositories.work_request.WorkRequestRepositoryImp
import com.example.datastore.di.dataStoreModule
import com.example.domain.di.domainModules.domainModule
import com.example.domain.repositories.AddResidentialAddressRepository
import com.example.domain.repositories.AppointmentTypeRepository
import com.example.domain.repositories.AppointmentsRepository
import com.example.domain.repositories.ChildRepository
import com.example.domain.repositories.ClinicRepository
import com.example.domain.repositories.MedicineRepository
import com.example.domain.repositories.UserRepository
import com.example.domain.repositories.WorkDayRepository
import com.example.domain.repositories.account_management.UserAccountManagementRepository
import com.example.domain.repositories.admin.clinic.AdminClinicRepository
import com.example.domain.repositories.admin.doctor.AdminDoctorRepository
import com.example.domain.repositories.admin.employee.AdminEmployeeRepository
import com.example.domain.repositories.admin.pharmacy.AdminPharmacyRepository
import com.example.domain.repositories.auth.AuthRepository
import com.example.domain.repositories.file.DownloadFileRepository
import com.example.domain.repositories.file.UploadChildDocumentsRepository
import com.example.domain.repositories.file.UploadEmployeeProfileImageRepository
import com.example.domain.repositories.file.UploadEmploymentDocumentsRepository
import com.example.domain.repositories.local.UserPreferencesRepository
import com.example.domain.repositories.medical_record.MedicalRecordRepository
import com.example.domain.repositories.pharmacy.PharmacyRepository
import com.example.domain.repositories.prescription.PrescriptionRepository
import com.example.domain.repositories.profile.AdminProfileRepository
import com.example.domain.repositories.profile.DoctorProfileRepository
import com.example.domain.repositories.profile.EmployeeProfileRepository
import com.example.domain.repositories.profile.EmploymentHistoryRepository
import com.example.domain.repositories.vaccine.VaccineRepository
import com.example.domain.repositories.work_request.WorkRequestRepository
import com.example.network.di.networkModule
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataModule = module{
    includes(
        dataStoreModule,
        networkModule,
        domainModule,
    )

    // Admin
    singleOf(::AdminDoctorRepositoryImp) { bind<AdminDoctorRepository>() }
    singleOf(::AdminEmployeeRepositoryImp) { bind<AdminEmployeeRepository>() }
    singleOf(::AdminClinicRepositoryImp) { bind<AdminClinicRepository>() }
    singleOf(::AdminPharmacyRepositoryImp) { bind<AdminPharmacyRepository>() }

    // Doctor
    singleOf(::MedicalRecordRepositoryImpl) { bind<MedicalRecordRepository>() }

    // Employee
    singleOf(::UploadChildDocumentsRepositoryImpl) { bind<UploadChildDocumentsRepository>() }

    // Shared Repositories
    singleOf(::AuthRepositoryImpl) { bind<AuthRepository>() }
    singleOf(::AddResidentialAddressRepositoryImpl) { bind<AddResidentialAddressRepository>() }
    singleOf(::UploadEmployeeProfileImageRepositoryImpl) { bind<UploadEmployeeProfileImageRepository>() }
    singleOf(::VaccineRepositoryImpl) { bind<VaccineRepository>() }
    singleOf(::DoctorProfileRepositoryImpl) { bind<DoctorProfileRepository>() }
    singleOf(::EmployeeProfileRepositoryImpl) { bind<EmployeeProfileRepository>() }
    singleOf(::PharmacyRepositoryImp) { bind<PharmacyRepository>() }
    singleOf(::EmploymentHistoryRepositoryImpl) { bind<EmploymentHistoryRepository>() }
    singleOf(::PrescriptionRepositoryImp) { bind<PrescriptionRepository>() }
    singleOf(::AdminProfileRepositoryImpl) { bind<AdminProfileRepository>() }
    singleOf(::UserPreferencesRepositoryImpl) { bind<UserPreferencesRepository>() }
    singleOf(::ChildRepositoryImp) { bind<ChildRepository>() }
    singleOf(::UserRepositoryImp) { bind<UserRepository>() }
    singleOf(::MedicineRepositoryImp) { bind<MedicineRepository>() }
    singleOf(::UserAccountManagementRepositoryImpl) { bind<UserAccountManagementRepository>() }
    singleOf(::ClinicRepositoryImp) { bind<ClinicRepository>() }
    singleOf(::AppointmentsRepositoryImp) { bind<AppointmentsRepository>() }
    singleOf(::WorkRequestRepositoryImp) { bind<WorkRequestRepository>() }
    singleOf(::DownloadFileRepositoryImpl) { bind<DownloadFileRepository>() }
    singleOf(::UploadEmploymentDocumentsRepositoryImpl) { bind<UploadEmploymentDocumentsRepository>() }
    singleOf(::AppointmentTypeRepositoryImp){ bind<AppointmentTypeRepository>()}
    singleOf(::WorkDayRepositoryImp){ bind<WorkDayRepository>()}

}