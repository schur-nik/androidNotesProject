package com.example.androidnotesproject.data

import java.time.LocalDate

object NoteList {

    private val subscribers = arrayListOf<Subscriber>()

    private val noteList: ArrayList<NoteItem> = arrayListOf(
        NoteItem.Note(
            1,
            LocalDate.now(),
            "Title1",
            "Any text blablablablablablablablablabla"
        ),
        NoteItem.Note(
            2,
            LocalDate.now(),
            "Title2",
            "Any text blablablablablablablablablabla"
        ),
        NoteItem.ScheduledNote(
            3,
            LocalDate.now(),
            "TitleTEST",
            "Any text blablablablablablablablablabla"
        )
    )

    fun getNoteList() = noteList

    fun addNote(item: NoteItem) {
        noteList.add(item)
        notifySubscribers()
    }

    fun getNoteById(id: Int): NoteItem? {
        return noteList.find { it.id == id }
    }

    fun subscribe(subscriber: Subscriber) {
        subscribers.add(subscriber)
    }

    fun unsubscribe(subscriber: Subscriber) {
        subscribers.remove(subscriber)
    }

    private fun notifySubscribers() {
        subscribers.forEach {
            it.update()
        }
    }

}