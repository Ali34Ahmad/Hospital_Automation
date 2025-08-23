package com.example.prescription_details.main


fun mockPrescriptionDetailsNavigationUiActions() = object : PrescriptionDetailsNavigationUiActions {
    override fun navigateUp() {

    }

    override fun navigateToPatientProfile(id: Int) {

    }

    override fun navigateToChildProfile(id: Int) {

    }

    override fun navigateToFulfillingPharmacy(pharmacyId: Int) {

    }

    override fun navigateToMedicineDetails(medicineId: Int) {

    }

}

fun mockPrescriptionDetailsBusinessUiActions() = object : PrescriptionDetailsBusinessUiActions {
    override fun onGetPrescriptionDetails() {

    }

    override fun onUpdateSelectedMedicineIndex(index: Int?) {

    }

    override fun showBottomSheet() {

    }

    override fun hideBottomSheet() {

    }

    override fun onRefresh() {

    }

    override fun clearToastMessage() {

    }

}
