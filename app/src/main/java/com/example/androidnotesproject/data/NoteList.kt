package com.example.androidnotesproject.data

import java.time.LocalDate

object NoteList {

    private val subscribers = arrayListOf<Subscriber>()

    private val noteList: ArrayList<NoteItem> = arrayListOf(
        NoteItem.Note(
            1,
            LocalDate.now(),
            "Shopping list",
            "Zucchini x 1 kg\n" +
                    "Cucumber x 2\n" +
                    "Milk x 1l\n" +
                    "Bread x 1"
        ),
        NoteItem.ScheduledNote(
            2,
            LocalDate.now(),
            "HOSPITAL 10:30",
            "Going to the hospital: Franche-Conte, 44"
        ),
        NoteItem.Note(
            3,
            LocalDate.now(),
            "Homework",
            "Working on homework"
        ),
        NoteItem.ScheduledNote(
            4,
            LocalDate.now().minusDays(1),
            "Sister's birthday",
            "Congratulate sister with birthday"
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