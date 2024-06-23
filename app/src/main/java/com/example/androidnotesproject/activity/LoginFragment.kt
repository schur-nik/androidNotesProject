package com.example.androidnotesproject.activity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidnotesproject.R
import com.example.androidnotesproject.data.User
import com.example.androidnotesproject.databinding.FragmentLoginBinding
import com.example.androidnotesproject.utils.*

class LoginFragment : Fragment() {

    private var binding: FragmentLoginBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentLoginBinding.inflate(inflater, container, false).also { binding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.loginTextViewToSignUp?.setOnClickListener{
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.mainFrame, SignUpFragment())
                .addToBackStack(null)
                .commit()
        }

        binding?.run {
            loginButtonMain.setOnClickListener {
                logInValidate(loginEditTextEmail.text.toString(), "EMAIL_ADDRESS").apply {
                    when (this) {
                        is ValidateResult.Invalid -> loginEditTextEmail.error = requireContext().getErrorString(errorCode)
                        is ValidateResult.Valid -> loginEditTextEmail.error = null
                    }
                }

                logInValidate(loginEditTextPassword.text.toString()).apply {
                    when (this) {
                        is ValidateResult.Invalid -> loginEditTextPassword.error = requireContext().getErrorString(errorCode)
                        is ValidateResult.Valid -> loginEditTextPassword.error = null
                    }
                }

                if (loginEditTextEmail.error.isNullOrBlank() && loginEditTextPassword.error.isNullOrBlank()) {
                    logIn(
                        context = requireContext(),
                        user = User(
                            email = loginEditTextEmail.text.toString(),
                            password = loginEditTextPassword.text.toString()
                        )
                    )

                    parentFragmentManager
                        .beginTransaction()
                        .replace(R.id.mainFrame, NotesFragment())
                        .addToBackStack(null)
                        .commit()

                }
            }
        }
    }

}