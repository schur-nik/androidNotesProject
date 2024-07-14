package com.example.androidnotesproject.db

import com.example.androidnotesproject.data.NoteItem

data class User(
    var email: String,
    var firstname: String = "undefined",
    var lastname: String = "undefined",
    var password: String = "undefined"
) {

    var notesList: List<NoteItem> = arrayListOf()

}



