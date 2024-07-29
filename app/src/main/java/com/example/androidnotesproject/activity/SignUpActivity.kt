package com.example.androidnotesproject.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidnotesproject.data.User
import com.example.androidnotesproject.databinding.ActivitySignupBinding
import com.example.androidnotesproject.utils.*

class SignUpActivity : AppCompatActivity() {

    private var binding: ActivitySignupBinding? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        binding?.signupTextViewToLogin?.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding?.run {
            signupButtonMain.setOnClickListener {
                signUpValidate(signupEditTextFirstName.text.toString(), "FIRST_NAME").apply {
                    when (this) {
                        is ValidateResult.Invalid -> signupEditTextFirstName.error = getErrorString(errorCode)
                        is ValidateResult.Valid -> signupEditTextFirstName.error = null
                    }
                }

                signUpValidate(signupEditTextEmail.text.toString(), "EMAIL_ADDRESS").apply {
                    when (this) {
                        is ValidateResult.Invalid -> signupEditTextEmail.error = getErrorString(errorCode)
                        is ValidateResult.Valid -> signupEditTextEmail.error = null
                    }
                }

                signUpValidate(signupEditTextPassword.text.toString(), "PASSWORD").apply {
                    when (this) {
                        is ValidateResult.Invalid -> signupEditTextPassword.error = getErrorString(errorCode)
                        is ValidateResult.Valid -> signupEditTextPassword.error = null
                    }
                }

                if (signupEditTextFirstName.error.isNullOrBlank() && signupEditTextEmail.error.isNullOrBlank() && signupEditTextPassword.error.isNullOrBlank()) {
                    signUp(
                        context = this@SignUpActivity,
                        user = User(
                            email = signupEditTextEmail.text.toString(),
                            firstname = signupEditTextFirstName.text.toString(),
                            lastname = signupEditTextLastName.text.toString(),
                            password = signupEditTextPassword.text.toString()
                        )
                    )

                    startActivity(Intent(this@SignUpActivity, LoginActivity::class.java))

                }
            }
        }
    }

}
