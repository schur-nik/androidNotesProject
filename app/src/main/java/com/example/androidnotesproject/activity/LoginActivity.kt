package com.example.androidnotesproject.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidnotesproject.data.User
import com.example.androidnotesproject.databinding.ActivityLoginBinding
import com.example.androidnotesproject.utils.*

class LoginActivity : AppCompatActivity() {

    private var binding: ActivityLoginBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        binding?.loginTextViewToSignUp?.setOnClickListener{
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        binding?.run {
            loginButtonMain.setOnClickListener {
                logInValidate(loginEditTextEmail.text.toString(), "EMAIL_ADDRESS").apply {
                    when (this) {
                        is ValidateResult.Invalid -> loginEditTextEmail.error = getErrorString(errorCode)
                        is ValidateResult.Valid -> loginEditTextEmail.error = null
                    }
                }

                logInValidate(loginEditTextPassword.text.toString()).apply {
                    when (this) {
                        is ValidateResult.Invalid -> loginEditTextPassword.error = getErrorString(errorCode)
                        is ValidateResult.Valid -> loginEditTextPassword.error = null
                    }
                }

                if (loginEditTextEmail.error.isNullOrBlank() && loginEditTextPassword.error.isNullOrBlank()) {
                    logIn(
                        context = this@LoginActivity,
                        user = User(
                            email = loginEditTextEmail.text.toString(),
                            password = loginEditTextPassword.text.toString()
                        )
                    )

                    startActivity(Intent(this@LoginActivity, NotesActivity::class.java))

                }
            }
        }
    }

}