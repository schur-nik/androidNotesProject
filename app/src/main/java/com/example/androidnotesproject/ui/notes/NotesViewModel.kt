package com.example.androidnotesproject.ui.notes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidnotesproject.models.NoteItem
import com.example.androidnotesproject.db.NoteListDB
import com.example.androidnotesproject.db.Subscriber
import com.example.androidnotesproject.repositories.NotesRepository

class NotesViewModel : ViewModel(), Subscriber {

    val list = MutableLiveData<List<NoteItem>>()

    private val notesRepository = NotesRepository()

    init {
        NoteListDB.subscribe(this)
    }

    override fun onCleared() {
        super.onCleared()
        NoteListDB.unsubscribe(this)
    }

    override fun update() {
        getNoteList()
    }

    fun getNoteList() {
        list.value = notesRepository.getNoteList()
    }

}