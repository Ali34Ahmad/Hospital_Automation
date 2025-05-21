package com.example.add_residential_address

import com.example.constants.enums.Gender


fun mockNavigationAction() = object : AddResidentialAddressNavigationUiActions {
    override fun navigateToUploadProfileImageScreen() {
        TODO("Not yet implemented")
    }

}

fun mockBusinessUiActions() = object : AddResidentialAddressBusinessUiActions {
    override fun onGovernorateTextChange(governorate: String) {
        TODO("Not yet implemented")
    }

    override fun onCityTextChange(city: String) {
        TODO("Not yet implemented")
    }

    override fun onRegionTextChange(region: String) {
        TODO("Not yet implemented")
    }

    override fun onStreetTextChange(street: String) {
        TODO("Not yet implemented")
    }

    override fun onNoteTextChange(note: String) {
        TODO("Not yet implemented")
    }

    override fun onAddressInfoVisibilityPolicyCheckChange(value: Boolean) {
        TODO("Not yet implemented")
    }

    override fun onShowErrorDialogStateChange(value: Boolean) {

    }

    override fun onSubmitButtonClick() {

    }
}