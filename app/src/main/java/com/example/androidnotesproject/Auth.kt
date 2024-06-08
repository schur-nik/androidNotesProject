package com.example.androidnotesproject

import android.content.Context
import android.widget.Toast
import java.lang.Exception

object Auth {

    private const val VALIDATE_NAME_LENGTH_MIN = 3;
    private const val VALIDATE_NAME_LENGTH_MAX = 255;
    private const val VALIDATE_PASS_LENGTH_MIN = 6;
    private const val VALIDATE_PASS_LENGTH_MAX = 50;
    private const val VALIDATE_PASS_REGULAR = "(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*";

    fun signUp(context: Context, user: User) {
//        signUp(user)
        Toast.makeText(context, context.getString(R.string.signup_successful_text), Toast.LENGTH_SHORT).show()
    }

    fun logIn(context: Context, user: User) {
//        logIn(user)
        Toast.makeText(context, context.getString(R.string.login_successful_text), Toast.LENGTH_SHORT).show()
    }

    fun signUpValidate(text: String, fieldType: String = "NULL"): ValidateResult {
        return when {
            text.isBlank() -> ValidateResult.Invalid(R.string.error_default_required)
            fieldType == "FIRST_NAME" && (text.length < VALIDATE_NAME_LENGTH_MIN || text.length > VALIDATE_NAME_LENGTH_MAX) -> ValidateResult.Invalid(R.string.error_name_length)
            fieldType == "PASSWORD" && (text.length < VALIDATE_PASS_LENGTH_MIN || text.length > VALIDATE_PASS_LENGTH_MAX) -> ValidateResult.Invalid(R.string.error_pass_length)
            fieldType == "PASSWORD" && !text.matches(Regex(VALIDATE_PASS_REGULAR)) -> ValidateResult.Invalid(R.string.error_pass_regex)
            fieldType == "EMAIL_ADDRESS" && !android.util.Patterns.EMAIL_ADDRESS.matcher(text).matches() -> ValidateResult.Invalid(R.string.error_email_regex)
            else -> ValidateResult.Valid
        }
    }

    fun logInValidate(text: String, fieldType: String = "NULL"): ValidateResult {
        return when {
            text.isBlank() -> ValidateResult.Invalid(R.string.error_default_required)
            fieldType == "EMAIL_ADDRESS" && !android.util.Patterns.EMAIL_ADDRESS.matcher(text).matches() -> ValidateResult.Invalid(R.string.error_email_regex)
            else -> ValidateResult.Valid
        }
    }

    sealed class ValidateResult {
        data object Valid : ValidateResult()
        data class  Invalid(val errorCode: Int) : ValidateResult()
    }

    fun getErrorString(context: Context, errorCode: Int): String {
        return try {
            context.getString(errorCode)
        } catch (e: Exception) {
            context.getString(R.string.error_default_required)
        }
    }

}