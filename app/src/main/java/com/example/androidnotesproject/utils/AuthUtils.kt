package com.example.androidnotesproject.utils

import android.content.Context
import android.widget.Toast
import com.example.androidnotesproject.R
import com.example.androidnotesproject.data.User

fun signUp(context: Context, user: User) {
//        signUp(user)
    context.showToastShort(context.getErrorString(R.string.signup_successful_text))
    Toast.makeText(context, context.getString(R.string.signup_successful_text), Toast.LENGTH_SHORT)
        .show()
}

fun logIn(context: Context, user: User) {
//        logIn(user)
    context.showToastShort(context.getErrorString(R.string.login_successful_text))
}

