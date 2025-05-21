package com.example.add_residential_address

class AddResidentialAddressUiActions(
    navigationActions:AddResidentialAddressNavigationUiActions,
    businessActions:AddResidentialAddressBusinessUiActions,
) :AddResidentialAddressBusinessUiActions by businessActions,
   AddResidentialAddressNavigationUiActions by navigationActions


interface AddResidentialAddressBusinessUiActions {
    fun onGovernorateTextChange(governorate: String)
    fun onCityTextChange(city: String)
    fun onRegionTextChange(region: String)
    fun onStreetTextChange(street: String)
    fun onNoteTextChange(note: String)
    fun onAddressInfoVisibilityPolicyCheckChange(value: Boolean)
    fun onShowErrorDialogStateChange(value: Boolean)
    fun onSubmitButtonClick()
}

interface AddResidentialAddressNavigationUiActions {
    fun navigateToUploadProfileImageScreen()
}