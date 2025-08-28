package com.example.prescription_details.main

class PrescriptionDetailsUiActions(
    navigationActions:PrescriptionDetailsNavigationUiActions,
    businessActions:PrescriptionDetailsBusinessUiActions,
) :PrescriptionDetailsBusinessUiActions by businessActions,
   PrescriptionDetailsNavigationUiActions by navigationActions


interface PrescriptionDetailsBusinessUiActions {
    fun onGetPrescriptionDetails()
    fun onUpdateSelectedMedicineIndex(index:Int?)
    fun showBottomSheet()
    fun hideBottomSheet()
    fun onRefresh()
    fun clearToastMessage()
}

interface PrescriptionDetailsNavigationUiActions {
    fun navigateUp()
    fun navigateToPatientProfile(id: Int)
    fun navigateToChildProfile(id: Int)
    fun navigateToFulfillingPharmacy(pharmacyId: Int)
    fun navigateToMedicineDetails(medicineId: Int)
}
