package com.example.androidnotesproject.data

data class User(
    var email: String,
    var firstname: String = "undefined",
    var lastname: String = "undefined",
    var password: String = "undefined"
) {

    var notesList: List<NoteItem> = arrayListOf()

}



