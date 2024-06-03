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
        if (user.firstname.length < VALIDATE_NAME_LENGTH_MIN || user.firstname.length > VALIDATE_NAME_LENGTH_MAX) {
            return 1
        } else if (user.password.length < VALIDATE_PASS_LENGTH_MIN || user.password.length > VALIDATE_PASS_LENGTH_MAX) {
            return 2
        } else if (!user.password.matches(Regex(VALIDATE_PASS_REGULAR))) {
            return 3
        } else if (user.email.isBlank() || !android.util.Patterns.EMAIL_ADDRESS.matcher(user.email).matches()) {
            return 4
        }
        return 0
    }

}