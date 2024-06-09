package com.example.androidnotesproject.utils

import com.example.androidnotesproject.data.Note
import com.example.androidnotesproject.data.NoteList.list
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun addNoteToList(title: String, message: String) {
    list.add(Note(list.last().id+1, LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString(), title, message))
}