package com.example.androidnotesproject.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

const val DATE_FORMAT = "dd/MM/yyyy"

fun LocalDate.toSimpleText(): String {
    return this.format(DateTimeFormatter.ofPattern(DATE_FORMAT))
}