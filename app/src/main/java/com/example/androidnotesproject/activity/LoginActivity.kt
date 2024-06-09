package com.example.androidnotesproject.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.androidnotesproject.R
import com.example.androidnotesproject.data.User
import com.example.androidnotesproject.utils.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginButtonMain: Button = findViewById(R.id.loginButtonMain)
        val loginTextViewToSignUp: TextView = findViewById(R.id.loginTextViewToSignUp)

        val loginEditTextEmail: EditText = findViewById(R.id.loginEditTextEmail)
        val loginEditTextPassword: EditText = findViewById(R.id.loginEditTextPassword)


        loginButtonMain.setOnClickListener {
            logInValidate(loginEditTextEmail.text.toString(), "EMAIL_ADDRESS").apply {
                when (this) {
                    is ValidateResult.Invalid -> loginEditTextEmail.error = getErrorString(errorCode)
                    else -> loginEditTextEmail.error = null
                }
            }

            logInValidate(loginEditTextPassword.text.toString()).apply {
                when (this) {
                    is ValidateResult.Invalid -> loginEditTextPassword.error = getErrorString(errorCode)
                    else -> loginEditTextPassword.error = null
                }
            }

            if (loginEditTextEmail.error.isNullOrBlank() && loginEditTextPassword.error.isNullOrBlank()) {
                logIn(
                    context = this,
                    user = User(
                        email = loginEditTextEmail.text.toString(),
                        password = loginEditTextPassword.text.toString()
                    )
                )

                startActivity(Intent(this, NotesActivity::class.java))

            }
        }

        loginTextViewToSignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }

}