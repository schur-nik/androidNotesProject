package com.example.androidnotesproject.utils

import com.example.androidnotesproject.data.Note
import com.example.androidnotesproject.data.NoteList.list
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun addNoteToList(title: String, message: String, date: String) {
    list.add(Note(list.last().id+1, LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_FORMAT)), title, message))
}