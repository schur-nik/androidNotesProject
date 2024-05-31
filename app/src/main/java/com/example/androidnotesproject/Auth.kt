package com.example.androidnotesproject

import android.content.Context
import android.widget.Toast
import java.lang.Exception

object Auth {

    fun signUp(context: Context, user: User) {
        val errorCode = signUpValidate(user)
        if (errorCode == 0) {
//            signUp()
            Toast.makeText(context, context.getString(R.string.signup_successful_text), Toast.LENGTH_SHORT).show()
        } else {
            try {
                val errorText = context.resources.getString(context.resources.getIdentifier("error_text_$errorCode", "string", context.packageName))
                Toast.makeText(context, errorText, Toast.LENGTH_LONG).show()
            } catch (e:Exception) {
                Toast.makeText(context,
                               context.resources.getString(context.resources.getIdentifier("error_text_default", "string", context.packageName)),
                               Toast.LENGTH_LONG).show()
            }

        }
    }

    fun logIn(context: Context, user: User) {
        Toast.makeText(context, context.getString(R.string.login_successful_text), Toast.LENGTH_SHORT).show()
    }

    private fun signUpValidate(user: User):Int {
        if (user.firstname.length < 3 || user.firstname.length > 255) {
            return 1
        } else if (user.password.length < 6 || user.password.length > 50) {
            return 2
        } else if (!user.password.matches(Regex("(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#\\\$%^&*(),.?\\\":{}|<>]).*"))) {
            return 3
        } else if (user.email.isBlank() || !android.util.Patterns.EMAIL_ADDRESS.matcher(user.email).matches()) {
            return 4
        }
        return 0
    }

}