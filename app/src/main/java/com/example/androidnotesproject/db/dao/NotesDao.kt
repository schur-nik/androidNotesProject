package com.example.androidnotesproject.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.androidnotesproject.db.entities.NoteEntity

@Dao
interface NotesDao {

    @Insert
    fun addNote(note: NoteEntity)

    @Query("SELECT * FROM Notes WHERE parent_id == :id")
    fun getNotes(id: Int) : List<NoteEntity>

}