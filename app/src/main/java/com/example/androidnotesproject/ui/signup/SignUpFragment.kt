package com.example.androidnotesproject.ui.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.androidnotesproject.db.User
import com.example.androidnotesproject.databinding.FragmentSignupBinding
import com.example.androidnotesproject.extensions.getErrorString
import com.example.androidnotesproject.ui.login.LoginFragment
import com.example.androidnotesproject.navigation.navigator
import com.example.androidnotesproject.ui.login.LoginViewModel
import com.example.androidnotesproject.utils.*

class SignUpFragment : Fragment() {

    private var binding: FragmentSignupBinding? = null;

    private val viewModel: SignUpModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentSignupBinding.inflate(inflater, container, false).also { binding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.signupTextViewToLogin?.setOnClickListener {
            navigator().startFragment(LoginFragment())
        }

        binding?.run {
            signupButtonMain.setOnClickListener {
                viewModel.signUpValidate(signupEditTextFirstName.text.toString(), "FIRST_NAME").apply {
                    when (this) {
                        is ValidateResult.Invalid -> signupEditTextFirstName.error =
                            requireContext().getErrorString(errorCode)
                        else -> signupEditTextFirstName.error = null
                    }
                }

                viewModel.signUpValidate(signupEditTextEmail.text.toString(), "EMAIL_ADDRESS").apply {
                    when (this) {
                        is ValidateResult.Invalid -> signupEditTextEmail.error =
                            requireContext().getErrorString(errorCode)
                        else -> signupEditTextEmail.error = null
                    }
                }

                viewModel.signUpValidate(signupEditTextPassword.text.toString(), "PASSWORD").apply {
                    when (this) {
                        is ValidateResult.Invalid -> signupEditTextPassword.error =
                            requireContext().getErrorString(errorCode)
                        else -> signupEditTextPassword.error = null
                    }
                }

                if (signupEditTextFirstName.error.isNullOrBlank() && signupEditTextEmail.error.isNullOrBlank() && signupEditTextPassword.error.isNullOrBlank()) {
                    viewModel.signUp(
                        context = requireContext(),
                        user = User(
                            email = signupEditTextEmail.text.toString(),
                            firstname = signupEditTextFirstName.text.toString(),
                            lastname = signupEditTextLastName.text.toString(),
                            password = signupEditTextPassword.text.toString()
                        )
                    )

                    navigator().startFragment(LoginFragment())
                }
            }
        }
    }

}
