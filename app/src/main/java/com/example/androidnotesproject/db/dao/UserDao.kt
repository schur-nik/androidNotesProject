package com.example.androidnotesproject.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.androidnotesproject.db.entities.UserEntity

@Dao
interface UserDao {

    @Insert
    fun addUser(user: UserEntity)

    @Query("SELECT * FROM Users WHERE email == :email and password == :password LIMIT 1")
    fun getUser(email: String, password: String) : UserEntity

    @Query("SELECT * FROM Users")
    fun getUsers() : List<UserEntity>

}