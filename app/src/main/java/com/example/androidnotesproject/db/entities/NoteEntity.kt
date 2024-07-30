package com.example.androidnotesproject.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(
    tableName = "Notes",
    foreignKeys = [ForeignKey(
        entity = UserEntity::class,
        parentColumns = ["r_id"],
        childColumns = ["parent_id"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("r_id")
    var id: Int,
    @ColumnInfo("parent_id")
    var parentId: Int,
    @ColumnInfo("title")
    val title: String,
    @ColumnInfo("message")
    val message: String,
    @ColumnInfo("create_date")
    val date: LocalDate,
    @ColumnInfo("scheduled")
    val scheduled: Boolean
)



