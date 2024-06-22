package com.example.androidnotesproject.data

import java.util.Date

data class Note(
    val id: Int,
    val date: Date?,
    val title: String,
    val message: String
)