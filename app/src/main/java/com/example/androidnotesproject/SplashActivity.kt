package com.example.androidnotesproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val splashButtonMain: Button = findViewById(R.id.splashButtonMain)
        val splashTextViewToLogIn: TextView = findViewById(R.id.splashTextViewToLogIn)

        splashButtonMain.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        splashTextViewToLogIn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}