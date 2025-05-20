package com.example.utility.validation

import androidx.annotation.StringRes
import com.example.utility.R

enum class ValidatorErrorMessage(@StringRes val string: Int){
    LESS_THAN_EIGHT_CHARS(R.string.less_than_eight_chars),
    EMPTY_FIELD(R.string.empty_field)
}