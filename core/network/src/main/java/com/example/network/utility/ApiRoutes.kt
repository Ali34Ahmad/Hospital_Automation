package com.example.network.utility

import com.example.network.model.enums.RoleDto

object ApiRoutes {

    const val BASE_URL = "https://dispensary-hkz3.onrender.com"
    private const val EMPLOYEE = "$BASE_URL/employee"
    private const val DOCTOR = "$BASE_URL/doctor"
    private const val ADMIN = "$BASE_URL/admin"


    const val SEARCH_FOR_CHILD = "$EMPLOYEE/searchFor-child"
    const val ADD_CHILD = "$EMPLOYEE/addChild"
    const val UPLOAD_CHILD_CERTIFICATE = "$EMPLOYEE/upload-Child-Certificate"
    const val CHILD_BY_ID = "$EMPLOYEE/find-child-byId"
    const val CHILDREN_BY_NAME = "$EMPLOYEE/find-child-byname"
    const val CHILDREN_BY_GUARDIAN_ID = "$EMPLOYEE/show-children_for_guardian"
    const val CHILDREN_BY_EMPLOYEE_ID = "$EMPLOYEE/show-children-added-by-employee"
    const val SEARCH_FOR_CHILDREN_ADDED_BY_EMPLOYEE_BY_NAME =
        "$EMPLOYEE/search-children-added-by-employee"

    //users
    const val SHOW_USER_PROFILE = "$EMPLOYEE/find-user-byId"
    const val SEARCH_FOR_USER = "$EMPLOYEE/searchFor-user"
    const val GUARDIAN_FOR_CHILD = "$EMPLOYEE/guardian-for-ex-child"
    const val USERS_BY_NAME = "$EMPLOYEE/find-user-byname"
    const val GUARDIANS_BY_CHILD_ID = "$EMPLOYEE/show-guardian_for_child"

    const val EMPLOYEE_SIGNUP = "$EMPLOYEE/sign-up"
    const val EMPLOYEE_LOGIN = "$EMPLOYEE/login"
    const val EMPLOYEE_LOGOUT = "$EMPLOYEE/logout"
    const val EMPLOYEE_SEND_OTP = "$EMPLOYEE/send-otp"
    const val EMPLOYEE_VERIFY_OTP = "$EMPLOYEE/verify-otp"
    const val ADD_RESIDENTIAL_ADDRESS = "$EMPLOYEE/add-address"

    const val UPLOAD_EMPLOYMENT_DOCUMENTS = "$EMPLOYEE/upload-file"
    const val UPLOAD_PROFILE_IMAGE = "$EMPLOYEE/add-image"
    const val EMPLOYEE_PROFILE = "$EMPLOYEE/show-profile"
    const val FIND_EMPLOYEE_BY_ID = "$EMPLOYEE/find-employee-by-id"
    const val EMPLOYEE_RESET_PASSWORD = "$EMPLOYEE/reset-password"

    const val CHECK_EMPLOYEE_PERMISSION = "$EMPLOYEE/show-permissions"
    const val DEACTIVATE_MY_EMPLOYEE_ACCOUNT = "$EMPLOYEE/deactivate-my-account"
    const val REACTIVATE_MY_EMPLOYEE_ACCOUNT = "$EMPLOYEE/reactivate-my-account"
    const val EMPLOYEE_EMPLOYMENT_HISTORY = "$EMPLOYEE/employment-history"

    const val ADMIN_PROFILE_BY_ID = "$EMPLOYEE/find-admin-by-id"


