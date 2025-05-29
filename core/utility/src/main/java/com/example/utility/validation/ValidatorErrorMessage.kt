package com.example.utility.validation

sealed interface ValidatorErrorMessage {
    enum class NormalText : ValidatorErrorMessage {
        EMPTY_FIELD,
    }

    enum class Name : ValidatorErrorMessage {
        EMPTY_FIELD,
        TOO_SHORT,
        TOO_LONG,
        STARTS_WITH_SPECIAL_CHAR,
        ENDS_WITH_SPECIAL_CHAR,
        CONSECUTIVE_SPECIAL_CHARS,
    }

    enum class Email : ValidatorErrorMessage {
        INVALID_EMAIL,
        EMPTY_FIELD,
    }

    enum class Password : ValidatorErrorMessage {
        LESS_THAN_EIGHT_CHARS,
        EMPTY_FIELD,
    }

    enum class ConfirmPassword : ValidatorErrorMessage {
        DOES_NOT_MATCH_PASSWORD,
        EMPTY_FIELD,
    }

    enum class PhoneNumber : ValidatorErrorMessage {
        INVALID_PHONE_NUMBER,
        EMPTY_FIELD,
    }

    enum class OtpError : ValidatorErrorMessage {
        INVALID_LENGTH,
        EMPTY_FIELD,
        NON_DIGIT_CHARACTER,
    }

}