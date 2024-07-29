package com.example.androidnotesproject.models

import java.time.LocalDate

sealed class NoteItem {

    open val id: Int = 0
    open val title: String = "NULL"

    data class Note(
        override val id: Int,
        val date: LocalDate,
        override val title: String,
        val message: String
    ) : NoteItem()

    data class ScheduledNote(
        override val id: Int,
        val date: LocalDate,
        override val title: String,
        val message: String
    ) : NoteItem()

}