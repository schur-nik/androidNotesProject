package com.example.androidnotesproject.ui.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.androidnotesproject.db.entities.UserEntity
import com.example.androidnotesproject.databinding.FragmentSignupBinding
import com.example.androidnotesproject.ui.login.LoginFragment
import com.example.androidnotesproject.navigation.navigator
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
            navigator().replaceFragment(LoginFragment())
        }

        binding?.run {
            signupButtonMain.setOnClickListener {
                signupEditTextFirstName.apply {
                    viewModel.signUpValidate(text.toString(), "FIRST_NAME").also { validateResult ->
                        setFieldError(this, validateResult)
                    }
                }

                signupEditTextEmail.apply {
                    viewModel.signUpValidate(text.toString(), "EMAIL_ADDRESS")
                        .also { validateResult ->
                            setFieldError(this, validateResult)
                        }
                }

                signupEditTextPassword.apply {
                    viewModel.signUpValidate(text.toString(), "PASSWORD").also { validateResult ->
                        setFieldError(this, validateResult)
                    }
                }

                if (signupEditTextFirstName.error.isNullOrBlank() && signupEditTextEmail.error.isNullOrBlank() && signupEditTextPassword.error.isNullOrBlank()) {
                    viewModel.signUp(
                        context = requireContext(),
                        email = signupEditTextEmail.text.toString(),
                        firstname = signupEditTextFirstName.text.toString(),
                        lastname = signupEditTextLastName.text.toString(),
                        password = signupEditTextPassword.text.toString()
                    )

                    navigator().replaceFragment(LoginFragment())
                }
            }
        }
    }

}
