package com.example.androidnotesproject.utils

import com.example.androidnotesproject.data.NoteItem
import com.example.androidnotesproject.data.NoteList.noteList
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun addNoteToList(title: String, message: String, date: String) {
    noteList.add(NoteItem.Note(noteList.last().id+1, LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_FORMAT)), title, message))
}

fun addScheduledNoteToList(title: String, message: String, date: String) {
    noteList.add(NoteItem.ScheduledNote(noteList.last().id+1, LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_FORMAT)), title, message))
}