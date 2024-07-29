package com.example.androidnotesproject.utils

import com.example.androidnotesproject.db.entities.NoteEntity
import com.example.androidnotesproject.models.NoteItem

fun NoteEntity.toNote(): NoteItem {
    return if (scheduled) {
        NoteItem.ScheduledNote(id, date, title, message)
    } else
        NoteItem.Note(id, date, title, message)
}

fun List<NoteEntity>.toNoteList(): List<NoteItem> {
    return map { noteEntity ->
        noteEntity.toNote()
    }
}
