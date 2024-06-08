package com.example.androidnotesproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


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
            Auth.signUpValidate(signupEditTextFirstName.text.toString(), "FIRST_NAME").apply {
                when (this) {
                    is Auth.ValidateResult.Invalid -> signupEditTextFirstName.error = Auth.getErrorString(this@SignUpActivity, this.errorCode)
                    else -> signupEditTextFirstName.error = null
                }
            }

            Auth.signUpValidate(signupEditTextEmail.text.toString(), "EMAIL_ADDRESS").apply {
                when (this) {
                    is Auth.ValidateResult.Invalid -> signupEditTextEmail.error = Auth.getErrorString(this@SignUpActivity, this.errorCode)
                    else -> signupEditTextEmail.error = null
                }
            }

            Auth.signUpValidate(signupEditTextPassword.text.toString(), "PASSWORD").apply {
                when (this) {
                    is Auth.ValidateResult.Invalid -> signupEditTextPassword.error = Auth.getErrorString(this@SignUpActivity, this.errorCode)
                    else -> signupEditTextPassword.error = null
                }
            }

            if (signupEditTextFirstName.error.isNullOrBlank() && signupEditTextEmail.error.isNullOrBlank() && signupEditTextPassword.error.isNullOrBlank()) {
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
        }

        signupTextViewToLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

    }

}
