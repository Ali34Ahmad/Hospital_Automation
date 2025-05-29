package com.example.domain.use_cases.add_residential_address

import com.example.domain.repositories.AddResidentialAddressRepository
import com.example.model.residential_address.AddAddressRequest
import com.example.model.residential_address.AddAddressResponse
import com.example.utility.network.rootError
import com.example.utility.network.Result

class AddResidentialAddressUseCase(
    private val addResidentialAddressRepository: AddResidentialAddressRepository
) {
    suspend operator fun invoke(
        addAddressRequest: AddAddressRequest
    ): Result<AddAddressResponse, rootError> {
//        // --- Optional: Add Business Logic/Validation Here ---
//        // For example, you might want to validate the address fields before sending them.
//        // This keeps validation logic separate from both the UI and the raw data fetching.
//
//        // Example: Validate that essential fields are not blank
//        if (addAddressRequest.street.isBlank()) {
//            return Result.Error(rootError(message = "Street address cannot be empty."))
//        }
//        if (addAddressRequest.city.isBlank()) {
//            return Result.Error(rootError(message = "City cannot be empty."))
//        }
//        if (addAddressRequest.postalCode.isBlank()) { // Assuming postalCode is a field
//            return Result.Error(rootError(message = "Postal code cannot be empty."))
//        }
//        // Add more specific validation as needed (e.g., postal code format, etc.)
//
//        // If all validations pass, proceed to call the repository
        return addResidentialAddressRepository.addResidentialAddress(addAddressRequest)
    }
}