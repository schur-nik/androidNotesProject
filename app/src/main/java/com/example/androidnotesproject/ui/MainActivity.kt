package com.example.androidnotesproject.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.androidnotesproject.R
import com.example.androidnotesproject.navigation.Navigation
import com.example.androidnotesproject.ui.splash.SplashFragment

class MainActivity : AppCompatActivity(), Navigation {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.mainFrame, SplashFragment())
            .commit()
    }

    override fun startFragment(fragment: Fragment, backStackName: String?) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.mainFrame, fragment)
            .addToBackStack(backStackName)
            .commit()
    }

    override fun addFragment(fragment: Fragment, backStackName: String?) {
        supportFragmentManager.beginTransaction()
            .add(R.id.mainFrame, fragment)
            .addToBackStack(backStackName)
            .commit()
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