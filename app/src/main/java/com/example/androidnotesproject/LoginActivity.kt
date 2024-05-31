package com.example.androidnotesproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginButtonMain: Button = findViewById(R.id.loginButtonMain)
        val loginTextViewToSignUp: TextView = findViewById(R.id.loginTextViewToSignUp)

        val loginEditTextEmail: EditText = findViewById(R.id.loginEditTextEmail)
        val loginEditTextPassword: EditText = findViewById(R.id.loginEditTextPassword)

        loginButtonMain.setOnClickListener {
            if (loginEditTextEmail.text.isBlank())
                loginEditTextEmail.error = getString(R.string.default_edittext_error)
            if (loginEditTextPassword.text.isBlank())
                loginEditTextPassword.error = getString(R.string.default_edittext_error)

            Auth.logIn(this, User(loginEditTextEmail.text.toString(),
                                         loginEditTextPassword.text.toString()
            ))
        }

        loginTextViewToSignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}