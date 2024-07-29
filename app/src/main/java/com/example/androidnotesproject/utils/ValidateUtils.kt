package com.example.androidnotesproject.utils

import android.content.Context
import android.widget.EditText
import com.example.androidnotesproject.R
import com.example.androidnotesproject.extensions.DATE_FORMAT
import com.example.androidnotesproject.extensions.getErrorString
import java.time.LocalDate
import java.time.format.DateTimeFormatter

sealed class ValidateResult {
    data object Valid : ValidateResult()
    data class  Invalid(val errorCode: Int) : ValidateResult()
}