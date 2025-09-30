package com.example.pharmacy_details.presentation

class PharmacyDetailsUiActions(
    navigationActions:PharmacyDetailsNavigationUiActions,
    businessActions:PharmacyDetailsBusinessUiActions,
) :PharmacyDetailsBusinessUiActions by businessActions,
   PharmacyDetailsNavigationUiActions by navigationActions


interface PharmacyDetailsBusinessUiActions {
    fun onDeactivateAccount()
    fun onReactivateAccount()
    fun onHideErrorDialog()
    fun onRefresh()
    fun clearToastMessage()
}

interface PharmacyDetailsNavigationUiActions {
    fun navigateUp()
    fun navigateToEmailApp(email:String,subject: String?)
    fun navigateToCallApp(phoneNumber: String)
    fun navigateToFulfilledPrescriptionsScreen(pharmacyId: Int)
    fun navigateToMedicinesScreen(pharmacyId: Int)
    fun navigateToEmploymentHistoryScreen(pharmacyId: Int)
}