    object Doctor {
        private const val DOCTOR = "$BASE_URL/doctor"
        const val ADD_MEDICAL_DIAGNOSIS = "$DOCTOR/add-medical-diagnosis"
        const val PROFILE = "$DOCTOR/show-profile"
        const val ADD_NEW_VACCINE = "$DOCTOR/add-new-vaccine"
        const val GET_VACCINE_BY_ID = "$DOCTOR/view-single-vaccine-details"
        const val GET_GENERIC_VACCINATION_TABLE = "$DOCTOR/get-generic-vaccination-table"
        const val UPDATE_GENERIC_VACCINATION_TABLE = "$DOCTOR/update-generic-vaccination-table"
        const val SIGNUP = "$DOCTOR/sign-up"
        const val LOGIN = "$DOCTOR/login"
        const val SEND_OTP = "$DOCTOR/send-otp"
        const val VERIFY_OTP = "$DOCTOR/verify-otp"
        const val LOGOUT = "$DOCTOR/logout"
        const val RESET_PASSWORD = "$DOCTOR/reset-password"
        const val ADD_RESIDENTIAL_ADDRESS = "$DOCTOR/add-address"
        const val UPLOAD_EMPLOYMENT_FILE = "$DOCTOR/upload-file"
        const val UPLOAD_PROFILE_IMAGE = "$DOCTOR/add-image"
        const val DEACTIVATE_MY_ACCOUNT = "$DOCTOR/deactivate-my-account"
        const val REACTIVATE_MY_ACCOUNT = "$DOCTOR/reactivate-my-account"
        const val EMPLOYMENT_HISTORY = "$DOCTOR/Doctor-employment-history"
        const val CHECK_PERMISSION = "$DOCTOR/show-permissions"
        const val SHOW_APPOINTMENTS = "$DOCTOR/show-appointments"
        const val SHOW_SINGLE_APPOINTMENT = "$DOCTOR/get-single-appointment-details"
        const val UPDATE_APPOINTMENT_STATE_TO_PASSED = "$DOCTOR/change-appointment-state-to-passed"
        const val UPDATE_APPOINTMENT_STATE_TO_MISSED = "$DOCTOR/change-appointment-state-to-missed"
        const val SEND_WORK_REQUEST = "$DOCTOR/send-doctor-request"
        const val GET_MEDICINES = "$DOCTOR/get-medicine-byname"
        const val GET_MEDICINE_BY_ID = "$DOCTOR/get-medicine-by-id"
        const val SHOW_ALL_CLINICS = "$DOCTOR/show-all-clinics"
        const val GET_CLINIC_BY_ID = "$DOCTOR/view-single-clinic-details"

        const val MEDICAL_PRESCRIPTIONS = "$DOCTOR/doctor_all_prescriptions"
        const val PRESCRIPTION_DETAILS = "$DOCTOR/prescription-details"

        const val ALL_VACCINES = "$DOCTOR/view-all-vaccines"
        const val UPDATE_VACCINE_VISIT_NUMBER = "$DOCTOR/update-vaccines-visit-number"
        const val UPDATE_VACCINES_LIST_VISIT_NUMBER = "$DOCTOR/update-vaccines-list-visit"

        const val PHARMACY_DETAILS = "$DOCTOR/pharmacy-details"
        const val ALL_MEDICAL_RECORDS = "$DOCTOR/doctor-record"

    }

    object Admin{
        const val CHILD_APPOINTMENTS = "$ADMIN/show-appointments-screen"
        const val USER_APPOINTMENTS = "$ADMIN/show-appointments-screen"
        const val MEDICINES_IN_SPECIFIC_PHARMACY = "$ADMIN/pharmacy-medicines"
        const val CHILDREN_ADDED_BY_EMPLOYEE = "$ADMIN/find-child-byname"
        const val DEACTIVATE_USER = "$ADMIN/change-anyone-state-resign-suspend"
        const val REACTIVATE_USER = "$ADMIN/reactivate-user"
        const val ADMIN_GET_PHARMACIES = "$ADMIN/get-departments-pharmacies-states"
        const val ADMIN_GET_CLINICS = "$ADMIN/get-departments-pharmacies-states"
        const val GET_ALL_EMPLOYEES = "$ADMIN/get-employees-states"
        const val GET_ALL_DOCTORS = "$ADMIN/get-employees-states"
        const val SHOW_DOCTOR_APPOINTMENTS = "$ADMIN/doctor-appointment-screen"
        const val GET_DOCTORS_IN_SPECIFIC_CLINIC ="$ADMIN/doctors-in-specific-clinic_states"
        const val DEACTIVATE_CLINIC = "$ADMIN/deactivate-clinic"
        const val REACTIVATE_CLINIC = "$ADMIN/reactivate-clinic"
        const val GET_ADMIN_DETAILS_FOR = "$ADMIN/admin-get-details-by-id-for"
        const val ALL_VACCINES = "$ADMIN/show-all-vaccines"
        const val GET_VACCINE_BY_ID = "$ADMIN/view-single-vaccine-details"
        const val MEDICAL_PRESCRIPTIONS = "$ADMIN/doctor_all_prescriptions"
        const val CHILDREN_BY_GUARDIAN_ID = "$ADMIN/show-children-for-user"
    }
    object Prescription{
        const val ADD_PRESCRIPTION = "$DOCTOR/add-prescription"
    }
    object Pharmacy{
        const val PHARMACIES_BY_MEDICINE_ID = "$DOCTOR/show-pharmacies-have-medicine"
    }

