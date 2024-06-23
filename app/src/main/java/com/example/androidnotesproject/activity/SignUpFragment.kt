package com.example.androidnotesproject.activity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidnotesproject.R
import com.example.androidnotesproject.data.User
import com.example.androidnotesproject.databinding.FragmentSignupBinding
import com.example.androidnotesproject.utils.*

class SignUpFragment : Fragment() {

    private var binding: FragmentSignupBinding? = null;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentSignupBinding.inflate(inflater, container, false).also { binding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.signupTextViewToLogin?.setOnClickListener{
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.mainFrame, LoginFragment())
                .addToBackStack(null)
                .commit()
        }

        binding?.run {
            signupButtonMain.setOnClickListener {
                signUpValidate(signupEditTextFirstName.text.toString(), "FIRST_NAME").apply {
                    when (this) {
                        is ValidateResult.Invalid -> signupEditTextFirstName.error = requireContext().getErrorString(errorCode)
                        is ValidateResult.Valid -> signupEditTextFirstName.error = null
                    }
                }

                signUpValidate(signupEditTextEmail.text.toString(), "EMAIL_ADDRESS").apply {
                    when (this) {
                        is ValidateResult.Invalid -> signupEditTextEmail.error = requireContext().getErrorString(errorCode)
                        is ValidateResult.Valid -> signupEditTextEmail.error = null
                    }
                }

                signUpValidate(signupEditTextPassword.text.toString(), "PASSWORD").apply {
                    when (this) {
                        is ValidateResult.Invalid -> signupEditTextPassword.error = requireContext().getErrorString(errorCode)
                        is ValidateResult.Valid -> signupEditTextPassword.error = null
                    }
                }

                if (signupEditTextFirstName.error.isNullOrBlank() && signupEditTextEmail.error.isNullOrBlank() && signupEditTextPassword.error.isNullOrBlank()) {
                    signUp(
                        context = requireContext(),
                        user = User(
                            email = signupEditTextEmail.text.toString(),
                            firstname = signupEditTextFirstName.text.toString(),
                            lastname = signupEditTextLastName.text.toString(),
                            password = signupEditTextPassword.text.toString()
                        )
                    )

                    parentFragmentManager
                        .beginTransaction()
                        .replace(R.id.mainFrame, LoginFragment())
                        .addToBackStack(null)
                        .commit()

                }
            }
        }
    }

}
