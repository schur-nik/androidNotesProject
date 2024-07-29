package com.example.androidnotesproject.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.androidnotesproject.db.User
import com.example.androidnotesproject.databinding.FragmentLoginBinding
import com.example.androidnotesproject.extensions.getErrorString
import com.example.androidnotesproject.ui.notes.NotesFragment
import com.example.androidnotesproject.ui.signup.SignUpFragment
import com.example.androidnotesproject.navigation.navigator
import com.example.androidnotesproject.utils.*

class LoginFragment : Fragment() {

    private var binding: FragmentLoginBinding? = null

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentLoginBinding.inflate(inflater, container, false).also { binding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.loginTextViewToSignUp?.setOnClickListener {
            navigator().startFragment(SignUpFragment())
        }

        binding?.run {
            loginButtonMain.setOnClickListener {
                viewModel.logInValidate(loginEditTextEmail.text.toString(), "EMAIL_ADDRESS").apply {
                    when (this) {
                        is ValidateResult.Invalid -> loginEditTextEmail.error =
                            requireContext().getErrorString(errorCode)
                        else -> loginEditTextEmail.error = null
                    }
                }

                viewModel.logInValidate(loginEditTextPassword.text.toString()).apply {
                    when (this) {
                        is ValidateResult.Invalid -> loginEditTextPassword.error =
                            requireContext().getErrorString(errorCode)
                        else -> loginEditTextPassword.error = null
                    }
                }

                if (loginEditTextEmail.error.isNullOrBlank() && loginEditTextPassword.error.isNullOrBlank()) {
                    viewModel.logIn(
                        context = requireContext(),
                        user = User(
                            email = loginEditTextEmail.text.toString(),
                            password = loginEditTextPassword.text.toString()
                        )
                    )

                    navigator().startFragment(NotesFragment())
                }
            }
        }
    }

}