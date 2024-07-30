package com.example.androidnotesproject.ui.login

import androidx.lifecycle.ViewModel
import com.example.androidnotesproject.R
import com.example.androidnotesproject.data.User
import com.example.androidnotesproject.db.entities.UserEntity
import com.example.androidnotesproject.repositories.SharedPreferencesRepository
import com.example.androidnotesproject.repositories.UserRepository
import com.example.androidnotesproject.utils.ValidateResult

class LoginViewModel : ViewModel() {

    fun logIn(email: String, password: String) : Int {
        val user: UserEntity? = UserRepository().getUser(email, password)
        return if (user != null) {
            SharedPreferencesRepository.setEmail(user.email)
            SharedPreferencesRepository.setPass(user.password)
            User.id = user.id
            R.string.login_successful_text
        } else {
            R.string.login_unsuccessful_text
        }

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