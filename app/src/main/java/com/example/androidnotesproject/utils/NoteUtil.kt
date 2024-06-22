package com.example.androidnotesproject.utils

import com.example.androidnotesproject.data.Note
import com.example.androidnotesproject.data.NoteList.list
import java.text.SimpleDateFormat

fun addNoteToList(title: String, message: String, date: String) {
    list.add(Note(list.last().id+1, SimpleDateFormat(DATE_FORMAT).parse(date), title, message))
}