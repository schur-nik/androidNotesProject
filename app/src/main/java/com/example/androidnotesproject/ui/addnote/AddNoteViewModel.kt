package com.example.androidnotesproject.ui.addnote

import android.widget.EditText
import androidx.lifecycle.ViewModel
import com.example.androidnotesproject.R
import com.example.androidnotesproject.extensions.DATE_FORMAT
import com.example.androidnotesproject.repositories.NotesRepository
import com.example.androidnotesproject.utils.ValidateResult
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class AddNoteViewModel : ViewModel() {

    private val notesRepository = NotesRepository()

    fun addNote(titleEdit: EditText, messageEdit: EditText, dateEdit: EditText, scheduled: Boolean) {
        if (!scheduled) {
            notesRepository.addNote(
                titleEdit,
                messageEdit)
        } else {
            notesRepository.addScheduledNote(
                titleEdit,
                messageEdit,
                dateEdit
            )
        }
    }

    fun addNoteValidate(text: String, fieldType: String = "NULL"): ValidateResult {
        return when {
            text.isBlank() -> ValidateResult.Invalid(R.string.error_default_required)
            fieldType == "DATE" -> try {
                LocalDate.parse(text, DateTimeFormatter.ofPattern(DATE_FORMAT))
                ValidateResult.Valid
            } catch (e: Exception) {
                ValidateResult.Invalid(R.string.error_date_regex)
            }

            else -> ValidateResult.Valid
        }
    }

}