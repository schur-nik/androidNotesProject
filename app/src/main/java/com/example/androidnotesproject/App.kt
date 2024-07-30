package com.example.androidnotesproject

import android.app.Application
import com.example.androidnotesproject.db.DBProvider
import com.example.androidnotesproject.repositories.SharedPreferencesRepository

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        SharedPreferencesRepository.init(this)
        DBProvider.init(this)
    }

}