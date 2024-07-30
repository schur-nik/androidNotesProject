package com.example.androidnotesproject.db

import android.content.Context
import androidx.room.Room
import com.example.androidnotesproject.db.dao.NotesDao
import com.example.androidnotesproject.db.dao.UserDao

object DBProvider {

    var notesDao: NotesDao? = null
    var userDao: UserDao? = null

    fun init(context: Context) {
        val db = Room.databaseBuilder(context, AppDB::class.java, "app_db")
            .allowMainThreadQueries()
            .build()
        notesDao = db.getNotesDao()
        userDao= db.getUserDao()
    }

}