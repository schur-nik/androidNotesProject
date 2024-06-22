package com.example.androidnotesproject.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidnotesproject.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private var binding: ActivitySplashBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        binding?.splashButtonMain?.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        binding?.splashTextViewToLogIn?.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

}