package com.example.androidnotesproject.utils

import android.widget.EditText

sealed class ValidateResult {
    data object Valid : ValidateResult()
    data class Invalid(val errorCode: Int) : ValidateResult()
}

fun setFieldError(field: EditText, validateResult: ValidateResult) {
    when (validateResult) {
        is ValidateResult.Invalid -> field.error =
            field.context.getString(validateResult.errorCode)

        else -> field.error = null
    }
}