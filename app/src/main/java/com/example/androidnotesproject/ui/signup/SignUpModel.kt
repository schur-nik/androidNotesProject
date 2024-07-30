package com.example.androidnotesproject.ui.signup

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.androidnotesproject.R
import com.example.androidnotesproject.db.entities.UserEntity
import com.example.androidnotesproject.extensions.showToastShort
import com.example.androidnotesproject.repositories.UserRepository
import com.example.androidnotesproject.utils.ValidateResult

private const val VALIDATE_NAME_LENGTH_MIN = 3
private const val VALIDATE_NAME_LENGTH_MAX = 255
private const val VALIDATE_PASS_LENGTH_MIN = 6
private const val VALIDATE_PASS_LENGTH_MAX = 50
private const val VALIDATE_PASS_REGULAR = "(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*"
class SignUpModel : ViewModel() {

    fun signUp(context: Context, email: String, firstname: String, lastname: String, password: String) {
        UserRepository().addUser(UserEntity(0, email, firstname, lastname, password))
        context.showToastShort(context.getString(R.string.signup_successful_text))
    }

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
}