package com.example.androidnotesproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat


class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val signupButtonMain: Button = findViewById(R.id.signupButtonMain)
        val signupTextViewToLogin: TextView = findViewById(R.id.signupTextViewToLogin)

        val signupEditTextFirstName: EditText = findViewById(R.id.signupEditTextFirstName)
        val signupEditTextLastName: EditText = findViewById(R.id.signupEditTextLastName)
        val signupEditTextEmail: EditText = findViewById(R.id.signupEditTextEmail)
        val signupEditTextPassword: EditText = findViewById(R.id.signupEditTextPassword)

        signupButtonMain.setOnClickListener {
            Auth.signUp(
                context = this,
                user = User(
                    email = signupEditTextEmail.text.toString(),
                    firstname = signupEditTextFirstName.text.toString(),
                    lastname = signupEditTextLastName.text.toString(),
                    password = signupEditTextPassword.text.toString()
                )
            )
        }

        signupTextViewToLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

    }

}