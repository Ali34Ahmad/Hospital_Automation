package com.example.medical_prescription_details.main

import com.example.prescription_details.main.PrescriptionDetailsBusinessUiActions
import com.example.prescription_details.main.PrescriptionDetailsNavigationUiActions


fun mockPrescriptionDetailsNavigationUiActions() = object : PrescriptionDetailsNavigationUiActions {
    override fun navigateUp() {

    }

    override fun navigateToPatientProfile(id: Int) {

    }

    override fun navigateToChildProfile(id: Int) {

    }

    override fun navigateToFulfillingPharmacy(pharmacyId: Int) {

    }

}

fun mockPrescriptionDetailsBusinessUiActions() = object : PrescriptionDetailsBusinessUiActions {
    override fun onGetPrescriptionDetails() {

    }

    override fun onUpdateSelectedMedicineIndex(index: Int?) {

    }

    override fun onRefresh() {

    }

    override fun clearToastMessage() {

    }

}
