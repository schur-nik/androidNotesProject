package com.example.androidnotesproject.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.androidnotesproject.db.dao.NotesDao
import com.example.androidnotesproject.db.dao.UserDao
import com.example.androidnotesproject.db.entities.NoteEntity
import com.example.androidnotesproject.db.entities.UserEntity
import com.example.androidnotesproject.utils.DateConverter

@Database(entities = [UserEntity::class, NoteEntity::class], version = 1)
@TypeConverters(value = [DateConverter::class])
abstract class AppDB : RoomDatabase() {

    abstract fun getUserDao() : UserDao

    abstract fun getNotesDao() : NotesDao

}