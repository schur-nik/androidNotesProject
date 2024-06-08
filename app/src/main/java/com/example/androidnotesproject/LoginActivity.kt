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
            Auth.logInValidate(loginEditTextEmail.text.toString(), "EMAIL_ADDRESS").apply {
                when (this) {
                    is Auth.ValidateResult.Invalid -> loginEditTextEmail.error = Auth.getErrorString(this@LoginActivity, this.errorCode)
                    else -> loginEditTextEmail.error = null
                }
            }

            Auth.logInValidate(loginEditTextPassword.text.toString()).apply {
                when (this) {
                    is Auth.ValidateResult.Invalid -> loginEditTextPassword.error = Auth.getErrorString(this@LoginActivity, this.errorCode)
                    else -> loginEditTextPassword.error = null
                }
            }

            if (loginEditTextEmail.error.isNullOrBlank() && loginEditTextPassword.error.isNullOrBlank()) {
                Auth.logIn(
                    context = this,
                    user = User(
                        email = loginEditTextEmail.text.toString(),
                        password = loginEditTextPassword.text.toString()
                    )
                )
            }
        }

        loginTextViewToSignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

    }

}