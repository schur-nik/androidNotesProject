package com.example.androidnotesproject.data

import java.time.LocalDate

data class Note(
    val id: Int,
    val date: LocalDate,
    val title: String,
    val message: String
)