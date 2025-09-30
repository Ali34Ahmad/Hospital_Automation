package com.example.pharmacy_details.presentation


fun mockPharmacyDetailsNavigationUiActions()=object : PharmacyDetailsNavigationUiActions {
    override fun navigateUp() {

    }

    override fun navigateToEmailApp(email: String, subject: String?) {

    }

    override fun navigateToCallApp(phoneNumber: String) {

    }

    override fun navigateToFulfilledPrescriptionsScreen(pharmacyId: Int) {

    }

    override fun navigateToMedicinesScreen(pharmacyId: Int) {

    }

    override fun navigateToEmploymentHistoryScreen(pharmacyId: Int) {

    }


}

fun mockPharmacyDetailsBusinessUiActions()=object : PharmacyDetailsBusinessUiActions {
    override fun onDeactivateAccount() {

    }

    override fun onReactivateAccount() {

    }

    override fun onHideErrorDialog() {

    }

    override fun onRefresh() {

    }

    override fun clearToastMessage() {

    }


}
