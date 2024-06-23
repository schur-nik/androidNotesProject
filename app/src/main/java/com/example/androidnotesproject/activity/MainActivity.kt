package com.example.androidnotesproject.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidnotesproject.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.mainFrame, SplashFragment())
            .commit()
    }

}