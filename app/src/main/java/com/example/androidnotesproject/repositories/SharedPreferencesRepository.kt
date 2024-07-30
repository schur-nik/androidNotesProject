package com.example.androidnotesproject.repositories

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

private const val APP_PREFERENCES = "APP_PREFERENCES"
private const val USER_PREFERENCES = "USER_PREFERENCES"
private const val IS_FIRST_LAUNCH = "IS_FIRST_LAUNCH"
private const val EMAIL = "EMAIL"
private const val PASS = "PASS"

object SharedPreferencesRepository {

    private var preferences: SharedPreferences? = null
    private var userPreferences: SharedPreferences? = null

    fun init(context: Context) {
        preferences = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        userPreferences = context.getSharedPreferences(USER_PREFERENCES, Context.MODE_PRIVATE)
    }

    fun isFirstLaunch(): Boolean {
        return preferences?.getBoolean(IS_FIRST_LAUNCH, true) ?: true
    }

    fun setFirstLaunch() {
        preferences?.edit {
            putBoolean(IS_FIRST_LAUNCH, false)
        }
    }

    fun getEmail(): String? {
        return userPreferences?.getString(EMAIL, null)
    }

    fun getPass(): String? {
        return userPreferences?.getString(PASS, null)
    }

    fun setEmail(email: String) {
        userPreferences?.edit {
            putString(EMAIL, email)
        }
    }

    fun setPass(pass: String) {
        userPreferences?.edit {
            putString(PASS, pass)
        }
    }

    fun clearUserData() {
        userPreferences?.edit() {
            clear()
        }
    }

}