package com.example.androidnotesproject.repositories

import android.widget.EditText
import com.example.androidnotesproject.data.NoteItem
import com.example.androidnotesproject.db.NoteListDB
import com.example.androidnotesproject.extensions.DATE_FORMAT
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class NotesRepository {

    fun getNoteList() = NoteListDB.getNoteList()

    fun addNote(titleEdit: EditText, messageEdit: EditText) {
        val id = NoteListDB.getNoteList().last().id + 1
        val title = titleEdit.text.toString()
        val message = messageEdit.text.toString()

        NoteListDB.addNote(
            NoteItem.Note(
                id,
                LocalDate.now(),
                title,
                message
            )
        )
    }

    fun addScheduledNote(titleEdit: EditText, messageEdit: EditText, dateEdit: EditText) {
        val id = NoteListDB.getNoteList().last().id + 1
        val title = titleEdit.text.toString()
        val message = messageEdit.text.toString()
        val date = dateEdit.text.toString()

        NoteListDB.addNote(
            NoteItem.ScheduledNote(
                id,
                LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_FORMAT)),
                title,
                message
            )
        )
    }

}