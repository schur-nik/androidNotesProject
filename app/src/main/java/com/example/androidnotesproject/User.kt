package com.example.androidnotesproject

data class User (
    var email: String,
    var firstname: String = "undefined",
    var lastname: String = "undefined",
    var password: String = "undefined"
){

    var notesList:HashMap<Int, String> = HashMap<Int, String>()

}



