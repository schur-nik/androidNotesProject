package com.example.androidnotesproject.ui.login

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.androidnotesproject.R
import com.example.androidnotesproject.db.User
import com.example.androidnotesproject.extensions.getErrorString
import com.example.androidnotesproject.extensions.showToastShort
import com.example.androidnotesproject.utils.ValidateResult

class LoginViewModel : ViewModel() {

    fun logIn(context: Context, user: User) {
//        logIn(user)
        context.showToastShort(context.getErrorString(R.string.login_successful_text))
    }

    fun logInValidate(text: String, fieldType: String = "NULL"): ValidateResult {
        return when {
            text.isBlank() -> ValidateResult.Invalid(R.string.error_default_required)
            fieldType == "EMAIL_ADDRESS" && !android.util.Patterns.EMAIL_ADDRESS.matcher(text)
                .matches() -> ValidateResult.Invalid(
                R.string.error_email_regex
            )

            else -> ValidateResult.Valid
        }
    }

}