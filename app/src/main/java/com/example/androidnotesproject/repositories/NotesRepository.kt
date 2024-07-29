package com.example.androidnotesproject.repositories
import android.widget.EditText
import com.example.androidnotesproject.data.User
import com.example.androidnotesproject.db.DBProvider
import com.example.androidnotesproject.db.entities.NoteEntity
import com.example.androidnotesproject.extensions.DATE_FORMAT
import com.example.androidnotesproject.utils.toNoteList
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class NotesRepository {

    fun getNoteList() = DBProvider.notesDao?.getNotes(User.id)?.toNoteList() ?: arrayListOf()

    fun addNote(titleEdit: EditText, messageEdit: EditText) {
        val title = titleEdit.text.toString()
        val message = messageEdit.text.toString()

        DBProvider.notesDao?.addNote(NoteEntity(0, User.id, title, message, LocalDate.now(), false))
    }

    fun addScheduledNote(titleEdit: EditText, messageEdit: EditText, dateEdit: EditText) {
        val title = titleEdit.text.toString()
        val message = messageEdit.text.toString()
        val date = dateEdit.text.toString()

        DBProvider.notesDao?.addNote(NoteEntity(0, User.id, title, message, LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_FORMAT)), true))
    }

}