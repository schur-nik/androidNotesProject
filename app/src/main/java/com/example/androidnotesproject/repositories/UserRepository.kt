package com.example.androidnotesproject.repositories

import com.example.androidnotesproject.db.DBProvider
import com.example.androidnotesproject.db.entities.UserEntity

class UserRepository {

    fun getUser(email: String, password: String) = DBProvider.userDao?.getUser(email, password)

    fun addUser(userEntity: UserEntity) = DBProvider.userDao?.addUser(userEntity)

    fun getUsers() = DBProvider.userDao?.getUsers()

}