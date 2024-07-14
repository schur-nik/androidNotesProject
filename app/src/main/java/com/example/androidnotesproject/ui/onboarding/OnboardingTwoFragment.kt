package com.example.androidnotesproject.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidnotesproject.databinding.FragmentOnboardingTwoBinding
import com.example.androidnotesproject.ui.login.LoginFragment
import com.example.androidnotesproject.navigation.navigator

class OnboardingTwoFragment : Fragment() {

    private var binding: FragmentOnboardingTwoBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentOnboardingTwoBinding.inflate(inflater, container, false).also { binding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.onboardingTwoTextViewSkip?.setOnClickListener{
            navigator().cancelFragment()
            navigator().startFragment(LoginFragment())
        }
    }

}