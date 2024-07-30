package com.example.androidnotesproject.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.androidnotesproject.R
import com.example.androidnotesproject.databinding.FragmentLoginBinding
import com.example.androidnotesproject.extensions.showToastShort
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
            navigator().replaceFragment(SignUpFragment())
        }

        binding?.run {
            loginButtonMain.setOnClickListener {
                loginEditTextEmail.apply {
                    viewModel.logInValidate(text.toString(), "EMAIL_ADDRESS")
                        .also { validateResult ->
                            setFieldError(this, validateResult)
                        }
                }

                loginEditTextPassword.apply {
                    viewModel.logInValidate(text.toString()).also { validateResult ->
                        setFieldError(this, validateResult)
                    }
                }

                if (loginEditTextEmail.error.isNullOrBlank() && loginEditTextPassword.error.isNullOrBlank()) {
                    val successful = viewModel.logIn(
                        loginEditTextEmail.text.toString(),
                        loginEditTextPassword.text.toString()
                    )
                    requireContext().showToastShort(
                        requireContext().getString(successful)
                    )
                    if (successful == R.string.login_successful_text
                    ) {
                        navigator().replaceFragment(NotesFragment(), false)
                    }

                }
            }
        }
    }

}