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
            signUpValidate(signupEditTextFirstName.text.toString(), "FIRST_NAME").apply {
                when (this) {
                    is ValidateResult.Invalid -> signupEditTextFirstName.error = getErrorString(errorCode)
                    else -> signupEditTextFirstName.error = null
                }
            }

            signUpValidate(signupEditTextEmail.text.toString(), "EMAIL_ADDRESS").apply {
                when (this) {
                    is ValidateResult.Invalid -> signupEditTextEmail.error = getErrorString(errorCode)
                    else -> signupEditTextEmail.error = null
                }
            }

            signUpValidate(signupEditTextPassword.text.toString(), "PASSWORD").apply {
                when (this) {
                    is ValidateResult.Invalid -> signupEditTextPassword.error = getErrorString(errorCode)
                    else -> signupEditTextPassword.error = null
                }
            }

            if (signupEditTextFirstName.error.isNullOrBlank() && signupEditTextEmail.error.isNullOrBlank() && signupEditTextPassword.error.isNullOrBlank()) {
                signUp(
                    context = this,
                    user = User(
                        email = signupEditTextEmail.text.toString(),
                        firstname = signupEditTextFirstName.text.toString(),
                        lastname = signupEditTextLastName.text.toString(),
                        password = signupEditTextPassword.text.toString()
                    )
                )

                startActivity(Intent(this, LoginActivity::class.java))

            }
        }

        signupTextViewToLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

}
