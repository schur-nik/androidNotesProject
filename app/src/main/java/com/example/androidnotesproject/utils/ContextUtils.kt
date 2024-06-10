package com.example.androidnotesproject.utils

import android.content.Context
import android.widget.Toast
import com.example.androidnotesproject.R
import java.lang.Exception

fun Context.getErrorString(errorCode: Int): String {
    return try {
        getString(errorCode)
    } catch (e: Exception) {
        getString(R.string.error_default)
    }
}

fun Context.showToastShort(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}