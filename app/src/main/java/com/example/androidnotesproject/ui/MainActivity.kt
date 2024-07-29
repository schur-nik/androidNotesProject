package com.example.androidnotesproject.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.androidnotesproject.R
import com.example.androidnotesproject.data.User
import com.example.androidnotesproject.db.entities.UserEntity
import com.example.androidnotesproject.navigation.Navigation
import com.example.androidnotesproject.repositories.SharedPreferencesRepository
import com.example.androidnotesproject.repositories.UserRepository
import com.example.androidnotesproject.ui.login.LoginFragment
import com.example.androidnotesproject.ui.notes.NotesFragment
import com.example.androidnotesproject.ui.splash.SplashFragment

class MainActivity : AppCompatActivity(), Navigation {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("ALL DB ACC", UserRepository().getUsers().toString())
        setContentView(R.layout.activity_main)
        if (!SharedPreferencesRepository.getEmail().isNullOrBlank()) {
            val user: UserEntity? = UserRepository().getUser(SharedPreferencesRepository.getEmail().toString(), SharedPreferencesRepository.getPass().toString())
            if (user != null) {
                User.id = user.id
                replaceFragment(NotesFragment(), false)
            } else {
                replaceFragment(LoginFragment(), false)
            }
        } else if (SharedPreferencesRepository.isFirstLaunch()) {
            replaceFragment(SplashFragment(), false)
        } else
            replaceFragment(LoginFragment(), false)
    }

    override fun replaceFragment(fragment: Fragment, backStack: Boolean) {
        when (backStack) {
            true ->
                supportFragmentManager.beginTransaction()
                    .replace(R.id.mainFrame, fragment)
                    .addToBackStack(null)
                    .commit()

            false ->
                supportFragmentManager.beginTransaction()
                    .replace(R.id.mainFrame, fragment)
                    .commit()
        }
    }

    override fun addFragment(fragment: Fragment, backStack: Boolean) {
        when (backStack) {
            true ->
                supportFragmentManager.beginTransaction()
                    .add(R.id.mainFrame, fragment)
                    .addToBackStack(null)
                    .commit()

            false ->
                supportFragmentManager.beginTransaction()
                    .add(R.id.mainFrame, fragment)
                    .commit()
        }
    }

    override fun cancelFragment() {
        supportFragmentManager.popBackStack()
    }

    override fun goBack() {
        onBackPressed()
    }

    override fun popBackStackAll() {
        supportFragmentManager.popBackStack(
            null,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
    }

}