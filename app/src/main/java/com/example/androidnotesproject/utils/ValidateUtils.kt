package com.example.androidnotesproject.utils

import com.example.androidnotesproject.R
import java.time.LocalDate

private const val VALIDATE_NAME_LENGTH_MIN = 3
private const val VALIDATE_NAME_LENGTH_MAX = 255
private const val VALIDATE_PASS_LENGTH_MIN = 6
private const val VALIDATE_PASS_LENGTH_MAX = 50
private const val VALIDATE_PASS_REGULAR = "(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*"

fun signUpValidate(text: String, fieldType: String = "NULL"): ValidateResult {
    return when {
        text.isBlank() -> ValidateResult.Invalid(R.string.error_default_required)
        fieldType == "FIRST_NAME" && (text.length < VALIDATE_NAME_LENGTH_MIN || text.length > VALIDATE_NAME_LENGTH_MAX) -> ValidateResult.Invalid(
            R.string.error_name_length
        )
        fieldType == "PASSWORD" && (text.length < VALIDATE_PASS_LENGTH_MIN || text.length > VALIDATE_PASS_LENGTH_MAX) -> ValidateResult.Invalid(
            R.string.error_pass_length
        )
        fieldType == "PASSWORD" && !text.matches(Regex(VALIDATE_PASS_REGULAR)) -> ValidateResult.Invalid(
            R.string.error_pass_regex
        )
        fieldType == "EMAIL_ADDRESS" && !android.util.Patterns.EMAIL_ADDRESS.matcher(text).matches() -> ValidateResult.Invalid(
            R.string.error_email_regex
        )
        else -> ValidateResult.Valid
    }
}

fun logInValidate(text: String, fieldType: String = "NULL"): ValidateResult {
    return when {
        text.isBlank() -> ValidateResult.Invalid(R.string.error_default_required)
        fieldType == "EMAIL_ADDRESS" && !android.util.Patterns.EMAIL_ADDRESS.matcher(text).matches() -> ValidateResult.Invalid(
            R.string.error_email_regex
        )
        else -> ValidateResult.Valid
    }
}

fun addNoteValidate(text: String, fieldType: String = "NULL"): ValidateResult {
    return when {
        text.isBlank() -> ValidateResult.Invalid(R.string.error_default_required)
        else -> ValidateResult.Valid
    }
}

sealed class ValidateResult {
    data object Valid : ValidateResult()
    data class  Invalid(val errorCode: Int) : ValidateResult()
}