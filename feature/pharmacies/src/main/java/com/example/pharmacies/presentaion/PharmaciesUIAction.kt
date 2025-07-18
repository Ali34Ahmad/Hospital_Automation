package com.example.pharmacies.presentaion

interface PharmaciesUIAction {
    object Refresh: PharmaciesUIAction
}
interface PharmaciesNavigationActions{
    fun navigateBack()
    fun navigateToPharmacyDetails(pharmacyId: Int)
}