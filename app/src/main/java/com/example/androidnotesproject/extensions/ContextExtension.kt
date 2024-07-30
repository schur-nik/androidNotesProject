package com.example.androidnotesproject.extensions

import android.content.Context
import android.widget.Toast
import com.example.androidnotesproject.R
import java.lang.Exception

fun Context.getString(code: Int): String {
    return try {
        getString(code)
    } catch (e: Exception) {
        getString(R.string.error_default)
    }
}

fun Context.showToastShort(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}