    //doctor : /doctor/show-appointments?params=Upcomming&limit=2&page=2&dateOrder
    //admin -> doctor : doctor-appointment-screen/143?limit=10&page=1&&params=Upcomming
    //admin -> {child,patient} : /admin/show-appointments-screen?limit=1&page=1&forWho=child&state=Upcomming&id=1&    !!!! dateOdred=ASC


    fun getDoctorAppointmentsEndPointFor(
        roleDto: RoleDto,
        ) = when(roleDto){
        RoleDto.DOCTOR -> Doctor.SHOW_APPOINTMENTS
        RoleDto.ADMIN -> Admin.SHOW_DOCTOR_APPOINTMENTS
        else -> ""
    }

    fun getLoginEndpointFor(role: RoleDto): String {
        return when (role) {
            RoleDto.EMPLOYEE -> EMPLOYEE_LOGIN
            RoleDto.DOCTOR -> Doctor.LOGIN
            RoleDto.ADMIN -> ""
            else -> ""
        }
    }

    fun getVerifyEmailEndpointFor(role: RoleDto): String {
        return when (role) {
            RoleDto.EMPLOYEE -> EMPLOYEE_VERIFY_OTP
            RoleDto.DOCTOR -> Doctor.VERIFY_OTP
            RoleDto.ADMIN -> ""
            else -> ""
        }
    }

    fun getSendOtpToEmailEndpointFor(role: RoleDto): String {
        return when (role) {
            RoleDto.EMPLOYEE -> EMPLOYEE_SEND_OTP
            RoleDto.DOCTOR -> Doctor.SEND_OTP
            RoleDto.ADMIN -> ""
            else -> ""
        }
    }
    fun getChildrenByEmployeeEndPointFor(role: RoleDto) =
        when(role){
            RoleDto.EMPLOYEE -> SEARCH_FOR_CHILDREN_ADDED_BY_EMPLOYEE_BY_NAME
            RoleDto.DOCTOR -> ""
            RoleDto.ADMIN -> Admin.CHILDREN_ADDED_BY_EMPLOYEE
            else -> ""
        }
    fun getMedicineByIdEndPointFor(role: RoleDto) : String =
        when(role){
            RoleDto.EMPLOYEE -> ""
            RoleDto.DOCTOR -> Doctor.GET_MEDICINE_BY_ID
            RoleDto.ADMIN -> Admin.GET_ADMIN_DETAILS_FOR
            else -> ""

        }

    fun getSingUpEndpointFor(role: RoleDto): String {
        return when (role) {
            RoleDto.EMPLOYEE -> EMPLOYEE_SIGNUP
            RoleDto.DOCTOR -> Doctor.SIGNUP
            RoleDto.ADMIN -> ""
            else -> ""

        }
    }

    fun getResetPasswordEndPointFor(role: RoleDto): String {
        return when (role) {
            RoleDto.EMPLOYEE -> EMPLOYEE_RESET_PASSWORD
            RoleDto.DOCTOR -> Doctor.RESET_PASSWORD
            RoleDto.ADMIN -> ""
            else -> ""

        }
    }

    fun getLogoutEndPointFor(role: RoleDto): String {
        return when (role) {
            RoleDto.EMPLOYEE -> EMPLOYEE_LOGOUT
            RoleDto.DOCTOR -> Doctor.LOGOUT
            RoleDto.ADMIN -> ""
            else -> ""

        }
    }

    fun getClinicByIdEndPointForRole(role: RoleDto) : String{
        return when(role){
            RoleDto.EMPLOYEE -> ""
            RoleDto.DOCTOR -> Doctor.GET_CLINIC_BY_ID
            RoleDto.ADMIN -> Admin.GET_ADMIN_DETAILS_FOR
            else -> ""

        }
    }

    fun getGuardianByIdEndPointForRole(role: RoleDto) : String=
        when(role){
            RoleDto.EMPLOYEE ->SHOW_USER_PROFILE
            RoleDto.DOCTOR -> ""
            RoleDto.ADMIN -> Admin.GET_ADMIN_DETAILS_FOR
            else -> ""

        }



    fun getChildrenByUserEndPointFor(role: RoleDto) =
        when(role){
            RoleDto.EMPLOYEE -> CHILDREN_BY_GUARDIAN_ID
            RoleDto.DOCTOR -> ""
            RoleDto.ADMIN -> Admin.CHILDREN_BY_GUARDIAN_ID
            else -> ""

        }
        fun getChildByIdEndPointFor(role: RoleDto): String =
            when (role) {
                RoleDto.EMPLOYEE -> CHILD_BY_ID
                RoleDto.DOCTOR -> ""
                RoleDto.ADMIN -> Admin.GET_ADMIN_DETAILS_FOR
                else -> ""

            }

