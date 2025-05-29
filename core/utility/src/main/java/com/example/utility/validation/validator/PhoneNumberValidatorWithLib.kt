package com.example.utility.validation.validator

    import com.example.utility.validation.ValidatorErrorMessage
    import com.google.i18n.phonenumbers.NumberParseException
    import com.google.i18n.phonenumbers.PhoneNumberUtil

    data object PhoneNumberValidatorWithLib : Validator {

        private val phoneUtil: PhoneNumberUtil = PhoneNumberUtil.getInstance()

        override fun validate(value: String): ValidatorErrorMessage.PhoneNumber? {
            if (value.isBlank()) {
                return ValidatorErrorMessage.PhoneNumber.EMPTY_FIELD
            }

            // You need to provide a default region (country code) for parsing
            // numbers that are not in international format (e.g., missing '+').
            // This could be based on user's locale, app's target region, etc.
            // For this example, let's use "US" as a default.
            // Consider making this configurable if your app targets multiple regions.
            val defaultRegion = "SY"

            return try {
                val phoneNumber = phoneUtil.parse(value, defaultRegion)

                if (phoneUtil.isValidNumber(phoneNumber)) {
                    null // Phone number is valid
                } else {
                    // Number is parseable but not considered valid (e.g., too short for the region, not allocated)
                    ValidatorErrorMessage.PhoneNumber.INVALID_PHONE_NUMBER
                }
            } catch (e: NumberParseException) {
                // Number could not be parsed at all (e.g., "abc", too many digits, invalid country code)
                ValidatorErrorMessage.PhoneNumber.INVALID_PHONE_NUMBER
            }
        }
    }