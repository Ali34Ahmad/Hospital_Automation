package com.example.pharmacies.presentaion

interface PharmaciesUIAction {
    object Refresh: PharmaciesUIAction
    object ClearToast : PharmaciesUIAction
}
interface PharmaciesNavigationActions{
    fun navigateBack()
    fun navigateToPharmacyDetails(pharmacyId: Int)
}