        fun getAddResidentialAddressEndPointFor(role: RoleDto): String {
        return when (role) {
            RoleDto.EMPLOYEE -> ADD_RESIDENTIAL_ADDRESS
            RoleDto.DOCTOR -> Doctor.ADD_RESIDENTIAL_ADDRESS
            RoleDto.ADMIN -> ""
            else -> ""

        }
    }

    fun getUploadEmploymentDocumentsEndPointFor(role: RoleDto): String {
        return when (role) {
            RoleDto.EMPLOYEE -> UPLOAD_EMPLOYMENT_DOCUMENTS
            RoleDto.DOCTOR -> Doctor.UPLOAD_EMPLOYMENT_FILE
            RoleDto.ADMIN -> ""
            else -> ""

        }
    }

    fun getUploadProfileImageEndPointFor(role: RoleDto): String {
        return when (role) {
            RoleDto.EMPLOYEE -> UPLOAD_PROFILE_IMAGE
            RoleDto.DOCTOR -> Doctor.UPLOAD_PROFILE_IMAGE
            RoleDto.ADMIN -> ""
            else -> ""

        }
    }

    fun getAppointmentByIdEndPointFor(role: RoleDto): String =
        when(role){
            RoleDto.EMPLOYEE -> ""
            RoleDto.DOCTOR -> Doctor.SHOW_SINGLE_APPOINTMENT
            RoleDto.ADMIN -> Admin.GET_ADMIN_DETAILS_FOR
            else -> ""

        }
    fun getDeactivateMyAccountEndPointFor(role: RoleDto): String {
        return when (role) {
            RoleDto.EMPLOYEE -> DEACTIVATE_MY_EMPLOYEE_ACCOUNT
            RoleDto.DOCTOR -> Doctor.DEACTIVATE_MY_ACCOUNT
            RoleDto.ADMIN -> ""
            else -> ""

        }
    }

    fun getReactivateMyAccountEndPointFor(role: RoleDto): String {
        return when (role) {
            RoleDto.EMPLOYEE -> REACTIVATE_MY_EMPLOYEE_ACCOUNT
            RoleDto.DOCTOR -> Doctor.REACTIVATE_MY_ACCOUNT
            RoleDto.ADMIN -> ""
            else -> ""

        }
    }

    fun getCheckPermissionEndPointFor(role: RoleDto): String {
        return when (role) {
            RoleDto.EMPLOYEE -> CHECK_EMPLOYEE_PERMISSION
            RoleDto.DOCTOR -> Doctor.CHECK_PERMISSION
            RoleDto.ADMIN -> ""
            else -> ""

        }
    }

    fun getEmploymentHistoryEndPointFor(role: RoleDto): String {
        return when (role) {
            RoleDto.EMPLOYEE -> EMPLOYEE_EMPLOYMENT_HISTORY
            RoleDto.DOCTOR -> Doctor.EMPLOYMENT_HISTORY
            RoleDto.ADMIN -> ""
            else -> ""

        }
    }


    fun getAllVaccinesEndPointForRole(role: RoleDto): String {
        return when (role) {
            RoleDto.EMPLOYEE -> throw Exception("This feature is not meant to be used for EMPLOYEE")
            RoleDto.DOCTOR -> Doctor.ALL_VACCINES
            RoleDto.ADMIN -> Admin.ALL_VACCINES
            else -> ""

        }
    }

    fun getVaccineDetailsEndPointForRole(role: RoleDto): String {
        return when (role) {
            RoleDto.EMPLOYEE -> throw Exception("This feature is not meant to be used for EMPLOYEE")
            RoleDto.DOCTOR -> Doctor.GET_VACCINE_BY_ID
            RoleDto.ADMIN -> Admin.GET_VACCINE_BY_ID
            else -> ""

        }
    }

    fun getPrescriptionsEndPointForRole(role: RoleDto): String {
        return when (role) {
            RoleDto.EMPLOYEE -> throw Exception("This feature is not meant to be used for EMPLOYEE")
            RoleDto.DOCTOR -> Doctor.MEDICAL_PRESCRIPTIONS
            RoleDto.ADMIN -> Admin.MEDICAL_PRESCRIPTIONS
            else -> ""

        }
    }
}