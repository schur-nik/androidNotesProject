package com.example.androidnotesproject.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "Users",
    indices = [
        Index("email", unique = true)
    ]
)
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("r_id")
    var id: Int,
    @ColumnInfo("email")
    var email: String,
    @ColumnInfo("firstname")
    var firstname: String = "undefined",
    @ColumnInfo("lastname")
    var lastname: String = "undefined",
    @ColumnInfo("password")
    var password: String = "undefined"
)



