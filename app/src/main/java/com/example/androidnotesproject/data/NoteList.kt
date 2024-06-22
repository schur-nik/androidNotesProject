package com.example.androidnotesproject.data

import java.time.LocalDate

object NoteList {

    val noteList : ArrayList<NoteItem> = arrayListOf(
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

}