package com.example.androidnotesproject.utils

import com.example.androidnotesproject.data.NoteItem
import com.example.androidnotesproject.data.NoteList.addNote
import com.example.androidnotesproject.data.NoteList.getNoteList
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun addNoteToList(title: String, message: String, date: String) {
    addNote(NoteItem.Note(getNoteList().last().id+1, LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_FORMAT)), title, message))
}

fun addScheduledNoteToList(title: String, message: String, date: String) {
    addNote(NoteItem.ScheduledNote(getNoteList().last().id+1, LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_FORMAT)), title, message))
}